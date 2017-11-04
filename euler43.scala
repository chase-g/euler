/**
 * Project Euler 
 * Problem 43
 * "d2d3d4=406 is divisible by 2, d3d4d5=063 is divisible by 3, d4d5d6=635 is divisible by 5, d5d6d7=357 is divisible by 7
 * d6d7d8=572 is divisible by 11, d7d8d9=728 is divisible by 13, d8d9d10=289 is divisible by 17
 * Find the sum of all 0 to 9 pandigital numbers with this property."
 * November 2, 2017
 */

object euler43{
  def main(args: Array[String]): Unit = {
  
    val primes = List(2,3,5,7,11,13,17)
    
    def div(sequence: String, sliceStart: Int, prime: Int): Boolean = {
      if(sliceStart == 8) return true
      if(sequence.slice(sliceStart, sliceStart + 3).toLong % primes(prime) == 0) div(sequence, sliceStart + 1, prime + 1)
      else return false
    }
    
    var total: Long = 0
    
    def check(item: String): Unit = {
        if(div(item, 1, 0)) total = total + item.toLong
    }
    
    for(i <- List(0,1,2,3,4,5,6,7,8,9).permutations) check(i.mkString)
    println("Answer: " + total)
   
  }
}