



import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONFormatobject hi
 {
	val Date = "12/13/15"
  val Time = "15:14:42"
  val Type = "Prospect"
  val Agent = "Ciaran Snow"
  val quantity = 129.65
  val Cost = "$117.45"
  val Name = "Vladimir Bright"
  val rowId = 7717090
}

case class Inputschema(Date: String, Time: String, Type: String, Agent: String, Quantity: Double, Cost: String, Name: String, RowID: Long)

object jsontest2{;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(686); 
	val input = Map("Date" -> "12/13/15","Time" -> "15:14:42",
  								"Type" -> "Prospect","Agent" -> "Ciaran Snow",
  								"quantity" -> 129.65,"Cost" -> "$117.45",
  								"Name" -> "Vladimir Bright","rowId" -> 7717090);System.out.println("""input  : scala.collection.immutable.Map[String,Any] = """ + $show(input ));$skip(33); 
	
	val p = new JSONObject(input);System.out.println("""p  : scala.util.parsing.json.JSONObject = """ + $show(p ));$skip(14); val res$0 = 
	p.toString();System.out.println("""res0: String = """ + $show(res$0))}
}
