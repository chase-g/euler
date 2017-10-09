/*
 * Project Euler
 * Problem 23
 * "Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers."
*/
    val t0 = System.nanoTime()
    //function to find factors
    def factors(num: Int) = {
        val factors = for(i <- 1 to (num - 1) if num % i == 0) yield { i }
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
      if(factorSum > num) { abundance(num + 1, num :: currentList) }
      else { abundance(num + 1, currentList) }
      } else return currentList
    }
    //all abundant numbers
    val abundant = abundance(2, List())
    //function to create a list of numbers which cannot be formed as the sum of two abundant numbers
    def notSumAbundant(num: Int, currentList: List[Int]): List[Int] = {
      val abundantList = abundant.filter(_ < num)
      if(num <= 20161) { 
        if(!abundantList.exists(x => abundantList.contains(num - x))) { notSumAbundant(num + 1, num :: currentList) }
        else { notSumAbundant(num + 1, currentList) }
     }  else return currentList
    }

    val answerList = notSumAbundant(1, List())
    val answer = answerList.reduceLeft(_ + _)
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000000 + " seconds")
    println(answer)
