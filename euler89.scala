/**
 * Project Euler
 * Problem 89
 * "The 11K text file, roman.txt, contains one thousand numbers written in valid, but not necessarily minimal, Roman numerals.
 * Find the number of characters saved by writing each of these in their minimal form."
 * December 10, 2017
 */
object euler89 {
  def main(args: Array[String]): Unit = {
    val roman = Map(
      "I" -> 1,
      "IV" -> 4,
      "V" -> 5,
      "IX" -> 9,
      "X" -> 10,
      "XL" -> 40,
      "L" -> 50,
      "XC" -> 90,
      "C" -> 100,
      "CD" -> 400,
      "D" -> 500,
      "CM" -> 900,
      "M" -> 1000)
    val numToRoman = for ((k, v) <- roman) yield (v, k)
    val comp = (for ((k, v) <- numToRoman) yield k).toArray.sortWith(_ > _)
    val compLen = comp.length

    def translate(romNum: String, index: Int = 0, acc: Int = 0): Int = {
      if (index == romNum.length) acc
      else {
        val arabNum = roman(romNum(index).toString)
        if (index == romNum.length - 1) translate(romNum, index + 1, acc + arabNum)
        else if (arabNum >= roman(romNum(index + 1).toString)) translate(romNum, index + 1, acc + arabNum)
        else translate(romNum, index + 1, acc - arabNum)
      }
    }

    def toRoman(num: Int, acc: String = ""): String = {
      def compare(n: Int, ind: Int = 0): Int = {
        if (n <= 0 || ind == compLen) 0
        else if (n >= comp(ind)) comp(ind)
        else compare(n, ind + 1)
      }
      val nextNum = compare(num)
      if (nextNum == 0) acc.reverse
      else toRoman(num - nextNum, numToRoman(nextNum) + acc)
    }

    import scala.io.Source
    val numerals = Source.fromFile("/Users/chasegorland/Downloads/p089_roman.txt").getLines.toList.map(_.toString)
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
