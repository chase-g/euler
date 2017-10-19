/**
 * Project Euler 
 * Problem 26
 * "Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part."
 * October 18, 2017
 */
object euler28 {
  def main(args: Array[String]): Unit = {
    def makeList(num: Int, current: List[Int]): List[Int] = {
      val amendedList = num :: current
      if(num < 1001 * 1001) makeList(num + 1, amendedList)
      else amendedList
    }
    
    val theList = makeList(1, List())
    
    def stepThrough(index: Int, step: Int, spiral: List[Int], total: Int): Int = {
      val answer = spiral(index) + spiral(index - step) + spiral(index - 2 * step) + spiral(index - 3 * step)
      val nextIndex = (index - 3 * step) - (step + 2)
      if(nextIndex > 0) stepThrough(nextIndex, step + 2, spiral, total + answer)
      else{
        println(total + answer)
        return total + answer
      }
    }
    stepThrough(theList.length - 3, 2, theList, 1)
  }
}
