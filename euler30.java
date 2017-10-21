/**
 * Project Euler 30
 * "Find the sum of all the numbers that can be written as the sum of fifth powers of their digits."
 * @version October 20, 2017
 * @author chase-g
 */
public class euler30{
	public static void main(String[] args) {
		double[] selected;
		selected = new double[1000000];
		for(int i = 2; i < 1000000; i++) {
			String iStr;
			iStr = String.valueOf(i);
			String [] strArray = iStr.split("");
			double[] expArray;
			expArray = new double[100];
			for(int n = 0; n < strArray.length; n++) {
				int num = Integer.valueOf(strArray[n]);
				double exp = Math.pow(num, 5);
				expArray[n] = exp;
			}
			double sum = 0;
			for(int t = 0; t < expArray.length; t++) {
				sum += expArray[t];
			}
			if(sum == i) {
				selected[i] = i;
			}
		}
		int total = 0;
		for(int e = 0; e < selected.length; e++) {
			total += selected[e];
    }
		System.out.println(total);
  }
}