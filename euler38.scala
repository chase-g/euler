/**
 * Project Euler 
 * Problem 38
 * "What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?"
 * October 8, 2017
 */
object euler38 {
  def main(args: Array[String]): Unit = {
    
    def check(num: Int): String = {
      val first = (num * 1).toString
      val second = (num * 2).toString
      val third = (num * 3).toString
      val fourth = (num * 4).toString
      val fifth = (num * 5).toString
      var answer = first + second
      if(answer.length > 8) return answer
      else if((answer + third).length > 8){
        answer = answer + third
        return answer
      }
      else if((answer + third + fourth).length > 8){
        answer = answer + third + fourth
        return answer
      }
      else if((first + second + third + fourth + fifth).length > 8){
        answer = answer + third + fourth + fifth 
        return answer
      }
      else return "0"
    }

  def allNums(str: String): Boolean = {
    if(str.length < 10) { 
      val slice = str.toList.map(_.toString).map(_.toInt)
      val digits = (1 to 9).toList
      digits.forall(slice.contains) && slice.length < 10
      } else { return false }
  }

  def highest(current: Int, record: Int): Int = {
    val strAnswer = check(current)
    if(current > 1000000) return record
    if(strAnswer.length < 10 && strAnswer.toInt > record && allNums(strAnswer)) {
      highest(current + 1, strAnswer.toInt)
    } else{
      highest(current + 1, record)
    }
  }
  println("Answer: " + highest(9,0))
    
  }
}

