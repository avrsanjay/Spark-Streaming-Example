import java.util.Random
object testplay2 {
val r = new Random()                              //> r  : java.util.Random = java.util.Random@6a1192e9
r.nextInt(4)                                      //> res0: Int = 3
/*package com.cloudwick.streaming.spark

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


object TestConsumer1{
  def main(args: Array[String]) {
		if (args.length < 4) {
			System.err.println("Usage: KafkaWordCount <zkQuorum> <group> <topics> <numThreads>")
			System.exit(1)
		}
		val updateFunc = (values: Seq[(Double,Double)], state: Option[(Double,Double)]) => Option[Double]{
		    values.foreach(x =>{
		      val currentC = x._1.toDouble
          val currentQ = x._2.toDouble
          println("Present C:"+currentC+" Present Q"+currentQ)
          val oldCQ = state.getOrElse((0d,0d))
          val newC = oldCQ._1+currentC
  		    val newQ = oldCQ._2+currentQ
		    })
		    (0d)
		}
		
		val Array(zkQuorum, group, topics, numThreads) = args
    val ssc = new StreamingContext("local[2]", "Test", Seconds(5))
    val sc = ssc.sparkContext
    ssc.checkpoint("/user/spark/checkpoint")
    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val Json = KafkaUtils.createStream(ssc,zkQuorum,group,topicMap)
                         .window(Seconds(10), Seconds(5))
                         .map(_._2)
                         .map(x => (compact(parse(x,false) \\ "Name").toString(),
                                    (compact(parse(x,false) \\ "Cost").toString().replace("$", " ").trim().toDouble,
                                    compact(parse(x,false) \\ "quantity").toDouble)))
    Json.updateStateByKey(updateFunc)
    /*Json.foreachRDD(rdd => {
        rdd.foreach { x =>
              val data = parse(x,false)
              val Date = compact(data \\ "Date")
              val Time = compact(data \\ "Time")
              val Type = compact(data \\ "Type")
              val Agent = compact(data \\ "Agent")
              val quantity = compact(data \\ "quantity")
              val Cost = compact(data \\ "Cost")
              val Name = compact(data \\ "Name")
              val rowId = compact(data \\ "rowId")
              val CostF = Cost.toString().replace("$", " ").trim().toDouble
              val mappedJson = ""
              val inputJson = mappedJson.map(x => (Name, CostF*quantity.toDouble))
         }
    })
    Json.saveAsTextFiles("/user/spark/output", "Dir")*/
    ssc.start()
    ssc.awaitTermination()
  }
}*/
}