val fruit = Set("apple", "orange", "peach", "banana")
fruit ++ Array[Char](' ')

// one line:
fruit.foreach(e => println(e.toUpperCase))

// multiple lines:
fruit.foreach { f =>
       val s = f.toUpperCase
       println(s)
   }

val illegalTokens = Array[Char]('a', 'b') ++ Array[Char](' ')
illegalTokens.foreach(println)
