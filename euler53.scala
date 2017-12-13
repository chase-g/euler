/*
 * Project Euler
 * Problem 53
 * "How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?"
*/
object euler53{
  def main(args: Array[String]): Unit = {
    
    def fact(x: BigInt, acc: BigInt = 1): BigInt = if(x == 1) acc else fact(x - 1, acc * x)

    def comboCount(n: BigInt, r: BigInt): BigInt = fact(n) / (fact(r) * fact(n - r))
    
    def iterate(n: Int, r: Int, acc: List[BigInt] = List()): List[BigInt] = {
      val answer = comboCount(n, r)
      if(answer > 1000000) iterate(n, r + 1, answer :: acc)
      else if(r < 10) iterate(n, r + 1, acc)
      else acc
    }
    
    val answer = (for {
      n <- 23 to 100
      if(iterate(n, 4).length > 0)
    } yield iterate(n, 4)).flatten
    
    println("Answer: " + answer.length)
  }
}