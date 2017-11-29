/**
 * Project Euler
 * Problem 48
 * "Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000."
 * November 24, 2017
 */

object euler54 {
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val hands = Source.fromFile("/Users/chasegorland/Downloads/p054_poker.txt").getLines.toArray.map(x => x.split(" "))
    
     def numConvert(card: String): Int = {
      if(card == "T") 10
      else if(card == "J") 11
      else if(card == "Q") 12
      else if(card == "K") 13
      else if(card == "A") 14
      else card.toInt
    }
    
    def highCard(hand1: Array[Int], hand2: Array[Int]): Int = {
      val hn1 = hand1.max
      val hn2 = hand2.max
      if(hn1 > hn2) 1 
      else if(hn2 > hn1) 2
      else{
        val update1 = hand1.filter(x => x != hn1)
        val update2 = hand2.filter(x => x != hn2)
        highCard(update1, update2)
      }
    }
    
    /*
    def royalFlush(hands: Array[Array[String]], index: Int = 0): Int = {
      val hn1 = hands(index).slice(0, 5).map(_.toString).map(x => x(0).toString)
      val hn2 = hands(index).slice(5, 11).map(_.toString).map(x => x(0).toString)
      val hs1 = hands(index).slice(0, 5).map(_.toString).map(x => x(1).toString)
      val hs2 = hands(index).slice(5, 11).map(_.toString).map(x => x(1).toString)
      if (hn1.intersect(Array("A", "K", "Q", "J", "T")).length == 5) {
        if (hs1.filter(x => x == hs1(0)).length == 5) return 1
      }
      if (hn2.intersect(Array("A", "K", "Q", "J", "T")).length == 5) {
        if (hs2.filter(x => x == hs2(0)).length == 5) return 2
        else 0
      } else return 0
    }*/
    
    def royalFlush(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      if (hand1.intersect(Array(14, 13, 12, 11, 10)).length == 5) {
        if (suit1.filter(x => x == suit1(0)).length == 5) return 1
      }
      if (hand2.intersect(Array(14, 13, 12, 11, 10)).length == 5) {
        if (suit2.filter(x => x == suit2(0)).length == 5) return 2
        else 0
      } else return 0
    }
    
    def fullHouse(hand1: Array[Int], hand2: Array[Int]): Int = {
      val h1 = hand1.sorted
      val h2 = hand2.sorted
      if(h1.count(h1(0) == _) + h1.count(h1(4) == _) == 5){
        if(h2.count(h2(0) == _) + h2.count(h2(4) == _) != 5) 1
        else highCard(Array(h1.groupBy(identity).maxBy(_._2.size)._1), Array(h2.groupBy(identity).maxBy(_._2.size)._1))
      } 
      else if(h2.count(h2(0) == _) + h2.count(h2(4) == _) == 5) 2
      else 0
    }
    
    def straight(hand1: Array[Int], hand2: Array[Int]): Int = {
      val h1 = hand1.sorted
      val h2 = hand2.sorted
      def isConsecutive(x: Array[Int], acc: Int = 0): Boolean = {
        if(x.length > 1) {
          if(x(1) - x(0) == 1) isConsecutive(x.tail)
          else false
        }
        else true
      }
      if(isConsecutive(h1) && !isConsecutive(h2)) 1
      else if(isConsecutive(h2) && !isConsecutive(h1)) 2
      else if(isConsecutive(h1) && isConsecutive(h2)) highCard(hand1, hand2)
      else 0
    }

    def flush(suit1: Array[String], suit2: Array[String]): Int = {
      if (suit1.filter(x => x == suit1(0)).length == 5) return 1
      else if (suit2.filter(x => x == suit2(0)).length == 5) return 2
      else 0
    }
    
    def onePair(hand1: Array[Int], hand2: Array[Int]): Int = {
      //must be checked after the two pair and three of a kind to avoid false positives
      val pair1 = hand1.filter(x => hand1.count(x == _) > 1)
      val pair2 = hand2.filter(x => hand2.count(x == _) > 1)
      if(pair2.isEmpty && !pair1.isEmpty) 1
      else if(pair1.isEmpty && !pair2.isEmpty) 2
      else if(pair1.isEmpty && pair2.isEmpty) 0
      else if(pair1(0) < pair2(0)) 2
      else 1
    }
    
    def threeKind(hand1: Array[Int], hand2: Array[Int]): Int = {
      val pair1 = hand1.filter(x => hand1.count(x == _) > 2)
      val pair2 = hand2.filter(x => hand2.count(x == _) > 2)
      if(pair2.isEmpty && !pair1.isEmpty) 1
      else if(pair1.isEmpty && !pair2.isEmpty) 2
      else if(pair1.isEmpty && pair2.isEmpty) 0
      else if(pair1(0) < pair2(0)) 2
      else 1
    }
    
    def fourKind(hand1: Array[Int], hand2: Array[Int]): Int = {
      val pair1 = hand1.filter(x => hand1.count(x == _) > 3)
      val pair2 = hand2.filter(x => hand2.count(x == _) > 3)
      if(pair2.isEmpty && !pair1.isEmpty) 1
      else if(pair1.isEmpty && !pair2.isEmpty) 2
      else if(pair1.isEmpty && pair2.isEmpty) 0
      else if(pair1(0) < pair2(0)) 2
      else 1
    }
    
    def twoPairs(hand1: Array[Int], hand2: Array[Int]): Int = {
      val h1 = hand1.sorted
      val h2 = hand2.sorted
      val pair1 = h1.count(h1(1) == _) == 2 && h1.count(h1(3) == _) == 2
      val pair2 = h2.count(h2(1) == _) == 2 && h2.count(h2(3) == _) == 2
      if(pair1 && !pair2) 1
      else if(pair2 && !pair1) 2
      else if(pair1 && pair2) highCard(h1, h2)
      else 0
    }
    
    def straightFlush(hand1: Array[Int], hand2: Array[Int], suit1: Array[String], suit2: Array[String]): Int = {
      if(straight(hand1, hand2) == 1 && flush(suit1, suit2) == 1) 1
      else if(straight(hand1, hand2) == 2 && flush(suit1, suit2) == 2) 2
      else 0
    }
    
    def handProcessor(hands: Array[Array[String]], index: Int): Array[Array[_ >: String with Int]] = {
      val hn1 = hands(index).slice(0, 5).map(x => x(0)).map(_.toString).map(x => numConvert(x))
      val hn2 = hands(index).slice(5, 11).map(x => x(0)).map(_.toString).map(x => numConvert(x))
      val hs1 = hands(index).slice(0, 5).map(_.toString).map(x => x(1).toString)
      val hs2 = hands(index).slice(5, 11).map(_.toString).map(x => x(1).toString)
      val handsArray = Array(hn1, hs1, hn2, hs2)
      handsArray
    }
    
    def iterator(index: Int = 0, acc: Int = 0, handsArray: Array[Array[String]] = hands): Int = {
      //this function should check each possible winning hand in descending order of strength
      //once a winning hand (returning a non-zero value) has been found, 
      //iterate recursively to the next value
      //acc keeps track of the number of player one wins
      //index passes through each index
      val hn1 = handsArray(index).slice(0, 5).map(x => x(0)).map(_.toString).map(x => numConvert(x))
      val hn2 = handsArray(index).slice(5, 11).map(x => x(0)).map(_.toString).map(x => numConvert(x))
      val hs1 = handsArray(index).slice(0, 5).map(_.toString).map(x => x(1).toString)
      val hs2 = handsArray(index).slice(5, 11).map(_.toString).map(x => x(1).toString)
      val initial = royalFlush(hn1, hn2, hs1, hs2)
      if(index == handsArray.length - 1) acc
      else if(initial == 1) iterator(index + 1, acc + 1)
      else if(initial == 2) iterator(index + 1, acc)
      else {
        val sf = straightFlush(hn1, hn2, hs1, hs2)
        if(sf == 1) {println("1"); iterator(index + 1, acc + 1)}
        else if(sf == 2) iterator(index + 1, acc)
        else {
          val fourOfKind = fourKind(hn1, hn2)
          if(fourOfKind == 1) iterator(index + 1, acc + 1)
          else if(fourOfKind == 2) iterator(index + 1, acc)
          else {
            val full = fullHouse(hn1, hn2)
            if(full == 1) iterator(index + 1, acc + 1)
            else if(full == 2) iterator(index + 1, acc)
            else { 
              val fl = flush(hs1, hs2)
              if(fl == 1) iterator(index + 1, acc + 1)
              else if(fl == 2) iterator(index + 1, acc)
              else {
                val str = straight(hn1, hn2)
                if(str == 1) iterator(index + 1, acc + 1)
                else if(str == 2) iterator(index + 1, acc)
                else {
                  val three = threeKind(hn1, hn2)
                  if(three == 1) iterator(index + 1, acc + 1)
                  else if(three == 2) iterator(index + 1, acc)
                  else {
                    val tp: Int = twoPairs(hn1, hn2)
                    if(tp == 1) iterator(index + 1, acc + 1)
                    else if(tp == 2) iterator(index + 1, acc)
                    else {
                       val pair = onePair(hn1, hn2)
                       if(pair == 1) iterator(index + 1, acc + 1)
                       else if(pair == 2) iterator(index + 1, acc)
                       else {
                         val high = highCard(hn1, hn2)
                         if(high == 1) iterator(index + 1, acc + 1)
                         else if(high == 2) iterator(index + 1, acc)
                         //should never be reached if it functions correctly:
                         else {
                           val answer = 0
                           answer
                         }
                       }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    val answer = iterator()
    println("Player one won " + answer + " hands")
    
  }
}