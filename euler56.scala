/*
 * Project Euler
 * Problem 56
 * "Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?"
 * December 13, 2017
*/
object euler56 {
  def main(args: Array[String]): Unit = {

    def digitSum(x: BigInt): Int = x.toString.toList.map(_.toString).map(_.toInt).reduceLeft(_ + _)

    val solution = for {
      i <- BigInt(2) to BigInt(99)
      n <- 2 to 99
    } yield digitSum(i.pow(n))
    
    println("Answer: " + solution.max)
  }
}