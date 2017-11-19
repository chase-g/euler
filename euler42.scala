/**
 * Project Euler
 * Problem 42
 * Project Euler 42
 * "By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value."
 * "Using words.txt, a 16K text file containing nearly two-thousand common English words, how many are triangle words?"
 * @version November 18, 2017
 * @author chase-g
 */
object euler42 {
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val wordsprelim = Source.fromFile("/Users/chasegorland/Downloads/p042_words.txt").getLines.toArray.map(x => x.split(","))
    val words = wordsprelim(0).map(y => y.filter(x => x != '\"'))
    val alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    def makeLetterMap(current: Int, acc: Map[Char, Int]): Map[Char, Int] = {
      val letter = alphabet(current)
      val update = acc + (letter -> current)
      if (current < alphabet.length - 1) makeLetterMap(current + 1, update)
      else update
    }
    val letterMap = makeLetterMap(1, Map(): Map[Char, Int])

    def triangular(x: Int): Boolean = {
      if ((Math.sqrt(8.0 * x + 1.0) - 1.0) / 2.0 % 1.0 == 0.0) true
      else false
    }

    def iterate(wordIndex: Int, acc: List[Int]): List[Int] = {
      val sum = (for (i <- words(wordIndex)) yield { letterMap(i) }).reduceLeft(_ + _)
      if (wordIndex == words.length - 1) acc
      else if (triangular(sum)) iterate(wordIndex + 1, sum :: acc)
      else iterate(wordIndex + 1, acc)
    }
    val answer = iterate(0, List()).length
  }
}