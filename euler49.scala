/**
 * Project Euler 
 * Problem 49
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 * November 4, 2017
 */
object euler49 {
  def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime()
    def sieve() = {  // Sieve of Erastosthenes 
      val notPrime = new Array[Boolean](9999999)  // Create array of booleans, each initially false
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
    val primes = new Array[Int](1000000)
    def makePrimes(item: Int = 0, next: Int = 0): Unit = {
      if(item == notPrime.length) print("")
      else if(notPrime(item) == false) {
        primes(next) = item
        makePrimes(item + 1, next + 1)
      } 
      else makePrimes(item + 1, next)
    } 
    makePrimes()
    
    def checkPerms(perms: Array[Int], index: Int = 0, acc: List[Int] = List()): List[Int] = {
      if(index < perms.length){
        if(notPrime(perms(index)) == false) checkPerms(perms, index + 1, perms(index) :: acc)
        else checkPerms(perms, index + 1, acc)
      }
      else acc
    }
    
    def checkDistance(head: Int, perms: List[Int], index: Int = 1): List[Int] = {
     /* if(index == perms.length) true
      else if(Math.abs(perms(index) - perms(index - 1)) == 3330) checkDistance(perms, index + 1)
      else false*/
      val numMed = perms.find(x => Math.abs(x - head) == 3330)
      val numLarge = perms.find(x => Math.abs(x - head) == 6660)
      val trio = List(head, numMed.getOrElse(0), numLarge.getOrElse(0))
      trio
    } 
    
    def iterate(item: Int = 0): String = {
      println(primes(item))
      if(primes(item) > 999){   
        val numbers = primes(item).toString.permutations.toArray.map(_.toInt)
          val ans = checkPerms(numbers).sorted
          if(!checkDistance(primes(item), ans).contains(0) && primes(item) != 1487) {
            checkDistance(primes(item), ans).mkString
          }
          else iterate(item + 1)
      } 
      else iterate(item + 1)
    }
    
    println(iterate(0))
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + " nanoseconds")
  }
}
  