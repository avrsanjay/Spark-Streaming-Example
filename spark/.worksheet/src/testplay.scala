object testplay {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(222); 
val person_json = """{"Date": "12/15/15",
  "Time": "2:08:36",
  "Type": "Prospect",
  "Agent": "Dieter Lott",
  "quantity": 185.98,
  "Cost": "$174.72",
  "Name": "Vance Parsons",
  "rowId": 7448024 }""";System.out.println("""person_json  : String = """ + $show(person_json ));$skip(66); 

val person = scala.util.parsing.json.JSON.parseFull(person_json);System.out.println("""person  : Option[Any] = """ + $show(person ));$skip(174); val res$0 = 

// returns "Joe Doe"
person match {
  case Some(m: Map[String, Any]) => m("quantity") match {
    //case s: String => s
    case d: Double => d
    //case i: Int => i
  }
};System.out.println("""res0: Double = """ + $show(res$0))}
}
