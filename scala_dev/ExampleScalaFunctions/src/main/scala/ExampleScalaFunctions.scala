import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object ExampleScalaFunctions {
  import scala.util.matching.Regex
  
  def beginnsWithSpark(line: String): Boolean = {
      line.matches("^(S|s)park.*")
    }
  
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkDummy").setMaster("local[*]")  
    val sc = new SparkContext(conf)
    
    val filename = "file:///Users/hadoop/spark-1.3.1-bin-hadoop1/README.md"
    
    val baseRDD = sc.textFile(filename).filter(beginnsWithSpark)
    
    baseRDD.collect().foreach(println)

  }
}