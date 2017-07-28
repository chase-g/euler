# Project Euler problem 5
# "What is the smallest positive number that is
# evenly divisible by all of the numbers from 1 to 20?"
import time
start = time.time()
def smallestFactor(start):
    #list comprehension filters out numbers not divisible by 20-11
    options = [i for i in range(start, start + 1000000, 20) if i % 11 == 0 and i % 13 == 0 \
               and i % 14 == 0 and i % 16 == 0 and i % 17 == 0 and i % 18 == 0 and i % 19 == 0 \
               and i % 20 == 0]
    #returns if found
    if len(options) > 0:
        print(options[0])
        return options[0]
    #recursion with larger numbers if not found
    else:
        smallestFactor(start + 1000000)
smallestFactor(2520)
end = time.time()
print("Speed: " + str(end - start))
