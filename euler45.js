/*
Project Euler
Problem 45:
"It can be verified that T285 = P165 = H143 = 40755.

Find the next triangle number that is also pentagonal and hexagonal."
*/
//generate next hexagonal number
function hexagon(x){
  var answer = (2 * x - 1) * x
  return answer
}

//check whether number is a pentagonal number
function isPent(x){
  var pentagon = (Math.sqrt((24 * x) + 1) + 1) / 6
  if(pentagon % 1.0 == 0.0) {
    return true
  } else {
      return false
  }
}
//all hexagonal numbers are also triangular, and so no additional triangle test is needed

function attempt(start){
  //check through 100 hexagonal numbers to see whether they are also pentagonal
  for(var i = start; i < start + 100; i++){
    var hex = hexagon(i)
    //return if the hexgonal number is pentagonal
    if(isPent(hex)){
      return hex
    }
  }
}

//recursive function
function recur(start){
  var feedback = attempt(start)
  //base case returns hexagonal/pentagonal number if found by attempt function
  if(feedback){
    console.log("Answer: " + feedback)
     return feedback
  }
  //otherwise run attempt function with the next 100 numbers
  else {
    recur(start + 100)
  }
}
//begin recursive function from 144, to start with next hexagonal number after 40755
recur(144)
