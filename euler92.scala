/*
 * Project Euler
 * Problem 92
 * "A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before...
 * How many starting numbers below ten million will arrive at 89?"
 * @version December 16, 2017
 * @author chase-g
*/
object euler92 {
  def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime()
    
    val blank = new Array[Boolean](10000000)
    def chain(number: Int, links: List[Int] = List()): Unit = {
      val next = number.toString.map(i => (i - '0') * (i - '0')).sum
      if (next == 89) for (i <- number :: links; n <- i.toString.permutations.toList.map(_.toInt)) blank(n) = true
      else if (next == 1) ()
      else {
        chain(next, number :: links)
      }
    }

    def checkAll(num: Int, counter: Int = 0): Unit = {
      if (num == 1) println("Answer: " + counter)
      else if (blank(num) == true) checkAll(num - 1, counter + 1)
      else {
        chain(num)
        if (blank(num) == true) checkAll(num - 1, counter + 1)
        else checkAll(num - 1, counter)
      }
    }
    checkAll(9999999)
    
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + " ns")
  }
}
