/*
 * Project Euler
 * Problem 24
 * "What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?"
*/
object euler24 {
  def main(args: Array[String]): Unit = {
    val l = (9 to 0 by -1).toList

    def permute(x: List[Int]): List[Int] = {
      //step one: get largest [smallest] index which has a value smaller than that of the subsequent index
      def one(ind: Int): Int = {
        if (x.length > ind && x(ind) < x(ind - 1)) ind
        else if (x.length > ind - 1) one(ind + 1)
        else x.length - 1
      }
      //step two: get the largest [smallest] index with a value larger than that identified in step one
      def two(ind: Int, comp: Int): Int = {
        if (x(ind) > x(comp)) ind
        else two(ind + 1, comp)
      }
      //step three: swap values identified in step one and step two
      def three(x: Int, y: Int, z: List[Int]): List[Int] = {
        val newY = z(x)
        val newX = z(y)
        val out = z.updated(x, newX).updated(y, newY)
        out
      }
      //step four: reverse all values above [below] the index identified in step one
      def four(w: List[Int], ind: Int): List[Int] = { w.take(ind).reverse ++ w.drop(ind) }

      val uno = one(1)
      val dos = two(0, uno)
      val tres = three(uno, dos, x)
      val cuatro = four(tres, uno)
      cuatro
    }
    //print digits of the millionth permutation
    def recur(nums: List[Int], times: Int, current: Int = 0): List[Int] = {
      if (current == times) nums
      else recur(permute(nums), times, current + 1)
    }
    val answer = recur(l, 999999)
    for (i <- answer.reverse) print(i); println();
  }
}
