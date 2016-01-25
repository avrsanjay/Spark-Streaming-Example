object testplay {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(61); 
  println("Welcome to the Scala worksheet");$skip(118); 
  val input = List("12/11/2015,23:48:16","Prospect","Pandora Blankenship","113.71","$122.99 ","Chancellor Valentine");System.out.println("""input  : List[String] = """ + $show(input ));$skip(47); 
  val mapped = (input.indices zip input).toMap;System.out.println("""mapped  : scala.collection.immutable.Map[Int,String] = """ + $show(mapped ));$skip(15); val res$0 = 
mapped.foreach;System.out.println("""res0: <error> = """ + $show(res$0))}
	//val lines = input.toMap
  
}
