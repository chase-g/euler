# Project Euler Problem 4
# "Find the largest palindrome made from the product of two 3-digit numbers."
import time
start = time.time()
factor1 = [i for i in range(999,100,-1)]
factor2 = [i for i in range(999,100,-1)]
def palEval(numsA, numsB):
    memo = []
    for i in numsA:
        for n in numsB:
            product = i * n
            prodList = list(str(product))
            if prodList[:(len(prodList) // 2)] == list(reversed(prodList[len(prodList) // 2:])):
                    memo.append(product)
    print(max(memo))
    return max(memo)
palEval(factor1,factor2)
end = time.time()
print("Speed:")
print(str(end - start))
