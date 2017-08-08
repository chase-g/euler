#Project Euler 20
#"Find the sum of the digits in the number 100!"
def factorial(n):
	answer = 1
	while(n > 0):
		answer = answer * n
		n = n - 1
	return answer
num = factorial(100)
st = str(num)
amount = 0
for i in st:
    amount += int(i)
print(amount)
