/**
 * Project Euler
 * Problem 26
 * "Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part."
 * July 13, 2018
 */
object euler26 {
  def main(args: Array[String]): Unit = {

    def longDivLen(num: Int, den: Int): Int = {
      import collection.mutable.Map
      val locations: collection.mutable.Map[Int, Int] = Map()

      def util(num: Int, den: Int, digits: List[Int], count: Int): List[Int] = {
        if (num != 0) {
          val digit = num / den
          val remainder: Int = num % den
          if (locations.contains(remainder)) digits
          else {
            locations += (remainder -> count)
            util(remainder * 10, den, digit :: digits, count + 1)
          }
        } else List(0)
      }
      util(num, den, List(), 0).length
    }

    def checkNums(current: Int, peak: Int, record: (Int, Int)): Int = {
      if (current < peak) {
        val reps = longDivLen(1, current)
        if (reps > record._2) checkNums(current + 1, peak, (current, reps))
        else checkNums(current + 1, peak, record)
      } else record._1
    }
    println(checkNums(2, 1000, (0, 0)))
  }
}
