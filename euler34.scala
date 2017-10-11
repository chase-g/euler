/**
 * Project Euler 
 * Problem 34
 * "Find the sum of all numbers which are equal to the sum of the factorial of their digits."
 * October 7, 2017
 */
object euler34 {
  def main(args: Array[String]): Unit = {
    // function which finds the sum of the factorials of each digit of that number
    def getFactorialSum(number: Int): Int = {
    // change number to string, then to a list of characters, 
    // then change each character to a String and then an Int
    val sliced = number.toString.toList.map(_.toString).map(_.toInt)
  
    // function to find each factorial
    def factorial(num: Int): Int = {
      if (num == 0) return 1
      else return num * factorial(num - 1)
    }
  
    // for each digit, yield its factorial 
    val subanswer = for(i <- sliced) yield {factorial(i)} 
    // store and return the sum of those factorials
    val getSum = subanswer.reduceLeft( _ + _ )
    getSum
  }

  // applies the above function to each number between a range, 
  // checks whether the function answer is equal to the number, and if so it yields that number
  val factorials = for(n <- 3 to 100000 if getFactorialSum(n) == n) yield { n }
  // get the sum of the the collected numbers
  val answer = factorials.reduceLeft( _ + _ )
  println(answer)
  }
}
