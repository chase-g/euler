/**
 * Project Euler 
 * Problem 37
 * "Find the sum of the only eleven primes that are both truncatable from left to right and right to left."
 * October 24, 2017
 */
object euler37 {
  def main(args: Array[String]): Unit = {
    def notPrimes() = {  // Sieve of Erastosthenes 
      val notPrime = new Array[Boolean](999999)  // Create array of booleans, each initially false
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
    
    val notPrime = notPrimes()

      def truncate(num: Int): Boolean = {
        if(num.toString.length == 1 && notPrime(num) == false) true
        else if(notPrime(num) == false) {
          val numStr = num.toString
          val truncated = numStr.slice(0, numStr.length - 1)
          val trunum = truncated.toInt
          truncate(trunum)
        } else false
      }
    
      def truncateRev(num: Int): Boolean = {
        if(num.toString.length == 1 && notPrime(num) == false) true
        else if(notPrime(num) == false) {
          val numStr = num.toString
          val truncated = numStr.slice(1, numStr.length)
          val trunum = truncated.toInt
          truncateRev(trunum)
        } else false
      }
    def createList(current: List[Int], num: Int): List[Int] = {
      if(current.length == 11) return current
      else if(truncate(num) && truncateRev(num)) createList(num :: current, num + 1)
      else createList(current, num + 1)
    }
    val answerList = createList(List(), 10)
    println(answerList.reduceLeft(_ + _))
  }
}
