/**
 * Project Euler 
 * Problem 32
 * "Find the sum of all products whose multiplicand/multiplier/productperm dentity can be written as a 1 through 9 pandigital."
 * October 30, 2017
 */
object euler32 {
  def main(args: Array[String]): Unit = {
    
    val perm = List(1,2,3,4,5,6,7,8,9).map(_.toString).permutations.toArray
    
    def tester(i: Int, runningList: List[Int]): List[Int] = {
      if(i == perm.length) return runningList
      else{
        val p = perm(i)
        //0000 = 00 * 000
        val product = (p(0) + p(1) + p(2) + p(3)).toInt
        val multiplier = (p(4) + p(5)).toInt
        val multiplicand = (p(6) + p(7) + p(8)).toInt
        if(multiplier * multiplicand == product) {
          val updatedList = product :: runningList
          tester(i + 1, updatedList)
        } else tester(i + 1, runningList)
      }
    }
    
    def quizzer(i: Int, runningList: List[Int]): List[Int] = {
      if(i == perm.length ) return runningList
      else{
        val p = perm(i)
        //0000 = 0000 * 0
        val product = (p(0) + p(1) + p(2) + p(3)).toInt
        val multiplier = (p(4)).toInt
        val multiplicand = (p(5) + p(6) + p(7) + p(8)).toInt
        if(multiplier * multiplicand == product) {
          val updatedList = product :: runningList
          quizzer(i + 1, updatedList)
        } else quizzer(i + 1, runningList)
      }
    }
    
    val answer = (tester(0, List()) ::: quizzer(0, List())).distinct.reduceLeft(_ + _)
    println("Answer: " + answer)
  }
}