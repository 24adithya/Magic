'''
Created on 12-Mar-2016

@author: Aditya.Narayana
'''
from pyspark import SparkConf, SparkContext
import os

sparkConf = SparkConf().setMaster("local").setAppName("RatingsHistogram")
sc = SparkContext(conf = sparkConf)

# The WordCounts Spark program
textFile = sc.textFile(os.environ["SPARK_HOME"] + "/README.md")
wordCounts = textFile.flatMap(lambda line: line.split()).map(lambda word: (word, 1)).reduceByKey(lambda a, b: a+b)
for wc in wordCounts.collect(): print wc
