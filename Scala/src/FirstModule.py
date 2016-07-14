'''
Created on 12-Mar-2016

@author: Aditya.Narayana
'''
def add(a,b):
    return a+b

def addFixedValue(a):
    y = 5
    return y +a
  
print add(1,2)
print addFixedValue(1) 

f = open('C:/Spark1.6Hadoop2.6/README.md', 'r')
print f

for line in f:
    print line.rstrip()