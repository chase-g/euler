answer = 0
#Create dictionary with alphabet to number associations
alpha = dict(zip('ABCDEFGHIJKLMNOPQRSTUVWXYZ', range(1, 27)))
#open file, split into list items by comma, and remove quotation marks
with open("p022_names.txt") as file: 
    content = file.read().split(",")   
    content = [i.strip('\"') for i in content]   
content.sort()   #sort alphabetically
for i in content:   #outer loop for each list item with counter variable
    total = 0
    for n in i:   #inner loop for each letter of each list item
        n = alpha[n]   #assign to associated number value
        total += n   #increment counter by number
    total *= (content.index(i) + 1)   #multiply total by position
    answer += total
print(answer)

        
        
