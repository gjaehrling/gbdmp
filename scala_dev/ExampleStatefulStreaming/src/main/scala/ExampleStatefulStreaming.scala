/*
 * Simple example of state-full streaming!!!
 */

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.{Level, Logger}

object ExampleStatefulStreaming {
  
  // function to calculate the sum of counts:
  def calculateTotal(newValues: Seq[Int], runningCount: Option[Int]): Option[Int] = {
    // add the new values with the previous running count to get the new count
    val newCount = newValues.foldLeft(0)(_ + _)  
    val previousCount = runningCount.getOrElse(0)
    Some(newCount + previousCount)
  }
  
  def main(args: Array[String]) {
    if (args.length > 2) {
      System.err.println("Usage: ExampleStreaming <hostname> <port>")
      System.exit(1)
    }
    
    // create the context with a 1 second size for the micro batch: 
    val sparkConf = new SparkConf().setAppName("ExampleStreaming").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    
    // for statefullness it is necessary to provide a checkpoint
    ssc.checkpoint("streaming")
            
    // to avoid printing out the INFO messages in the console (needs import org.apache.log4j{Level, Logger}
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)
    
    val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)
    
    // split each word in each batch and count the occurrences in each batch as total counts
    val words = lines.flatMap { w => w.split(" ") }
    val pairs = words.map { x => (x, 1) }
    val runningCounts = pairs.updateStateByKey[Int](calculateTotal _)
    val top10 = runningCounts.map(item => item.swap)
    
    //runningCounts.print()
    top10.print()

    ssc.start()
    ssc.awaitTermination()
    
  }
}