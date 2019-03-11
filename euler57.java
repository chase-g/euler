import java.math.BigInteger;

public class euler57 {
	public static void main(String[] args) {
		class Rational {
			public BigInteger n, d, den;
			public int nDigits, dDigits;

			public Rational(BigInteger numerator, BigInteger denominator) {
				this.den = numerator.gcd(denominator);
				this.n = numerator.divide(den);
				this.d = denominator.divide(den);
				this.nDigits = n.toString().length();
				this.dDigits = d.toString().length();
			}

			@Override
			public String toString() {
				return String.format(n.toString() + "/" + d.toString());
			}

			public boolean numeratorMoreDigits() {
				if (nDigits > dDigits) {
					return true;
				} else {
					return false;
				}
			}

			public Rational divideBy(Rational other) {
				return new Rational(this.n.multiply(other.d), this.d.multiply(other.n));
			}

			public Rational add(Rational other) {
				return new Rational(this.n.multiply(other.d).add(other.n.multiply(this.d)), this.d.multiply(other.d));
			}
		}

		BigInteger x = BigInteger.valueOf(3);
		BigInteger y = BigInteger.valueOf(2);
		BigInteger z = BigInteger.valueOf(1);
		Rational current = new Rational(x, y);
		Rational priorAnswer = current;
		Rational fixedOne = new Rational(z, z);
		int counter = 0;
		for (int i = 1; i < 1000; i++) {
			priorAnswer = current;
			// 1 + (1 / (1 + prior answer))
			current = (fixedOne.divideBy((priorAnswer.add(fixedOne)))).add(fixedOne);
			if (current.numeratorMoreDigits()) {
				counter++;
			}
		}
		System.out.println(counter);
	}
}
