/**
 * Project Euler 30
 * "Find the sum of all the numbers that can be written as the sum of fifth powers of their digits."
 * @version October 20, 2017
 * @author chase-g
 */
public class euler30{
	public static void main(String[] args) {
		double[] selected;
		selected = new double[1000000];  // create array with capacity for all potential values 
		for(int i = 2; i < 1000000; i++) {  // loop without including one
			String iStr;
			iStr = String.valueOf(i);  // create string of each number value and split into array of digits
			String [] strArray = iStr.split("");
			double[] expArray;
			expArray = new double[10];
			for(int n = 0; n < strArray.length; n++) {  // for each digit 
				int num = Integer.valueOf(strArray[n]);  // get int value of the digit
				double exp = Math.pow(num, 5);  // get x^5 for that digit
				expArray[n] = exp;  // append to the expArray
			}
			double sum = 0;
			for(int t = 0; t < expArray.length; t++) { 
				sum += expArray[t];  // iterate through each exArray value to generate their sum
			}
			if(sum == i) {  // if the sum of digits^5 equals the original number, save it to the memo array
				selected[i] = i;
			}
		}
		int total = 0;
		for(int e = 0; e < selected.length; e++) {
			total += selected[e];  // sum the values in the array of selected numbers
    }
		System.out.println(total); 
  }
}
