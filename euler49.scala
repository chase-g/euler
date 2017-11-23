/**
 * Project Euler
 * Problem 49
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 * November 22, 2017
 */
object euler49 {
  def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime()
    def sieve() = { // Sieve of Erastosthenes
      val notPrime = new Array[Boolean](9999999) // Create array of booleans, each initially false
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

    //filters out permutation values of the base value which are not prime
    //outputs all prime permutations as a list
    def checkPerms(perms: Array[Int], index: Int = 0, acc: List[Int] = List()): List[Int] = {
      if (index < perms.length) {
        if (notPrime(perms(index)) == false) checkPerms(perms, index + 1, perms(index) :: acc)
        else checkPerms(perms, index + 1, acc)
      } else acc
    }

    //finds trio of values which are each 3330 apart starting from the base value
    //and outputs them order, or else outputs zeros if not found
    def checkDistance(base: Int, perms: List[Int], index: Int = 1): List[Int] = {
      val numMed = perms.find(x => Math.abs(x - base) == 3330)
      val numLarge = perms.find(x => Math.abs(x - base) == 6660)
      val trio = List(base, numMed.getOrElse(0), numLarge.getOrElse(0))
      trio
    }

    //iterates over each potential value, calls checker functions, and outputs answer which meets criteria
    def iterate(item: Int = 1001): String = {
      if (notPrime(item) == false) {
        val numbers = item.toString.permutations.toArray.map(_.toInt)
        val ans = checkPerms(numbers).sorted
        if (!checkDistance(item, ans).contains(0) && item != 1487) {
          checkDistance(item, ans).mkString
        } else iterate(item + 2)
      } else iterate(item + 2)
    }

    println(iterate())
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + " nanoseconds")
  }
}
