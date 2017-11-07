#!/usr/bin/env python

# Imports

import sys
import os

# Path for spark source folder
os.environ['SPARK_HOME'] = "/opt/cloudera/parcels/CDH-5.8.0-1.cdh5.8.0.p0.42/lib/spark"
os.environ['JAVA_HOME'] = "/opt/jdk1.8.0_101/"
# Append pyspark  to Python Path
sys.path.append("/opt/cloudera/parcels/CDH-5.8.0-1.cdh5.8.0.p0.42/lib/spark")
sys.path.append("/opt/jdk1.8.0_101/")

try:
    from pyspark import SparkContext
    from pyspark import SparkConf
    from pyspark.sql import SQLContext
    from pyspark.sql import HiveContext

    print ("Successfully imported Spark Modules")

except ImportError as e:
    print ("Can not import Spark Modules", e)
    sys.exit(1)

# CONSTANTS
APP_NAME = "Spark Application Template"


# OTHER FUNCTIONS/CLASSES


# Main functionality

def test_spark_context(sc):
    print "in main function: "
    rdd = sc.parallelize(range(10000), 10)
    print "the mean is: ", rdd.mean()


def connect_to_database(hc):
    print "in sql function: "
    db = hc.sql("SHOW DATABASES")
    print db.show()


if __name__ == "__main__":
    # Configure OPTIONS
    conf = SparkConf().setAppName(APP_NAME)
    # conf = conf.setMaster("local[*]")
    conf = conf.setMaster("yarn-client")

    sc = SparkContext(conf=conf)
    sqlContext = SQLContext(sc)
    hiveContext = HiveContext(sqlContext)
    sc.setLogLevel("ERROR")
    # Execute Main functionality
    test_spark_context(sc)
    connect_to_database(hiveContext)
