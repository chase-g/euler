/**
 * Project Euler
 * Problem 79
 * Passcode derivation
 * "Given that the three characters are always asked for in order, analyse the file so as to determine the shortest possible secret passcode of unknown length."
 * December 5, 2017
 */
object euler79 {
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val samples = Source.fromFile("p079_keylog.txt").getLines.toList.flatMap(x => x.split("\n").map(_.toInt)).map(_.toString)

    def checkFirst(numChar: Char, arr: List[String] = samples): Boolean = {
      val answer = for {
        i <- arr
        if(i.tail.contains(numChar))
      } yield numChar
      if (answer.isEmpty) true
      else false
    }

    def iterate(order: List[Char] = List(), arr: List[String] = samples): String = {
      if(arr.isEmpty) order.reverse.mkString
      else {
        val options = (for {
          i <- arr
        } yield i.head).distinct
        val answer = for {
          n <- options
          if (checkFirst(n, arr))
        } yield n
        val ans = answer.head
        val remaining = arr.map(_.toSet.filterNot(x => x == ans).mkString).filter(x => x != "")
        iterate(answer.head :: order, remaining)
      }
    }
 
    println("Shortest possible password: " + iterate())
  }
}