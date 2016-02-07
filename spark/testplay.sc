import scala.math.BigInt
import java.nio.ByteBuffer
import org.apache.hadoop.hbase.util.Bytes


object testplay {
	val s = "$114.85".toString()              //> s  : String = $114.85
	val r = s.replace('$', ' ').replace('\"', ' ').trim()
                                                  //> r  : String = 114.85
	val q = r.toDouble                        //> q  : Double = 114.85
	
}