/**
 * Project Euler 
 * Problem 33
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 * November 4, 2017
 */
object euler33 {
  def main(args: Array[String]): Unit = {
    
    def factors(num: Int, current: Int = 1, runningList: List[Int] = List()): List[Int] = {
      if(num % current == 0) {
        val updatedList = current :: runningList
        if(current <= num) factors(num, current + 1, updatedList)
        else return runningList
      } 
      else if(current <= num) factors(num, current + 1, runningList)
      else return runningList
    }
    
    def gcm(a: Int, b: Int): Int = {  
      val afactors = factors(a)
      val bfactors = factors(b)
      val common = afactors.filter(x => bfactors.contains(x))
      if(common.isEmpty) 0
      else {
        val answer = common.max
        answer
      }
    }
    
    def simplify(num: Int, dem: Int) = {
      val thegcm = gcm(num, dem)
      if(thegcm != 0) {
        val newNum = num / thegcm
        val newDem = dem / thegcm
        val fraction = List(newNum, newDem)
        fraction
      }
      else List(num, dem)
    }
    
    def strSimple(num: Int, dem: Int) = {
      val str = List(num.toString, dem.toString)
      val common = str(0).filter(x => str(1).contains(x))
      val numchanged = num.toString.toList.map(_.toString).filter(_ != common)
      val demchanged = dem.toString.toList.map(_.toString).filter(_ != common)
      val frachanged = List(numchanged.mkString, demchanged.mkString)
      frachanged
    }
    
   def fractions(num: Int = 10, dem: Int = 11, runningList: List[List[Int]] = List()): List[List[Int]] = {
     val frachanged = strSimple(num, dem)
     if(!frachanged.contains("")){
       if(frachanged.map(_.toInt) != List(num, dem)) {
        val simplified = simplify(num, dem)
        val frasimple = simplify(frachanged(0).toInt, frachanged(1).toInt)
        if(simplified == frasimple && num < 100 && dem < 100) {
          val updatedList = List(num, dem) :: runningList
          if(num >= dem - 1) fractions(10, dem + 1, updatedList)
          else if(dem < 100) fractions(num + 1, dem, updatedList)
          else return updatedList
        } 
        else if(num >= dem - 1) fractions(10, dem + 1, runningList)
        else if(dem < 100) fractions(num + 1, dem, runningList)
        else return runningList
        }
       else if(num >= dem - 1) fractions(10, dem + 1, runningList)
      else if(dem < 100) fractions(num + 1, dem, runningList)
      else return runningList
      }
      else if(num >= dem - 1) fractions(10, dem + 1, runningList)
      else if(dem < 100) fractions(num + 1, dem, runningList)
      else return runningList
      }
       
   def process() = {
     val fullList = fractions()
     val notNeeded = List(10,20,30,40,50,60,70,80,90)
     val nextList = fullList.filter(x => !notNeeded.contains(x(0)) && !notNeeded.contains(x(1))) 
     val num = for (i <- nextList) yield i(0)
     val dem = for (i <- nextList) yield i(1)
     val numerator = num.reduceLeft(_ * _)
     val denominator = dem.reduceLeft(_ * _)
     val fraction = simplify(numerator, denominator)
     val answer = fraction(1)
     println("Answer: " + answer)
   }
    process()
    
  }
}
  