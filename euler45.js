/*It can be verified that T285 = P165 = H143 = 40755.

Find the next triangle number that is also pentagonal and hexagonal.
*/
function hexagon(x){
  var answer = (2 * x - 1) * x
  return answer
}

function isPent(x){
  var pentagon = (Math.sqrt((24 * x) + 1) + 1) / 6
  if(pentagon % 1.0 == 0.0) {
    return true
  } else {
      return false
  }
}

function attempt(start){
  for(var i = start; i < start + 100; i++){
    var hex = hexagon(i)
    if(isPent(hex)){
      return hex
    }
  }
}

function recur(start){
  var feedback = attempt(start)
  if(feedback){
    console.log("Answer: " + feedback)
     return feedback
  }
  else {
    recur(start + 100)
  }
}

recur(144)
