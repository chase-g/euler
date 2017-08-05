/**
* Project Euler 16
* "What is the sum of the digits of the number 2^1000?"
*/
import java.math.BigInteger;
public class euler16 {
	public static void main(String[] args) {
//use BigInteger type to manage 2^1000
		BigInteger num, answer;
		answer = new BigInteger("0");
//set 2^1000
		num = new BigInteger("2");
		num = num.pow(1000);
		String numStr;
//create a string version of 2^1000
		numStr = num.toString();
//loop over each digit of the string
		for(int i = 0; i < numStr.length(); i++) {
//take each digit as a substring
			String str; 
			str = numStr.substring(i, i+1);
//convert the digit into a BigInteger and add it to the answer
			BigInteger section; 
			section = new BigInteger(str);
			answer = answer.add(section);	
		}
		System.out.println(answer);
	}
}