/* Euler 51
 * "Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family."
 * August 1, 2018
 */
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

object euler51 {
  def main(args: Array[String]): Unit = {

    //stores primes, so that first the set is checked with inPrimes to see if the number has already been designed prime
    //otherwise, check whether it is prime with isPrime (called inside inPrimes) and add to the set if it is
    val primes = scala.collection.mutable.Set[Int]()
    def inPrimes(num: Int, fact: Int = 2): Boolean = {
      if (primes.contains(num)) true
      else isPrime(num, 2)
    }

    def isPrime(num: Int, fact: Int = 2): Boolean = {
      if (fact > (num / 2) + 1) {
        primes += num
        true
      } else if (num % fact == 0) false
      else isPrime(num, fact + 1)
    }

    def replaceDigits(num: List[Int], digit: Int, indices: List[Int]) = {
      def replaceDig(num: List[Int], digit: Int, indices: List[Int], current: Int = 0, acc: List[Int] = List()): Int = {
        if (num.isEmpty) acc.reverse.mkString.toInt
        else if (!indices.isEmpty && current == indices.head) replaceDig(num.tail, digit, indices.tail, current + 1, digit :: acc)
        else replaceDig(num.tail, digit, indices, current + 1, num.head :: acc)
      }
      replaceDig(num, digit, indices.toList)
    }

    def iterateOpts(num: List[Int], score: Int): Boolean = {
      //iterate through each combination of indices that could be replaced
      //iterate through each 0-9 digit that could be the replacement
      //ie of the number 123 it would attempt (0)(1)(2), (0,1)(0,2)(1,2), etc

      //generates a list of lists of all digit index combinations for this number
      def combos(length: Int): List[List[Int]] = {
        val lst = (0 to length).toList
        (for {
          i <- 1 to length
        } yield lst.combinations(i).toList).toList.flatten
      }

      //checks whether the indices match
      def indicesMatch(ind: List[Int]): Boolean = {
        val m = num(ind.head)
        for (i <- ind) {
          if (num(i) != m) return false
        }
        return true
      }

      def tryCombo(indices: List[Int], len: Int): Boolean = {
        //if the indices to be swapped do not match, it cannot be the answer
        if (!indicesMatch(indices)) return false
        //keep track of hits and misses; short circuit to false once it cannot reach the threshold
        val strikesLimit = 10 - score
        var counter = 0
        var strikes = 0
        //iterate through each digit replacement
        for (i <- 0 to 9) {
          val repNum = replaceDigits(num, i, indices)
          if (inPrimes(repNum) && repNum.toString.length == len) {
            counter = counter + 1
            if (counter == score) return true
          } else if (strikes <= strikesLimit) strikes = strikes + 1
          else return false
        }
        return false
      }

      //iterate through each combination of digit indices
      for (n <- combos(num.length - 1)) {
        val l = num.length
        val ans = tryCombo(n, l)
        if (ans == true) return true
      }
      return false
    }

    //checks primes until the correct one is found, starting with the winner for seven primes
    var found = false
    var i = 56003
    while (!found) {
      if (inPrimes(i)) {
        val primeAsList: List[Int] = i.toString.toList.map(_.toString.toInt)
        if (iterateOpts(primeAsList, 8)) { println("answer: " + i); found = true }
        else i += 1
      } else i += 1
    }

  }
}
