/**
 * Project Euler
 * Problem 52
 * "Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits."
 * @version November 19, 2017
 * @author chase-g
 */
object euler52 {
  def main(args: Array[String]): Unit = {
    
    //recursively checks whether one array contains all the values of the other
    def includesAll(target: Array[Int], included: Array[Int], index: Int = 0): Boolean = {
      if(index == included.length - 1) true
      else if(target.contains(included(index))) includesAll(target, included, index + 1)
      //short circuits to false at first test failure
      else false
    }
    
    def multiples(num: Int): Array[Int] = { //generates an array of multiples
      val multiples = Array(num, num * 2, num * 3, num * 4, num * 5, num * 6)
      multiples
    }
    
    def getDigits(num: Int): Array[Int] = { //splits digits of each multiple into an Array[Int]
      val digits = num.toString.split("").map(_.toInt)
      digits
    }
    
    //recursively iterates among the current number and each multiple, 
    //checking whether all the digits match between each multiple
    def innerIterate(arr: Array[Array[Int]], index: Int = 1): Boolean = {
      if(index == arr.length - 1) {
        if(arr(0).length < 2) false //prevent automatically resolving to true for single digit options
        else true
      }
      else if(includesAll(arr(0), arr(index))) innerIterate(arr, index + 1)
      //short circuits to false at first test failure
      else false
    }
    
    //recursively iterates through each possible number
    def iterate(num: Int): Int = {
      val mults = multiples(num).map(x => getDigits(x))
      if(innerIterate(mults)) num
      else iterate(num + 1)
    }
    
    println("Answer: " + iterate(0))
  }
}