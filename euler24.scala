/*
 * Project Euler
 * Problem 24
 * "What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?"
*/
val t0 = System.nanoTime()
import scala.collection.mutable.ListBuffer
//Create list buffer to hold the permutation numbers
var perm = new ListBuffer[Int]()
for(t <- 0 to 9) perm += t
println(perm)
  var stepone = 0
  var steptwo = 0
  var stepfour = 0
//create a loop, starting from 2 because the list is generated with the first permutation
for( i<- 2 to 1000000){
//Loop over each number
  for(n <- 0 to 9){
    //step one: get largest index which has a value smaller than that of the subsequent index
    if(n != 9 && perm(n) < perm(n + 1) && n > stepone){
      stepone = n; 
    }
    //step two: get the largest index with a value larger than that identified in step one
  else if(n > stepone && perm(n) > perm(stepone)){
      steptwo = n
    }
  }
//save index above step one for use in step four
    stepfour = stepone + 1
//step three: swap values identified in step one and step two
    var saved1 = perm(stepone)
    var saved2 = perm(steptwo)
    perm(stepone) = saved2
    perm(steptwo) = saved1
//step four: reverse all values including and above the value identified in step four variable
    var frontend = perm.take(stepfour)
    var backend = perm.drop(stepfour).reverse
    perm = frontend ++ backend
//reset variables  
    stepone = 0
    steptwo = 0
    stepfour = 0
}
//print digits of the millionth permutation
print("Answer: "); for(i <- perm) print(i)
val t1 = System.nanoTime()
println()
println("Elapsed time: " + (t1 - t0) + "ns")


