/*
 * Simple example of state-less streaming!!!
 */

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.{Level, Logger}

object ExampleStreaming {
  def main(args: Array[String]) {
    if (args.length > 2) {
      System.err.println("Usage: ExampleStreaming <hostname> <port>")
      System.exit(1)
    }
    
    // create the context with a 1 second size for the micro batch: 
    val sparkConf = new SparkConf().setAppName("ExampleStreaming").setMaster("local[*]")
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    
    // to avoid printing out the INFO messages in the console (needs import org.apache.log4j{Level, Logger}
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)
    
    // create a DStram that will connect to a source 
    // in this example, the source is the serverIP:serverPort
    // given as first and second command line argument    
    val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)
    
    // split each word in each batch and count the occurrences in each batch separately
    val words = lines.flatMap { _.split(" ") }
    val wordCounts = words.map { x => (x, 1) }.reduceByKey(_ + _)
    
    // print the counts on the console
    wordCounts.print()
    
    // start the computation and await the computation to terminate
    ssc.start()
    ssc.awaitTermination()
  }
}