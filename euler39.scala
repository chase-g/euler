/**
 * Project Euler
 * Problem 39
 * "If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
 * {20,48,52}, {24,45,51}, {30,40,50}
 * For which value of p ≤ 1000, is the number of solutions maximised?"
 * November 22, 2017
 */
object euler39 {
  def main(args: Array[String]): Unit = {

    val startMap = (1 to 10000).map(x => (x, 0)).toMap

    def pytha(a: Int, b: Int): Double = {
      val c: Double = Math.sqrt((a * a) + (b * b))
      c
    }

    def inner(acc: Map[Int, Int], a: Int, b: Int): Map[Int, Int] = {
      val c = pytha(a, b)
      val perimeter = (a + b + c).toInt
      if (c % 1.0 == 0.0 && perimeter < 1001) {
        val update = acc + (perimeter -> (acc(perimeter) + 1))
        if (b < a) inner(update, a, b + 1)
        else update
      } else if (b < a) inner(acc, a, b + 1)
      else acc
    }

    def iterator(perimeters: Map[Int, Int], a: Int): Map[Int, Int] = {
      val output = inner(perimeters, a, 1)
      if (a < 999) iterator(output, a + 1)
      else output
    }

    println(iterator(startMap, 3).filterKeys(_ < 1001).maxBy(_._2)._1)

  }
}