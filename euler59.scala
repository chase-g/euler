/**
 * Project Euler
 * Problem 59
 * XOR Decryption
 * December 1, 2017
 */
object euler59 {
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val message = Source.fromFile("p059_cipher.txt").getLines.toArray.flatMap(x => x.split(",").map(_.toInt))
    
    def decrypted(mess: Array[Int]): Boolean = {
      def len(ch: Char): Int = {
        mess.count(_.toInt == ch)
      }
      val eLen = len('e')
      val tLen = len('t')
      val oLen = len('o')
      val zLen = len('z')
      val xLen = len('x')
      val qLen = len('q')
      if(eLen > zLen && eLen > xLen && eLen > qLen && oLen > zLen && tLen > zLen) {
        val words = mess.map(_.toChar).mkString
        if(words.contains("the") && words.contains("and") && words.contains("was") && words.contains("is")) true
        else false
      } else false
    }
    
    def xor(a: Int, b: Int, c: Int, index: Int, mess: Array[Int] = message): Int = {
        if(index == 0) mess(index) ^ a 
        else if((index % 3) == 0) mess(index) ^ a
        else if((index - 1) % 3 == 0) mess(index) ^ b 
        else if((index - 2) % 3 == 0) mess(index) ^ c
        else 0
      }
    
    def applyXor(a: Int, b: Int, c: Int, mess: Array[Int] = message): Array[Int] = {
      val answer = for{ 
        i <- 0 until mess.length
      } yield xor(a, b, c, i)
      answer.toArray
    }

    def iterate(a: Int, b: Int, c: Int, mess: Array[Int] = message): Int = {
      val current = applyXor(a, b, c)
      if(decrypted(current)) {
        println("The Decrypted message: \n" + current.map(_.toChar).mkString)
        println("The encryption keyword is: '" + a.toChar + b.toChar + c.toChar+ "'")
        current.reduceLeft(_ + _)
      }
      else if(c < 123 && b < 123) iterate(a, b, c + 1)
      else if(c >= 123 && b < 123) iterate(a, b + 1, 97)
      else if(a >= 123) 0
      else iterate(a + 1, 97, 97)
    }
    println("Answer: " + iterate(97,97,97))
  }
}