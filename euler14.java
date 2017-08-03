/**
 * Project Euler 14
 * Longest Collatz Sequence under one million
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * "Which starting number, under one million, produces the longest chain?"
 * @version August 3, 2017
 * @author chase-g
 */
public class euler14
{
	public static int record = 0;
   public static void main(String[] args)
   {

	final long startTime = System.currentTimeMillis();
	   for(int i = 2; i < 1000000; i++) {
	   long collatz = i;
	   int counter = 0;
	   while(collatz != 1) {
		   if(collatz % 2 == 0) {
			  collatz = collatz / 2; 
			  counter++;
			  if(counter > record && collatz == 1) {
				   record = counter;
				   System.out.println(i + ", current longest chain: " + record);
			   }
		   } else {
			   collatz = (3 * collatz) + 1;
			   counter++;
			   if(counter > record && collatz == 1) {
				   record = counter;
				   System.out.println(i + "--current longest chain: " + record);
			   }
		   }
	   }
    }
final long endTime = System.currentTimeMillis();
System.out.println("Speed in milliseconds: " + (endTime - startTime));
  }
}
