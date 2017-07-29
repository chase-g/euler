# Project Euler 12
# "What is the value of the first triangle number
# to have over five hundred divisors?"
import itertools
import time
t0 = time.time()
start = 0
def factors(num):
    #get square root
    newnum = int((num ** .5) + 1)
    #get length of list every factor up to square root and then double 
    return len([i for i in range(1, newnum) if num % i == 0]) * 2
def triangles():
    total = 0
    #iterate up from one
    for i in itertools.count(1,1):
        #add to total (triangular number)
        total += i
        #print if length of factor list is greater than 500
        if(factors(total) > 500):
            print("Answer: " + str(total))
            print("Factors: " + str(factors(total)))
            break
triangles()
t1 = time.time()
print("Speed: " + str(t1 - t0))

