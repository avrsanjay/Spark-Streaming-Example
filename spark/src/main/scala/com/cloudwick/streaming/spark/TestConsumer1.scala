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
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{HBaseAdmin,HTable,Put,Get,Scan,ResultScanner}
import org.apache.hadoop.hbase.filter.{Filter,PrefixFilter,RowFilter}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.Logging
import org.apache.log4j.{Level, Logger}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.apache.spark.SparkConf
import scala.collection.mutable
import java.nio.ByteBuffer




object TestConsumer1{
	def main(args: Array[String]) {
		if (args.length < 4) {
			System.err.println("Usage: KafkaWordCount <zkQuorum> <group> <topics> <numThreads>")
			System.exit(1)
		}
		val Array(zkQuorum, group, topics, numThreads) = args
		val ssc = new StreamingContext("local[2]", "Test", Seconds(5))
		ssc.checkpoint("/user/spark/checkpoint")
		val sparkConf = new SparkConf().setAppName("Hbase Sample")
		val sc = ssc.sparkContext

		
		val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
		val Json = KafkaUtils.createStream(ssc,zkQuorum,group,topicMap)
		.window(Seconds(5), Seconds(5))
		.map(_._2)

		
		Json.foreachRDD(rdd => {
			rdd.foreach { x =>
  			val data = parse(x,false)
			  val StockSymbol = compact(data \\ "StockSymbol")
			  val StockSymbolF = StockSymbol.replace('"', ' ').trim()
			  val StockNumber = compact(data \\ "StockNumber")       
			  val Mp = compact(data \\ "Mp")  
			  val MpF = Mp.replace('$', ' ').replace('\"', ' ').trim()
			  val Bp = compact(data \\ "Bp")
			  val BpF = Bp.replace('$', ' ').replace('\"', ' ').trim()
			  val Ap = compact(data \\ "Ap")
			  val ApF = Ap.replace('$', ' ').replace('\"', ' ').trim()
			  val BQ = compact(data \\ "BQ")         
			  val Aq = compact(data \\ "Aq")         
			  val Vol = compact(data \\ "Vol")
			  val rowId = compact(data \\ "rowId")
			  
			  val conf = new HBaseConfiguration()
		    val admin = new HBaseAdmin(conf)
			  val table = new HTable(conf, "mytable1")
			  	
  			val s = new Scan().setReversed(true)
  			val filter = new PrefixFilter(Bytes.toBytes(StockNumber+"_"))
  			s.setFilter(filter)
  			s.addFamily(Bytes.toBytes("mycf"))
  			val rs = table.getScanner(s)
  			val rit = rs.iterator()
  			if(rit.hasNext()){
  			  val nxt = rit.next()
  			  val oldMp = Bytes.toString(nxt.getValue(Bytes.toBytes("mycf"), Bytes.toBytes("column2")))
  			  val oldBp = Bytes.toString(nxt.getValue(Bytes.toBytes("mycf"), Bytes.toBytes("column3")))
  			  val oldAp = Bytes.toString(nxt.getValue(Bytes.toBytes("mycf"), Bytes.toBytes("column4")))
  			  val NewMp = (MpF.toDouble + oldMp.replace('\"', ' ').trim().toDouble).toString()
  			  val NewBp = (BpF.toDouble + oldBp.replace('\"', ' ').trim().toDouble).toString()
  			  val NewAp = (ApF.toDouble + oldAp.replace('\"', ' ').trim().toDouble).toString()
  			  val ts = System.currentTimeMillis()
  			  
  			  val theput = new Put(Bytes.toBytes(StockNumber.toString()+"_"+ts.toString()),ts)
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column1"),Bytes.toBytes(StockSymbol))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column2"),Bytes.toBytes(NewMp))
  			  theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column3"),Bytes.toBytes(NewBp))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column4"),Bytes.toBytes(NewAp))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column5"),Bytes.toBytes(BQ))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column6"),Bytes.toBytes(Aq))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column7"),Bytes.toBytes(Vol))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column8"),Bytes.toBytes(rowId))
			    table.put(theput)
  			}
  			else {					
			    val ts = System.currentTimeMillis()
  			  val theput = new Put(Bytes.toBytes(StockNumber.toString()+"_"+ts.toString()),ts)
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column1"),Bytes.toBytes(StockSymbol))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column2"),Bytes.toBytes(MpF))
  			  theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column3"),Bytes.toBytes(BpF))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column4"),Bytes.toBytes(ApF))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column5"),Bytes.toBytes(BQ))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column6"),Bytes.toBytes(Aq))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column7"),Bytes.toBytes(Vol))
			    theput.add(Bytes.toBytes("mycf"),Bytes.toBytes("column8"),Bytes.toBytes(rowId))
			    table.put(theput)
  			}
 			}
		})
		ssc.start()
    ssc.awaitTermination()
	}
}