/*
 * Project Euler 56
 * "A googol (10100) is a massive number: one followed by one-hundred zeros; 100100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
 * Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?"
*/
import java.math.BigInteger;
public class euler56 {
	public static void main(String[] args) {
		System.out.println("answer: " + runThru());
	}
	private static int record = 0;
	private static BigInteger lwrBound = BigInteger.valueOf(0);
	
	private static BigInteger makeExp(int num, int pow) {
		BigInteger x = BigInteger.valueOf(num);
		BigInteger ans = x;
		for(int i = 0; i < pow; i ++) {
			ans = ans.multiply(x);
		}
		return ans;
	}
	
	private static BigInteger lowerBound(int record) {
		String str = new String(new char[(record / 9)]).replace("\0", "9");
		BigInteger ans = new BigInteger(str);
		return ans;
	}
	
	private static int digitSum(BigInteger num) {
		String[] digits = num.toString().split("");
		int ans = 0;
		for(int n = digits.length - 1; n >= 0; n--) {
			ans = ans + Integer.parseInt(digits[n]);
		}
		return ans;
	}
	
	//check each base and exponent combo
	//if current num in consideration is at lower bound of current record,
	//switch to next lower base
	//else if current num is at exp 100 and still below lower bound,
	//stop and return the record
	//else iterate down to next lower power
	//where lower bound is defined as the number which has too few digits to beat the existing record
	private static int runThru() {
		int num = 99;
		int pow = 100;
		int currentSum = 0;
		boolean notMin = true;
		BigInteger currentVal = makeExp(100,100);
		while(notMin) {
			currentVal = makeExp(num,pow);
			currentSum = digitSum(currentVal);
			if(currentSum > record) {
				lwrBound = lowerBound(currentSum);
				record = currentSum;
			}
			else if(pow != 100 && currentVal.compareTo(lwrBound) == -1) {
				num = num - 1;
				pow = 100;
			}
			else if(num == 0 || pow == 100 && currentVal.compareTo(lwrBound) == -1) {
				notMin = false;
				return record;
			}
			else if (pow > 0){
				pow = pow - 1;
			}
			else {
				num = num - 1;
				pow = 100;
			}
		}
		return record;
	}
	
}