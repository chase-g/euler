import java.math.BigInteger;

class euler63 {
	public static void main(String[] args) {
		int runningCount = 0;
		int currentPower = 1;
		while (currentPower > 0) {
			int numMatches = iteratePowers(currentPower);
			if (numMatches == 0) {
				currentPower = 0;
			} else {
				runningCount += numMatches;
				currentPower++;
			}
		}
		System.out.println("answer: " + runningCount);
	}

	public static int iteratePowers(int power) {
		BigInteger base = BigInteger.valueOf(1);
		int counter = 0;
		BigInteger currentAns = BigInteger.valueOf(1);
		while (currentAns.toString().length() <= power) {
			BigInteger exp = BigInteger.valueOf(1);
			for (int i = 1; i <= power; i++) {
				exp = exp.multiply(base);
			}
			if (exp.toString().length() == power) {
				counter++;
				base = base.add(BigInteger.valueOf(1));
				currentAns = exp;
			} else {
				base = base.add(BigInteger.valueOf(1));
				currentAns = exp;
			}
		}
		return counter;
	}
}
