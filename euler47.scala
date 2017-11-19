/**
 * Project Euler
 * Problem 47
 * "Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?"
 * @version November 19, 2017
 * @author chase-g
 */
object euler47 {
  def main(args: Array[String]): Unit = {
    def sieve() = { // Sieve of Erastosthenes
      val notPrime = new Array[Boolean](1000000) // Create array of booleans, each initially false
      notPrime(0) = true
      notPrime(1) = true
      for (i <- 2 until notPrime.length) { // For each index in the array of booleans
        // set all the multiples of each prime number to true
        if (notPrime(i) == false) {
          for (n <- i until notPrime.length by i) {
            if (n != i) {
              notPrime(n) = true
            }
          }
        }
      }
      notPrime
    }

    val notPrime = sieve()
    def makePrimes(index: Int = notPrime.length - 1, acc: List[Int] = List()): List[Int] = {
      if (index == 0) acc
      else if (notPrime(index) == false) makePrimes(index - 1, index :: acc)
      else makePrimes(index - 1, acc)
    }
    val primes = makePrimes().toArray

    def primeFactors(num: Int, index: Int = 0, acc: List[Int] = List()): List[Int] = {
      if (num < (primes(index) * 2 - 1)) acc
      else if (num % primes(index) == 0) primeFactors(num, index + 1, primes(index) :: acc)
      else primeFactors(num, index + 1, acc)
    }

    def runThrough(num: Int = 100, acc: List[Int] = List()): List[Int] = {
      if (acc.length == 4) acc
      else if (primeFactors(num).length == 4) runThrough(num + 1, num :: acc)
      else runThrough(num + 1, List())
    }

    val group = runThrough()
    val answer = group(group.length - 1)
    println("Answer: " + answer)
  }
}