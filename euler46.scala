/**
 * Project Euler 
 * Problem 46
 * "What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?"
 * October 31, 2017
 */
object euler46 {
  def main(args: Array[String]): Unit = {
    
    def sieve() = {  // Sieve of Erastosthenes 
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
    
    val notPrime = sieve()
    
    def getSquares(num: Int, currentList: List[Int]): List[Int] = {
      val square = num * num
      val updatedList = square :: currentList
      if(num == 1) return updatedList
      else getSquares(num - 1, updatedList)
    }
    
    val squares = getSquares(100, List())
    
    def squaresTilPrime(number: Int, index: Int): Boolean = {
      val square = squares(index)
      if(square * 2 < number) {
        val checkPrime = number - (square * 2) 
        if(notPrime(checkPrime) == false) return true
        else squaresTilPrime(number, index + 1)
      } else return false
    }
    
    
    def recurCheck(number: Int): Int = {
      if(number % 2 == 1 && notPrime(number)) {
        if(squaresTilPrime(number, 0)) recurCheck(number + 2)
        else return number
      }
      else recurCheck(number + 2)
    }
    println("Answer: " + recurCheck(5))
  }
}
  