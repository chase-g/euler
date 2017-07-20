#Project Euler problem 2
#"By considering the terms in the Fibonacci sequence whose values do not exceed four million, 
#find the sum of the even-valued terms."
import time
start = time.time()
nums = [1,2]
evenTotal = 0
for i in nums:
    if nums[len(nums) - 1] < 4000000:
        nums.append((nums[len(nums) - 1] + nums[len(nums) - 2]))
for i in nums:
    if i % 2 == 0:
        evenTotal += i
print(nums)
print(evenTotal)
end = time.time()
print("Speed: " + str(end - start))
