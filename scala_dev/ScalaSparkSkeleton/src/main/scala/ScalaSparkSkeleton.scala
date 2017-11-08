/**
  * Created by hadoop on 8/21/16 as Spark Scala Skeleton!
  */

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.log4j.Logger
import org.apache.log4j.Level

object ScalaSparkSkeleton {
  def main(args: Array[String]): Unit = {

   val conf = new SparkConf()    // defining a spark configuration object
      .setMaster("local[*]")
      .setAppName("ScalaSkeleton")
      .set("spark.executor.memory", "2g")

    // set log-level to error
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)

    val sc = new SparkContext(conf)   // this is the actual starting execution point

    //val lines = sc.parallelize(Seq("This is the first line", "This is the second line", "This is the third line"))
    //val lines = sc.textFile("file:///home/hadoop/tmp/spark_input.txt")    // test for local file input
    val lines = sc.textFile("hdfs://hadoop.cloudera:8020/tmp/test_input_data/problems_and_solutions") // test from hdfs

    val count = lines.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    count.foreach(println)
    println("Test Spark Scala Skeleton finished!")

  }
}
