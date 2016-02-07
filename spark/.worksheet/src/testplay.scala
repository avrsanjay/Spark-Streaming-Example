import scala.math.BigInt
import java.nio.ByteBuffer
import org.apache.hadoop.hbase.util.Bytes


object testplay {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(143); 
	val s = "$114.85".toString();System.out.println("""s  : String = """ + $show(s ));$skip(55); 
	val r = s.replace('$', ' ').replace('\"', ' ').trim();System.out.println("""r  : String = """ + $show(r ));$skip(20); 
	val q = r.toDouble;System.out.println("""q  : Double = """ + $show(q ))}
	
}
