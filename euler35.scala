/**
 * Project Euler 
 * Problem 35
 * "How many circular primes are there below one million?"
 * October 8, 2017
 */
object euler35 {
  def main(args: Array[String]): Unit = {

    def notPrimes() = {  // Sieve of Erastosthenes 
      val notPrime = new Array[Boolean](1000000)  // Create array of booleans, each initially false
      notPrime(0) = true
      notPrime(1) = true
      for(i <- 2 until notPrime.length){  // For each index in the array of booleans
      // set all the multiples of each prime number to true
      if(notPrime(i) == false) {
      for(n <- i until notPrime.length by i){
        if(n != i){
          notPrime(n) = true
        }
      }
    }
  }
notPrime
}
  val notPrime = notPrimes() //holds array of non prime numbers
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

  def circularCheck(num: Int) = {  // Outputs whether or every circular permutation of a number is prime
    var output = true
    val numStr = num.toString
    // Call circle function to get a list of each circular permutation of the number
    val circularPermutations = circle(List(numStr), numStr).map(_.toInt)
    // For each number in that list, format to Int and then check whether it is prime
      for(e <- circularPermutations) {
        val current = e.toInt
        // Set output to false if any of the values are not prime
        if(notPrime(current) == true) output = false
        } 
    output
  }

  def findAnswer() = {
    val answerArray = new Array[Int](1000000)  // Stores answers
    for(t <- 2 until 1000000) {  // For every number in range below 1,000,000, call circularCheck
      if(notPrime(t) == false){
      if(circularCheck(t)) answerArray(t) = t  // If circularCheck returns true on a number, add it to answerArray
    }
  }
  // Retain only distinct values and filter out zeros, save length
  val circularPrimes = answerArray.distinct.filter(_ > 0)
  val answer = circularPrimes.length
  println(answer)
  answer
  }
  findAnswer()
  }
}
