/*
 * Project Euler
 * Problem 58
 * August 4, 2018
*/
import static java.lang.Math.toIntExact;

class euler58 {
	public static void main(String[] args) {
		//initiate spiral with upper bound for sieve
		new spiral(699999999);
		spiral.makeSpiral();
	}

}

class spiral {

	private static boolean[] notPrimes;

	public spiral(int limit) {
		//stores prime/non-prime numbers
		notPrimes = setNotPrimes(limit);
	}

	private static boolean[] setNotPrimes(int lim) {
		//uses sieve of eratosthenes to note prime numbers
		boolean[] notPrimes = new boolean[lim];
		notPrimes[0] = true;
		notPrimes[1] = true;
		int num = 2;
		while (num < lim) {
			if (notPrimes[num] == false) {

				for (int i = num + num; i < lim; i += num) {
					notPrimes[i] = true;
				}
			}
			num++;
		}
		return notPrimes;
	}

	private static boolean inPrime(long num) {
		try {
			//check whether the number is marked prime
			if (notPrimes[toIntExact(num)] == false)
				return true;
			else
				return false;
		} catch (IndexOutOfBoundsException e) {
			//if number is out of the bounds of the sieve, use the back up isPrime method
			boolean ans = isPrime(num);
			return ans;
		}
	}

	//this method determines whether a number is prime if the numbers exceed the bounds of the sieve
	public static boolean isPrime(long num) {
		if (num % 2L == 0)
			return false;
		else {
			long fact = 3L;
			long limit = (num / 2) + 1;
			while (fact < limit) {
				if (num % fact == 0)
					return false;
				else
					fact += 2;
			}
			return true;
		}
	}

	public static void makeSpiral() {
		//start with the state of the first spiral
		long diagInterval = 2;
		long nextNum = 9;
		double numer = 3;
		double denom = 5;
		double currentRatio = 1;
		int corner = 1;
        //continue until the ratio of primes along the diagnols < 10%
		while (currentRatio > .1) {
			//iterate the interval between corners by two each time it spirals
			diagInterval += 2L;
			//iterate over each corner, spaced by the current interval
			while (corner <= 4) {
				nextNum = nextNum + diagInterval;
				//if the number is prime, the numerator in the ratio of primes to all numbers is incremented
				if (inPrime(nextNum)) {
					numer++;
					corner++;
				} else {
					corner++;
				}
			}
			//denominator is incremented by all four additional corners
			denom += 4L;
			corner = 1;
			currentRatio = numer / denom;
		}
		// add one to the interval because side length should be inclusive of the corner
		System.out.println(diagInterval + 1);
	}

}