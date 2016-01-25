object testplay {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val input = List("12/11/2015,23:48:16","Prospect","Pandora Blankenship","113.71","$122.99 ","Chancellor Valentine")
                                                  //> input  : List[String] = List(12/11/2015,23:48:16, Prospect, Pandora Blankens
                                                  //| hip, 113.71, "$122.99 ", Chancellor Valentine)
  val mapped = (input.indices zip input).toMap    //> mapped  : scala.collection.immutable.Map[Int,String] = Map(0 -> 12/11/2015,2
                                                  //| 3:48:16, 5 -> Chancellor Valentine, 1 -> Prospect, 2 -> Pandora Blankenship,
                                                  //|  3 -> 113.71, 4 -> "$122.99 ")
mapped.foreach                                                //> (0,12/11/2015,23:48:16)
                                                  //| (5,Chancellor Valentine)
                                                  //| (1,Prospect)
                                                  //| (2,Pandora Blankenship)
                                                  //| (3,113.71)
                                                  //| (4,$122.99 )
	//val lines = input.toMap
  
}