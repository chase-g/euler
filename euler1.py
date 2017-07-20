#Project Euler problem 1
#"Find the sum of all the multiples of 3 or 5 below 1000"
import time
start = time.time()
print(sum([i for i in range(1,1000) if (i % 3 == 0 or i % 5 == 0)]))
end = time.time()
print("Speed: " + str(end - start))
