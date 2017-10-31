/**
 * Project Euler 
 * Problem 41
 * "What is the largest n-digit pandigital prime that exists?"
 * October 28, 2017
 */
object euler41 {
  def main(args: Array[String]): Unit = {
    
    def isPrime(num: Int): Boolean = {
      for(i <- 2 to num/2){
        if(num % i == 0) return false
      } 
      return true
    }
    
    def isPandigital(num: Int): Boolean = {
      val numList = num.toString.map(_.asDigit).toList
      val digits = (1 to numList.length).toList
      for(i <- digits) if(!numList.contains(i)) return false
      return true
    }
    
     def topIterator(start: Int): Int = {
      if(isPandigital(start) && isPrime(start)) return start
      else topIterator(start - 2)
    }
     
    println(topIterator(7654321))
  }
}
