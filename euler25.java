/**
 * Project Euler 25
 * "What is the index of the first term in the Fibonacci sequence to contain 1000 digits?"
 * @version August 9, 2017
 * @author chase-g
 */
import java.math.BigInteger;
public class euler25{
	public static void main(String[] args) {
	//use BigInteger type for variables
	BigInteger amount, placeholder, num;
	amount = BigInteger.valueOf(1);
	placeholder = BigInteger.valueOf(1);
	num = BigInteger.valueOf(1);
	long index = 2;
	//continue until the amount reaches 1000 digits
	while(amount.toString().length() < 1000) {
		//set num to the placeholder, which is the previous amount
		num = placeholder;
		//set placeholder to the current amount
		placeholder = amount;
		//increase amount by num, the previous amount
		amount = amount.add(num);
		index++;
	}
		System.out.println(index);
  }
}
