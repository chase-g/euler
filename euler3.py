#Project Euler Problem 3
#"What is the largest prime factor of the number 600851475143?"
#recursive algorithm
import time
import math
start = time.time()
number = 600851475143
#create list of numbers (odd only, counting down) 
#which are factors of the square root of the number (rounding up)
factors = [i for i in range(int((number ** .5) + 1), 2, -1) if number % i == 0]
#memo list
primeNums = []
def prime(num, factorList):
    ans = factorList[0]
#base case, if number is divisible by the potential answer and it's already identified as prime
    if num % ans == 0 and ans in primeNums:
        print("Answer: " + str(ans))
        return ans
#If the factor is prime add it to the primeNums list and recursively call prime
    elif all(ans % i for i in range(2, ans)):
        primeNums.append(ans)
        prime(num, factorList)
#If the factor isn't prime then recursively call prime starting with the next factor
    else:
        prime(num, factorList[1:])
#call prime using the list of factors of the number
prime(number, factors)
end = time.time()
print("Speed: " + str(end - start))
