



import scala.util.parsing.json.JSONObject
object hi
 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(81); 
	val Date = "12/13/15";System.out.println("""Date  : String = """ + $show(Date ));$skip(24); 
  val Time = "15:14:42";System.out.println("""Time  : String = """ + $show(Time ));$skip(24); 
  val Type = "Prospect";System.out.println("""Type  : String = """ + $show(Type ));$skip(28); 
  val Agent = "Ciaran Snow";System.out.println("""Agent  : String = """ + $show(Agent ));$skip(24); 
  val quantity = 129.65;System.out.println("""quantity  : Double = """ + $show(quantity ));$skip(23); 
  val Cost = "$117.45";System.out.println("""Cost  : String = """ + $show(Cost ));$skip(31); 
  val Name = "Vladimir Bright";System.out.println("""Name  : String = """ + $show(Name ));$skip(22); 
  val rowId = 7717090;System.out.println("""rowId  : Int = """ + $show(rowId ))}
}

case class Inputschema(Date: String, Time: String, Type: String, Agent: String, Quantity: Double, Cost: String, Name: String, RowID: Long)

object jsontest2{
	val input = Map("Date" -> "12/13/15","Time" -> "15:14:42",
  								"Type" -> "Prospect","Agent" -> "Ciaran Snow",
  								"quantity" -> 129.65,"Cost" -> "$117.45",
  								"Name" -> "Vladimir Bright","rowId" -> 7717090)
	
	val p = new JSONObject(input)
	

}
