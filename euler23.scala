/*
 * Project Euler
 * Problem 23
 * "Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers."
*/
object euler23 {
  def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime()
    //function to find factors
    def factors(num: Int) = {
        val max = (num / 2) + 1
        val factors = for(i <- 1 to max if num % i == 0) yield { i }
        factors
    }
    //function to find sums
    def sumList(numList: IndexedSeq[Int]) = {
      val sum = numList.reduceLeft( _ + _ )
      sum
    }
    //function to recursively form list of abundant numbers
    def abundance(num: Int, currentList: List[Int]): List[Int] = {
      if(num <= 20161){
      val numFactors = factors(num)
      val factorSum = sumList(numFactors)
      //if the number is abundant, prepend it to the running list of abundant numbers in the function call
      if(factorSum > num) { abundance(num + 1, num :: currentList) }
      else { abundance(num + 1, currentList) }
      } else return currentList
    }
    //all abundant numbers
    val abundant = abundance(3, List())
    //function to create a list of numbers which cannot be formed as the sum of two abundant numbers
    def notSumAbundant(num: Int, currentList: List[Int]): List[Int] = {
      val abundantList = abundant.filter(_ < num)
      if(num <= 20161) { 
        //if it cannot be made as the sum of abundant numbers, prepend it to the current list when calling on the next number
        if(!abundantList.exists(x => abundantList.contains(num - x))) { 
          notSumAbundant(num + 1, num :: currentList) 
          } else { notSumAbundant(num + 1, currentList) }
     }  else return currentList
    }
    //store the values and then sum them
    val answerList = notSumAbundant(1, List())
    val answer = answerList.reduceLeft(_ + _)
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000000 + " seconds")
    println(answer)
  }
}
