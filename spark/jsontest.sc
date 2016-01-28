



import scala.util.parsing.json.JSONObject
object hi
 {
	val Date = "12/13/15"                     //> Date  : String = 12/13/15
  val Time = "15:14:42"                           //> Time  : String = 15:14:42
  val Type = "Prospect"                           //> Type  : String = Prospect
  val Agent = "Ciaran Snow"                       //> Agent  : String = Ciaran Snow
  val quantity = 129.65                           //> quantity  : Double = 129.65
  val Cost = "$117.45"                            //> Cost  : String = $117.45
  val Name = "Vladimir Bright"                    //> Name  : String = Vladimir Bright
  val rowId = 7717090                             //> rowId  : Int = 7717090
}

case class Inputschema(Date: String, Time: String, Type: String, Agent: String, Quantity: Double, Cost: String, Name: String, RowID: Long)

object jsontest2{
	val input = Map("Date" -> "12/13/15","Time" -> "15:14:42",
  								"Type" -> "Prospect","Agent" -> "Ciaran Snow",
  								"quantity" -> 129.65,"Cost" -> "$117.45",
  								"Name" -> "Vladimir Bright","rowId" -> 7717090)
	
	val p = new JSONObject(input)
	

}