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
    from operator import add

    print ("Successfully imported Spark Modules")

except ImportError as e:
    print ("Can not import Spark Modules", e)
    sys.exit(1)

# CONSTANTS
APP_NAME = "Spark Extract Information Example"


# OTHER FUNCTIONS/CLASSES

def test_spark_context(sc):
    print "####### test spark context #######"
    print "Spark version: ", sc.version, " on: ", sc.master


def extract_information(row):
    import re
    import numpy as np

    selected_indices = [
         2,4,5,6,7,9,10,11,12,13,14,15,16,17,18,
         19,21,22,23,24,25,27,28,29,30,32,33,34,
         36,37,38,39,40,41,42,43,44,45,46,47,48,
         49,50,51,52,53,54,55,56,58,60,61,62,63,
         64,65,66,67,68,69,70,71,72,73,74,75,76,
         77,78,79,81,82,83,84,85,87,89
    ]

    record_split = re.compile("([\s]{19})([0-9]{1})([\s]{40})([0-9\s]{2})([0-9\s]{1})([0-9]{1})([0-9]{2})" +
                         "([\s]{2})([FM]{1})([0-9]{1})([0-9]{3})([0-9\s]{1})([0-9]{2})([0-9]{2})" +
                         "([0-9]{2})([0-9\s]{2})([0-9]{1})([SMWDU]{1})([0-9]{1})([\s]{16})([0-9]{4})" +
                         "([YNU]{1})([0-9\s]{1})([BCOU]{1})([YNU]{1})([\s]{34})([0-9\s]{1})([0-9\s]{1})" +
                         "([A-Z0-9\s]{4})([0-9]{3})([\s]{1})([0-9\s]{3})([0-9\s]{3})([0-9\s]{2})([\s]{1})" +
                         "([0-9\s]{2})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})" +
                         "([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})" +
                         "([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})" +
                         "([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})([A-Z0-9\s]{7})" +
                         "([A-Z0-9\s]{7})([\s]{36})([A-Z0-9\s]{2})([\s]{1})([A-Z0-9\s]{5})([A-Z0-9\s]{5})" +
                         "([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})" +
                         "([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})" +
                         "([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})" +
                         "([A-Z0-9\s]{5})([A-Z0-9\s]{5})([A-Z0-9\s]{5})([\s]{1})([0-9\s]{2})([0-9\s]{1})" +
                         "([0-9\s]{1})([0-9\s]{1})([0-9\s]{1})([\s]{33})([0-9\s]{3})([0-9\s]{1})([0-9\s]{1})")

    try:
        rs = np.array(record_split.split(row))[selected_indices]
    except:
        rs = np.array(['-99'] * len(selected_indices))
    return rs
    #     return record_split.split(row)

def read_from_file(path):
    """

    :type path: object
    """
    assert isinstance(path, basestring)
    raw_data = sc.textFile(path, 4)
    extract_information(raw_data)
    data_from_file_conv = raw_data.map(extract_information)
    print "####### in calling function: #########"
    print data_from_file_conv.map(lambda row: row).take(1)

    data_2014 = data_from_file_conv.map(lambda row: int(row[16]))
    print data_2014.take(10)

# Main functionality

if __name__ == "__main__":
    # for python logging:
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
    rows = read_from_file("/gbdmp/data/other_sources/VS14MORT.txt")
