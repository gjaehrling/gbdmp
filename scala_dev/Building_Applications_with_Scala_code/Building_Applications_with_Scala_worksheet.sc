"Hello World"

// scala uses object to define classes
// in scala an object is a singleton by default
object HelloWorld1 {
  def main(args: Array[String]) = println("Hello World")
}
HelloWorld1.main(null)

// another possibility is to use inheritance from the App class
// this avoids the definition of a main method:
object HelloWorld2 extends App {
  println("Hello World")
}
HelloWorld2
HelloWorld2.main(null)