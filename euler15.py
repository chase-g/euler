#Project Euler 15
'''
"Starting in the top left corner of a 2×2 grid,
and only being able to move to the right and down,
there are exactly 6 routes to the bottom right corner.
How many such routes are there through a 20×20 grid?"
'''
#define factorial function
def factorial(n):
	answer = 1
	while(n > 0):
		answer = answer * n
		n = n - 1
	return answer
#find central binomial coefficient of 20
ans = factorial(2 * 20) / factorial(20)**2
print(ans)
