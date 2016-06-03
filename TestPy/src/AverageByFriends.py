'''
Created on 15-Mar-2016

@author: Aditya.Narayana
'''
from pyspark import SparkConf, SparkContext
import collections
 
def parseLine(line):
    fields = line.split(',')
    age = int (fields[2])
    friends = int (fields[3])
    return (age, friends)

conf = SparkConf().setMaster("local").setAppName("AvgByFriends")
sc = SparkContext(conf = conf)

lines = sc.textFile("file:///ApSpC/socialNetwork.txt")
ageFriends = lines.map(parseLine)

results = ageFriends.collect()
print '\nAge Friends pair' 
for result in results:
    print result
# sortedResults = collections.OrderedDict(sorted(ageFriends.items()))
# for key, value in sortedResults.iteritems():
#     print "%s %i" % (key, value)

#create an array of key ,value pair
totalFriendsByAgePair = ageFriends.mapValues(lambda x: (x,1))

results = totalFriendsByAgePair.collect()
print '\nTotal Friends By Age Pair'
for result in results:
    print result

totalFriendsByAge = totalFriendsByAgePair.reduceByKey(lambda x,y: (x[0] + y[0], x[1] + y[1]))

results = totalFriendsByAge.collect()
print '\nTotal Friends By Age '
for result in results:
    print result

totalFriendsByAvgAge = totalFriendsByAge.mapValues(lambda x: x[0]/x[1])
results = totalFriendsByAvgAge.collect()
print '\nTotal Friends By Avg. Age '
for result in results:
    print result
