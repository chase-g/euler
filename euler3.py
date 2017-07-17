#Project Euler Problem 3
#recursive algorithm
import time
import math
start = time.time()
number = 600851475143
factors = [i for i in range(int((number ** .5) + 1), 2, -1) if number % i == 0]
primeNums = []
print(factors)
def prime(num, factorList):
    ans = factorList[0]
    if num % ans == 0 and ans in primeNums:
        print(ans)
        return ans
    elif all(ans % i for i in range(2, ans)):
        primeNums.append(ans)
        prime(num, factorList)
    else:
        prime(num, factorList[1:])
prime(number, factors)
end = time.time()
print("Speed: ")
print(end - start)

