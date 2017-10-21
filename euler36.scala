/**
 * Project Euler 
 * Problem 36
 * "Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2."
 * October 18, 2017
 */
object euler36 {
  def main(args: Array[String]): Unit = {
    def palindrome(num: Int, running: List[Int]): List[Int] = {
      if(num == 1000000) return running
      else{
        val binary = num.toBinaryString
        val decimal = num.toString
        val revBinary = binary.reverse
        val revDecimal = decimal.reverse
        if(binary == revBinary && decimal == revDecimal) {
          val updated: List[Int] = num :: running
          palindrome(num + 1, updated)
        }
        else palindrome(num + 1, running)
      }
    }
    val answer = palindrome(0, List()).reduceLeft(_ + _)
    println(answer)
  }
}
