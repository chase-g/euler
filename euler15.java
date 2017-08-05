/**
 * Project Euler 15
 * "Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
 * How many such routes are there through a 20×20 grid?"
 * Answer can be found with binomial coefficient
 * @version August 3, 2017
 * @author chase-g
 */
import java.math.BigInteger;
public class euler15
{
   public static void main(String[] args)
   {
//use BigInteger type to handle large numbers
	BigInteger first, second;
	first = new BigInteger("40");
	second = new BigInteger("20");
//apply the binomial coefficient formula (2n)! / n!^2
//factorial of numerator
	BigInteger fact1 = factorial(first);
//factorial of denominator
	BigInteger fact2 = factorial(second);
//to the power of 2
	fact2 = fact2.pow(2);
//divide numerator by denominator
	BigInteger ans = fact1.divide(fact2);
	System.out.println(ans);
  }
//method for finding factorials
   public static BigInteger factorial(BigInteger num) {
	   BigInteger answer, facto, nothing, one;
	   nothing = new BigInteger("0");
	   one = new BigInteger("1");
	   answer = new BigInteger("1");
//while the number is greater than 0
	   while(num.compareTo(nothing) > 0) {
//multiply the answer with number
		   answer = answer.multiply(num);
//and then decrement the number
		   num = num.subtract(one);
	   }
	   return answer;
   }
}