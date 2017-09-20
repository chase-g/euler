/*
 * Project Euler
 * Problem 23
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
*/
object euler23{
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

var abundant = new ListBuffer[Int]();
var answerList = new ListBuffer[Int]();
var abundantSums = new ListBuffer[Int]();
//loop through all numbers up to 28123
for(i <- 1 to 20161){
//create list
  var factors = new ListBuffer[Int]();
  for(n <- 1 to (i-1)){
//if n is a factor, create list buffer with divisor prepended to prior list
    if(i % n == 0){factors += n};
}
  //create counter to check abundance
  var sum: Int = 0;
  //sum the factors
  for(t <- factors){sum += t}
  //check whether the factor sum exceeds the numbers
  if(sum > i){abundant += i}
//create a counter to check whether any currently existing abundant numbers
  //can be added up to the number by subtracting each from the number and checking
  //whether the difference is included in the abundant numbers list
  //break from the loop when one combination is found to avoid duplicative effort
  var counter: Int = 0;
  //breakable loop
  breakable{
  for(x <- abundant){
    //difference
    var checkable = i - x;
 //if difference is contained in the abundant numbers, increment counter and break
    if(abundant.contains(checkable)){counter += 1; break}
  }
  }
//if counter has not been incremented after loop completes or breaks,
  //then add number to answer list
  if(counter == 0){answerList += i}
}
//get the sum of all values in the answer list
var answer: Int = 0;
for(i <- answerList){
  answer += i;
}
println(answer)
}
