/**
 * Project Euler 
 * Problem 27
 * "Considering quadratics of the form:
 * n^2 + an + b, where |a|<1000 and |b|≤1000
 * where where |n| is the modulus/absolute value of n
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of nn, starting with n = 0."
 * October 31, 2017
 */

object euler27 {
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
    
    def quadratic(a: Int, b: Int, n: Int): Int = {
      val answer = Math.abs(Math.pow(n, 2) + (a * n) + b)
      answer.toInt
    }
    
    def applyWhilePrime(a: Int, b: Int, n: Int, record: Int): Int = {
      val answer = quadratic(a, b, n)
      if(answer > 0 && answer < notPrime.length && notPrime(answer) == false) applyWhilePrime(a, b, n + 1, record + 1)
      else return record
    }
    
    def quadraticSeries(a: Int, b: Int, aholder: Int, bholder: Int, record: Int): Int = {
      if (a == 1001 && b == 1000){
        return aholder * bholder
      }
      else {
        val primesLength = applyWhilePrime(a, b, 0, 0)
        if(primesLength <= record && b < 1000) quadraticSeries(a, b + 1, aholder, bholder, record)
        else if(primesLength <= record && b == 1000) quadraticSeries(a + 1, -1000, aholder, bholder, record)
        else if(primesLength > record && b < 1000) quadraticSeries(a, b + 1, a, b, primesLength)
        else quadraticSeries(a + 1, -1000, a, b, primesLength)
      }
    }
    
    val answer = quadraticSeries(-1000, -1000, 0, 0, 0)
    println("Answer: " + answer)
  }
}