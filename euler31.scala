/**
 * Project Euler
 * Problem 31
 * "How many different ways can £2 be made using any number of coins?"
 * November 10, 2017
 */
object euler31{
  def main(args: Array[String]): Unit = {
    def countChange(money: Int, coins: List[Int]): Int = {
    val combinations = new Array[Int](money + 1)
    combinations(0) = 1
    def iterate(num: Int, theCoin: Int): Unit = {
      if (num > money) print("")
      else {
        combinations(num) = combinations(num) + combinations(num - theCoin)
        iterate(num + 1, theCoin)
      }
    }
    def makeChange(index: Int): Unit = {
      if (index < coins.length) {
        val coin = coins(index)
        iterate(coin, coin)
        makeChange(index + 1)
      } else println("Answer: " + combinations(money))
    }
    makeChange(0)
    combinations(money)
  }
    countChange(200, List(1, 2, 5, 10, 20, 50, 100, 200))
  }
}