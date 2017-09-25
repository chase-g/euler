/**
 * Project Euler 
 * Problem 67
 * "Find the maximum total from top to bottom in triangle.txt, a 15K text file containing a triangle with one-hundred rows."
 * September 25, 2017
 */
import scala.io.Source
val filename = "p067_triangle.txt"
val triangle = Array.ofDim[Int](100,100)   //create a 100 by 100 array of int arrays
val lines = Source.fromFile(filename).getLines.toArray   //get the text from the file and save each line as an array item
for(i <- 0 until lines.length){   //loop through each line of the lines array
//split by newlines, change to Int, and add each array item to the array of arrays
  triangle(i) = lines(i).split(" ").map(_.toInt)
}
//determine the greater of the two values below each value, moving from second from the bottom to the top
for(i <- triangle.length - 2 to 0 by -1){   //outer loop over each row, starting at second to last
  for(n <- 0 until triangle(i).length){   //inner loop over each value within the row
    //save the maximum of the two values below the selected number
    val best = Math.max(triangle(i + 1)(n), triangle(i + 1)(n + 1))   
    triangle(i)(n) = triangle(i)(n) + best   //add the higher of the two to the selected number
  }
}
println("Answer: " + triangle(0)(0))   //top value will have been set to the value of the highest path
