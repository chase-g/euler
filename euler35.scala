/**
 * Project Euler 
 * Problem 35
 * "How many circular primes are there below one million?"
 * October 7, 2017
 */
import util.control.Breaks._

// Sieve of Erastosthenes 
val notPrime = new Array[Boolean](1000000)
notPrime(0) = true
notPrime(1) = true
for(i <- 2 until notPrime.length){
  if(notPrime(i) == false) {
    for(n <- i until notPrime.length by i){
      if(n != i){
        notPrime(n) = true
      }
    }
  }
}

// Outputs a list of strings of each circular permutation of a given number
def circle(circleList: List[String], start: String, first: Boolean = true): List[String] = {
  if(start == circleList.head && first == false) return circleList.tail 
  else {
  val strList = circleList(0)
  val front = strList.head
  val back = strList.tail
  val cycled = (back + front) :: circleList
  
  circle(cycled, start, false)
  }
}

// Outputs whether or every circular permutation of a number is prime
def circularCheck(num: Int) = {
    var output = true
    val numStr = num.toString
    val circularPermutations = circle(List(numStr), numStr).map(_.toInt)
      for(e <- circularPermutations) {
      val current = e.toString.toInt
      if(notPrime(current) == true) output = false
      } 
    output
}

val answerArray = new Array[Int](1000000)

for(t <- 2 until 1000000) {
  if(notPrime(t) == false){
    if(circularCheck(t)) answerArray(t) = t
  }
}

val circularPrimes = answerArray.distinct.filter(_ > 0)
val answer = circularPrimes.length

