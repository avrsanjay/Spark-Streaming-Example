import scala.math.BigInt
import java.nio.ByteBuffer
import org.apache.hadoop.hbase.util.Bytes
import scala.util.matching.Regex


object testplay {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(187); 
		val topics = "MARKET1,MARKET2,MARKET3";System.out.println("""topics  : String = """ + $show(topics ));$skip(62); 
		val result = scala.collection.mutable.MutableList[String]();System.out.println("""result  : scala.collection.mutable.MutableList[String] = """ + $show(result ));$skip(60); 
		val topicIterator = topics.split(",").toIterable.iterator;System.out.println("""topicIterator  : Iterator[String] = """ + $show(topicIterator ));$skip(145); 
		while(topicIterator.hasNext){
		  val topic = topicIterator.next()
		  println("topic:"+topic)
		  val market = topic
		  result += market
		};$skip(20); 
    println(result)}
}
