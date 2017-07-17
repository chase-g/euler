#Project Euler problem 1
import time
start = time.time()
print(sum([i for i in range(1,1000) if (i % 3 == 0 or i % 5 == 0)]))
end = time.time()
print("Speed: " + str(end - start))
