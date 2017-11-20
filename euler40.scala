/**
 * Project Euler
 * Problem 40
 * "If dn represents the nth digit of the fractional part, find the value of the following expression.
 * d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000"
 * @version November 19, 2017
 * @author chase-g
 */
object euler40 {
  def main(args: Array[String]): Unit = {
    val str = (1 to 100000).map(_.toString).mkString.map(_.toString).map(_.toInt)
    val answer = str(0) * str(9) * str(99) * str(999) * str(9999) * str(99999)
    println("Answer: " + answer)
  }
}