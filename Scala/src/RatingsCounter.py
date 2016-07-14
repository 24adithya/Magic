'''
Created on 12-Mar-2016

@author: Aditya.Narayana
'''
from pyspark import SparkConf, SparkContext

conf = SparkConf().setMaster("local").setAppName("RatingsHistogram")
sc = SparkContext(conf = conf)

# lines = sc.textFile("file:///ApSpC/ml-100k/u.data")
# lines = sc.textFile("file:///C:/ApSpC/ml-100k/u.data")
lines = sc.textFile("README.md")
lines.count()
# ratings = lines.map(lambda x: x.split()[2])
# result = ratings.countByValue()
# 
# sortedResults = collections.OrderedDict(sorted(result.items()))
# for key, value in sortedResults.iteritems():
#     print "%s %i" % (key, value)
