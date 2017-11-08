import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import java.text.SimpleDateFormat

//attention, SimpleDateFormat only compiles under scala 2.10.4
//compiling using scala 2.11.7 leads to the following esception: 
//Exception in thread "main" java.lang.NoSuchMethodError: scala.runtime.VolatileObjectRef.zero()Lscala/runtime/VolatileObjectRef;
 
object ExampleCaseClass {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("ExampleCaseClass").setMaster("local[*]")  
    val sc = new SparkContext(conf)

    val format = new java.text.SimpleDateFormat("yyyy-MM-dd")

    case class Register (d: java.util.Date, uuid: String, cust_id: String, lat: Float, lng: Float)
    case class Click (d: java.util.Date, uuid: String, landing_page: Int)
    
    /* to run on cluster using spark-submit   
    val reg = sc.textFile("/user/hadoop/data/reg.tsv").map(_.split("\t")).map(r => (r(1), Register(format.parse(r(0)), r(1), r(2), r(3).toFloat, r(4).toFloat)))
      
    val clk = sc.textFile("/user/hadoop/data/clk.tsv").map(_.split("\t")).map(c => (c(1), Click(format.parse(c(0)), c(1), c(2).trim.toInt)))
      
    */  

    // to run locally using run configuration run_ExampleCaseClass:
    val reg = sc.textFile("file:///Users/hadoop/Documents/programs/scala/oreilly-spark-training/data/join/reg.tsv").map(_.split("\t")).map(r => (r(1), Register(format.parse(r(0)), r(1), r(2), r(3).toFloat, r(4).toFloat)))
      
    val clk = sc.textFile("file:///Users/hadoop/Documents/programs/scala/oreilly-spark-training/data/join/clk.tsv").map(_.split("\t")).map(c => (c(1), Click(format.parse(c(0)), c(1), c(2).trim.toInt)))

    
    reg.join(clk).collect().foreach { println }
    
  }
}