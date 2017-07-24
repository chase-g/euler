'''
Project Euler 9
There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
'''
answer = 0
def triples(start1,start2):
    #Euclid's formula for finding Pythagorean triples
    a = start1**2 - start2**2
    b = 2 * start1 * start2
    c = start1**2 + start2**2
    if a + b + c == 1000 and a > 0:
        answer = a * b * c
        print(answer)
#loop over each integer combination between 1-500
for i in range(1,500):
    for n in range(1,500):
#stop when answer is filled or continue calling Euclid's formula
        if answer == 0:
            triples(i,n)
