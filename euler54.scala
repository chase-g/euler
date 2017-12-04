/**
 * Project Euler
 * Problem 54
 * "How many hands does Player 1 win?"
 * November 28, 2017
 */

object euler54 {
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val hands = Source.fromFile("p054_poker.txt").getLines.toArray.map(x => x.split(" "))

    def numConvert(card: String): Int = {
      if (card == "T") 10
      else if (card == "J") 11
      else if (card == "Q") 12
      else if (card == "K") 13
      else if (card == "A") 14
      else card.toInt
    }

    def highCard(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      val hn1 = hand1.max
      val hn2 = hand2.max
      if (hn1 > hn2) 1
      else if (hn2 > hn1) 2
      else {
        val update1 = hand1.filter(x => x != hn1)
        val update2 = hand2.filter(x => x != hn2)
        highCard(update1, update2, Array(""), Array(""))
      }
    }

    def royalFlush(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      if (hand1.intersect(Array(14, 13, 12, 11, 10)).length == 5) {
        if (suit1.filter(x => x == suit1(0)).length == 5) return 1
      }
      if (hand2.intersect(Array(14, 13, 12, 11, 10)).length == 5) {
        if (suit2.filter(x => x == suit2(0)).length == 5) return 2
        else 0
      } else return 0
    }

    def fullHouse(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      val h1 = hand1.sorted
      val h2 = hand2.sorted
      if (h1.count(h1(0) == _) + h1.count(h1(4) == _) == 5) {
        if (h2.count(h2(0) == _) + h2.count(h2(4) == _) != 5) 1
        else highCard(Array(h1.groupBy(identity).maxBy(_._2.size)._1), Array(h2.groupBy(identity).maxBy(_._2.size)._1), Array(""), Array(""))
      } else if (h2.count(h2(0) == _) + h2.count(h2(4) == _) == 5) 2
      else 0
    }

    def straight(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      val h1 = hand1.sorted
      val h2 = hand2.sorted
      def isConsecutive(x: Array[Int], acc: Int = 0): Boolean = {
        if (x.length > 1) {
          if (x(1) - x(0) == 1) isConsecutive(x.tail)
          else false
        } else true
      }
      if (isConsecutive(h1) && !isConsecutive(h2)) 1
      else if (isConsecutive(h2) && !isConsecutive(h1)) 2
      else if (isConsecutive(h1) && isConsecutive(h2)) highCard(hand1, hand2, Array(""), Array(""))
      else 0
    }

    def flush(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      if (suit1.filter(x => x == suit1(0)).length == 5) return 1
      else if (suit2.filter(x => x == suit2(0)).length == 5) return 2
      else 0
    }

    def onePair(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      val pair1 = hand1.filter(x => hand1.count(x == _) > 1)
      val pair2 = hand2.filter(x => hand2.count(x == _) > 1)
      if (pair2.isEmpty && !pair1.isEmpty) 1
      else if (pair1.isEmpty && !pair2.isEmpty) 2
      else if (pair1.isEmpty && pair2.isEmpty) 0
      else if (pair1(0) < pair2(0)) 2
      else 1
    }

    def threeKind(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      val pair1 = hand1.filter(x => hand1.count(x == _) > 2)
      val pair2 = hand2.filter(x => hand2.count(x == _) > 2)
      if (pair2.isEmpty && !pair1.isEmpty) 1
      else if (pair1.isEmpty && !pair2.isEmpty) 2
      else if (pair1.isEmpty && pair2.isEmpty) 0
      else if (pair1(0) < pair2(0)) 2
      else 1
    }

    def fourKind(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      val pair1 = hand1.filter(x => hand1.count(x == _) > 3)
      val pair2 = hand2.filter(x => hand2.count(x == _) > 3)
      if (pair2.isEmpty && !pair1.isEmpty) 1
      else if (pair1.isEmpty && !pair2.isEmpty) 2
      else if (pair1.isEmpty && pair2.isEmpty) 0
      else if (pair1(0) < pair2(0)) 2
      else 1
    }

    def twoPairs(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      val h1 = hand1.sorted
      val h2 = hand2.sorted
      val pair1 = h1.count(h1(1) == _) == 2 && h1.count(h1(3) == _) == 2
      val pair2 = h2.count(h2(1) == _) == 2 && h2.count(h2(3) == _) == 2
      if (pair1 && !pair2) 1
      else if (pair2 && !pair1) 2
      else if (pair1 && pair2) highCard(h1, h2, Array(""), Array(""))
      else 0
    }

    def straightFlush(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      //There can only be one winner from each of these function calls
      //and so ties do not need to be handled
      val straightWinner = straight(hand1, hand2, Array(""), Array(""))
      val flushWinner = flush(Array(0), Array(0), suit1, suit2)
      if (straightWinner == 1 && flushWinner == 1) 1
      else if (straightWinner == 2 && flushWinner == 2) 2
      else 0
    }

    val testList = List(royalFlush _, straightFlush _, fourKind _,
      fullHouse _, flush _, straight _, threeKind _,
      twoPairs _, onePair _, highCard _)

    //iterate through each hand; for that hand, iterate through each test function until finding a winner 
    def iterator(index: Int = 0, acc: Int = 0, function: Int = 0, handsArray: Array[Array[String]] = hands): Int = {
      if (index == handsArray.length - 1) acc
      else {
        //set the value of each hand's numbers and suits
        val hn1 = handsArray(index).slice(0, 5).map(x => x(0)).map(_.toString).map(x => numConvert(x))
        val hn2 = handsArray(index).slice(5, 11).map(x => x(0)).map(_.toString).map(x => numConvert(x))
        val hs1 = handsArray(index).slice(0, 5).map(_.toString).map(x => x(1).toString)
        val hs2 = handsArray(index).slice(5, 11).map(_.toString).map(x => x(1).toString)
        //check whether the current function finds a winner
        val current = testList(function)(hn1, hn2, hs1, hs2)
        //if not, call recursively with the next function in the list
        if (current == 0) iterator(index, acc, function + 1)
        //if player one wins, add to the accumulator and in either case iterate to the next hand
        else if (current == 1) iterator(index + 1, acc + 1, 0)
        else iterator(index + 1, acc, 0)
      }
    }

    val answer = iterator()
    println("Player one won " + answer + " hands")

  }
}
