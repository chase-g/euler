import java.util.Hashtable;
/**
 * Project Euler 17
 * "If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?"
 * @version August 6, 2017
 * @author chase-g
 */
public class euler17{
	public static void main(String[] args) {
		//create hashtable
		Hashtable<Integer, String> dict = new Hashtable<Integer, String>();
		//add hashtable values corresponding with the written version of each digit
		dict.put(0, "");
		dict.put(1, "one");
		dict.put(2, "two");
		dict.put(3, "three");
		dict.put(4, "four");
		dict.put(5, "five");
		dict.put(6, "six");
		dict.put(7, "seven");
		dict.put(8, "eight");
		dict.put(9, "nine");
		dict.put(10, "ten");
		dict.put(11, "eleven");
		dict.put(12, "twelve");
		dict.put(13, "thirteen");
		dict.put(14, "fourteen");
		dict.put(15, "fifteen");
		dict.put(16, "sixteen");
		dict.put(17, "seventeen");
		dict.put(18, "eighteen");
		dict.put(19, "nineteen");
		dict.put(20, "twenty");
		dict.put(30, "thirty");
		dict.put(40, "forty");
		dict.put(50, "fifty");
		dict.put(60, "sixty");
		dict.put(70, "seventy");
		dict.put(80, "eighty");
		dict.put(90, "ninety");
		dict.put(1000, "onethousand");
		//amount which will be incremented to serve as a counter
		int amount = 0;
		//loop over each digit through 1000
		for(int i = 1; i < 1001; i++) {
			//if the number is 1-20 or 1000:
			if(dict.containsKey(i) && i < 21 || i == 1000) {
				//get the string corresponding with the number in the hashtable
				String str = dict.get(i);
				//increment by the string length
				amount += str.length();
			} else {
				//create a string from the number
				String iStr;
				iStr = String.valueOf(i);
				//split each digit into an array
				String [] strArray = iStr.split("");
				//for each digit in that array
				for(int n = 0; n < strArray.length; n++) {
					//if a three digit number ending in '00', e.g. 100, 200
					if(strArray.length == 3 && strArray[1].equals("0") && strArray[2].equals("0")) {
						//turn the digit into a string
						int item;
						item = Integer.valueOf(strArray[n]);
						//modify the string from the hashtable
						String str = dict.get(item) + "hundred";
						amount += str.length();
						//no more work needed for final digit
						break;
					//if the first digit of some other three digit number, e.g. 101, 345
					} else if(n == 0 && strArray.length == 3){
						int item;
						item = Integer.valueOf(strArray[n]);
						//modify the string from the hashtable 
						String str = dict.get(item) + "hundredand";
						amount += str.length();
					//if a three digit number with the second digit a '1', e.g. 115, 719
					} else if(strArray.length == 3 && strArray[1].equals("1")){
						//create an int from the combined digits instead
						String digits = strArray[1] + strArray[2];
						int item = Integer.valueOf(digits);
						String str = dict.get(item);
						amount += str.length();
						//no more work needed for final digit
						break;
				    //if second digit of a three digit number or the first of a two digit
				    //e.g. the 'forty' in 141 or 41
					} else if(n == 1 && strArray.length == 3 || n == 0 && strArray.length == 2){
						int item;
						//create integer with a zero, e.g. 4 -> 40
						item = Integer.valueOf(strArray[n] + 0);
						String str = dict.get(item);
						amount += str.length();
					//all other numbers:
					} else {
						int item;
						item = Integer.valueOf(strArray[n]);
						String str = dict.get(item);
						amount += str.length();
					}
		  }
	   }
	 }
		System.out.println(amount);
  }
}