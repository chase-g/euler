/**
 * Project Euler 
 * Problem 26
 * "Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part."
 * October 18, 2017
 */
object euler28 {
  def main(args: Array[String]): Unit = {
    
    // create descending array from peak value determined by dimensions 1001 x 1001 down to 1
    val grid = ((1001 * 1001) to 1 by -1).toArray // lowest index will be highest value
    
    // function steps from innermost corner values outward starting with high indexes,
    // where the index of each corner value should be 2 indexes further apart at each successive layer
    def stepThrough(index: Int, step: Int, spiral: Array[Int], total: Int): Int = {
      // get sum of each corner of the layer, determined by the stepped up gap
      val answer = spiral(index) + spiral(index - step) + spiral(index - 2 * step) + spiral(index - 3 * step)
      val nextIndex = (index - 3 * step) - (step + 2) // determine next index using a step increased by two
      if(nextIndex > 0) stepThrough(nextIndex, step + 2, spiral, total + answer) // if the index is above zero, call recursively 
      else{
        // else print and return the running total 
        println(total + answer)
        return total + answer
      }
    }
    
    stepThrough(grid.length - 3, 2, grid, 1) // initiate at the next value, with one already counted
  }
}
