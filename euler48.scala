/**
 * Project Euler
 * Problem 48
 * "Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000."
 * November 23, 2017
 */
object euler48 {
  def main(args: Array[String]): Unit = {
    
    def exp(current: BigInt = 1, acc: BigInt = 0): BigInt = {
      if (current == 1001) acc
      else exp(current + 1, current.pow(current.toInt) + acc)
    }
    val power = exp().toString
    println("Answer: " + power.slice(power.length - 10, power.length))
    
  }
}
