/* SampleApp.scala:
   This application simply counts the number of lines that contain "val" from itself
 */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
 
object SampleApp {
  def main(args: Array[String]) {
    val txtFile = "file:///Users/hadoop/spark-1.3.1-bin-hadoop1/README.md"
    val conf = new SparkConf().setAppName("Sample Application").setMaster("local[*]")  
    val sc = new SparkContext(conf)
    
    val txtFileLines = sc.textFile(txtFile , 2).cache()
    val numAs = txtFileLines .filter(line => line.contains("Spark")).count()
    println("Lines with Spark: %s".format(numAs))
    val mostWords = txtFileLines.map(line => line.split(" ").size).reduce((a, b) => if (a > b) a else b)
    println("Lines with most words: %s".format(mostWords))
  }
}