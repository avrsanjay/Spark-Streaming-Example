package com.cloudwick.streaming.spark

import org.apache.spark._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.kafka.KafkaUtils._
import org.apache.spark.sql._
import org.apache.spark.Logging
import org.apache.log4j.{Level, Logger}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{HBaseAdmin,HTable,Put,Get}
import org.apache.hadoop.hbase.util.Bytes


case class Inputschema(Date: String, Time: String, Type: String, Agent: String, Quantity: Double, Cost: String, Name: String, RowID: Long)

object TestConsumer1{
  def main(args: Array[String]) {
		if (args.length < 4) {
			System.err.println("Usage: KafkaWordCount <zkQuorum> <group> <topics> <numThreads>")
			System.exit(1)
		}
		var i:Int = 1                                    
		val Array(zkQuorum, group, topics, numThreads) = args
    val ssc = new StreamingContext("local[2]", "Test", Seconds(5))
    val sc = ssc.sparkContext
    ssc.checkpoint("/user/spark/checkpoint")
    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val Json = KafkaUtils.createStream(ssc,
                                       zkQuorum,
                                       group,
                                       topicMap).window(Seconds(10), Seconds(5)).map(x => x._2)
                                       
    Json.foreachRDD(rdd => {
        rdd.foreach { x =>
              val data = parse(x)
              val Date = compact(data \\ "Date")         
              val Time = compact(data \\ "Time")       
              val Type = compact(data \\ "Type")         
              val Agent = compact(data \\ "Agent")         
              val quantity = compact(data \\ "quantity")         
              val Cost = compact(data \\ "Cost")         
              val Name = compact(data \\ "Name")         
              val rowId = compact(data \\ "rowId")
              val quantityF = quantity.toString().replace("$", " ").trim().toDouble
              "Message "+i+":[{Date:"+Date+"},{Time:"+Time+"},{Type:"+Type+"},{Agent:"+Agent+"},{quantity:"+quantityF+"},{Cost:"+Cost+"},{Name:"+Name+"},{rowId:"+rowId+"}]"
         }
    })        
    Json.saveAsTextFiles("/user/spark/output", "dir")    
    ssc.start()
    ssc.awaitTermination()
  }
}