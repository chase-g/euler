/**
 * Project Euler
 * Problem 89
 * "The 11K text file, roman.txt, contains one thousand numbers written in valid, but not necessarily minimal, Roman numerals. 
 * Find the number of characters saved by writing each of these in their minimal form."
 * December 10, 2017
 */
object euler89 {
  def main(args: Array[String]): Unit = {
    val roman = Map('I' -> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' -> 500, 'M' -> 1000)

    def translate(number: List[Char], index: Int = 0, acc: Int = 0): Int = {
      if(index == number.length) acc
      else if(index == number.length - 1) translate(number, index + 1, acc + roman(number(index)))    
      else if(roman(number(index)) >= roman(number(index + 1))) translate(number, index + 1, acc + roman(number(index)))
      else translate(number, index + 1, acc - roman(number(index)))
    }
    
    def toRoman(num: Int, acc: List[Char] = List()): List[Char] = {
      if(num - 1000 >= 0) toRoman(num - 1000, 'M' :: acc)
      else if(num - 900 >= 0) toRoman(num - 900, 'M' :: 'C' :: acc)
      else if(num - 500 >= 0) toRoman(num - 500, 'D' :: acc)
      else if(num - 400 >= 0) toRoman(num - 400, 'D' :: 'C' :: acc)
      else if(num - 100 >= 0) toRoman(num - 100, 'C' :: acc)
      else if(num - 90 >= 0) toRoman(num - 90, 'C' :: 'X' :: acc)
      else if(num - 50 >= 0) toRoman(num - 50, 'L' :: acc)
      else if(num - 40 >= 0) toRoman(num - 40, 'L' :: 'X' :: acc)
      else if(num - 10 >= 0) toRoman(num - 10, 'X' :: acc)
      else if(num - 9 >= 0) toRoman(num - 9, 'X' :: 'I' :: acc)
      else if(num - 5 >= 0) toRoman(num - 5, 'V' :: acc)
      else if(num - 4 >= 0) toRoman(num - 4, 'V' :: 'I' :: acc)
      else if(num - 1 >= 0) toRoman(num - 1, 'I' :: acc)
      else acc.reverse
    }
    
    import scala.io.Source
    val numerals = Source.fromFile("/Users/chasegorland/Downloads/p089_roman.txt").getLines.toList.map(_.toList)
    val numbers = numerals.map(x => translate(x))
    val answers = numerals.map(x => translate(x)).map(x => toRoman(x).mkString)
    
    val oldDigitCount = (for {
      i <- numerals
    } yield i.length).reduceLeft(_ + _)
    
    val newDigitCount = (for {
      n <- answers
    } yield n.length).reduceLeft(_ + _)
    
    val answer = oldDigitCount - newDigitCount 
    println("Answer: " + answer)  
  }
}
