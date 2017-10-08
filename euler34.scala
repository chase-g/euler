/**
 * Project Euler 
 * Problem 34
 * "Find the sum of all numbers which are equal to the sum of the factorial of their digits."
 * October 7, 2017
 */
def recurUp(number: Int): Int = {
  val numStr = number.toString
  val sliced = numStr.toList.map(_.toString).map(_.toInt)
  
  def factorial(num: Int): Int = {
    if (num == 0) return 1
    else return num * factorial(num - 1)
  }
  
  val subanswer = for(i <- sliced) yield {factorial(i)} 
  val getSum = subanswer.reduceLeft( _ + _ )
  getSum
}

val factorials = for(n <- 3 to 100000 if recurUp(n) == n) yield { 
  n
  }
val answer = factorials.reduceLeft( _ + _ )