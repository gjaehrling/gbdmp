/**
  * Created by hadoop on 8/21/16 as Spark Scala Skeleton!
  */

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object Example1 {
  // instantiation of conf and sc as "global variables"
  val conf = new SparkConf() // defining a spark configuration object
    .setMaster("local[*]")
    .setAppName("HighPerformanceSpark_Example1")
    .set("spark.executor.memory", "2g")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

    // call function for counting the data and returning an RDD:
    val res = countData("hdfs://hadoop.cloudera:8020/tmp/test_input_data/problems_and_solutions")
    res.foreach(println)

    // testing badIdea:
    val inputRdd = sc.textFile("hdfs://hadoop.cloudera:8020/tmp/test_input_data/problems_and_solutions")
    val badIdeaOutput = badIdea(inputRdd)
    badIdeaOutput.foreach(println)

    println("Test Spark Example finished!")

  }

  // method counting the occurrences
  def countData(path: String): RDD[(String, Int)] = {
    println("in method countData: ")
    val res = sc.textFile(path)
    val count = res.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    count
  }

  // bad idea: uses group by key
  def badIdea(rdd: RDD[String]): RDD[(String, Int)] = {
    println("in method badIdea: ")
    val words = rdd.flatMap(_.split(" "))
    val wordPairs = words.map((_, 1))
    val grouped = wordPairs.groupByKey()
    val wordCounts = grouped.mapValues(_.sum)
    wordCounts
  }

  // good idea: doesn't use group by key
  //tag::simpleWordCount[]
  def simpleWordCount(rdd: RDD[String]): RDD[(String, Int)] = {
    val words = rdd.flatMap(_.split(" "))
    val wordPairs = words.map((_, 1))
    val wordCounts = wordPairs.reduceByKey(_ + _)
    wordCounts
  }

  //end::simpleWordCount[]

  // example of filtering an RDD with stopWords and illegalTokens:
  def withStopWordsFiltered(rdd: RDD[String], illegalTokens: Array[Char], stopWords: Set[String]): RDD[(String, Int)] = {
    val tokens: RDD[String] = rdd.flatMap(_.split(illegalTokens ++ Array[Char](' ')).
      map(_.trim.toLowerCase))
    val words = tokens.filter(token =>
      !stopWords.contains(token) && (token.length > 0))
    val wordPairs = words.map((_, 1))
    val wordCounts = wordPairs.reduceByKey(_ + _)
    wordCounts
  }
}
