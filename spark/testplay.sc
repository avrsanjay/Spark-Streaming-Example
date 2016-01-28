object testplay {
val person_json = """{"Date": "12/15/15",
  "Time": "2:08:36",
  "Type": "Prospect",
  "Agent": "Dieter Lott",
  "quantity": 185.98,
  "Cost": "$174.72",
  "Name": "Vance Parsons",
  "rowId": 7448024 }"""                           //> person_json  : String = {"Date": "12/15/15",
                                                  //|   "Time": "2:08:36",
                                                  //|   "Type": "Prospect",
                                                  //|   "Agent": "Dieter Lott",
                                                  //|   "quantity": 185.98,
                                                  //|   "Cost": "$174.72",
                                                  //|   "Name": "Vance Parsons",
                                                  //|   "rowId": 7448024 }

val person = scala.util.parsing.json.JSON.parseFull(person_json)
                                                  //> person  : Option[Any] = Some(Map(quantity -> 185.98, Name -> Vance Parsons, 
                                                  //| Agent -> Dieter Lott, Date -> 12/15/15, Cost -> $174.72, rowId -> 7448024.0,
                                                  //|  Time -> 2:08:36, Type -> Prospect))

// returns "Joe Doe"
person match {
  case Some(m: Map[String, Any]) => m("quantity") match {
    //case s: String => s
    case d: Double => d
    //case i: Int => i
  }
}                                                 //> res0: Double = 185.98
}