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
	private static int record = 0;
	private static int recordHolder = 0;
   public static void main(String[] args)
   {
//start timer
	final long startTime = System.currentTimeMillis();
	//iterate over every number up to one million
	   for(int i = 2; i < 1000000; i++) {
	//set number to perform collatz sequence
	   long collatz = i;
	//counts number of steps in the collatz sequence
	   int counter = 0;
	//run sequence until reaching one
	   while(collatz != 1) {
//if even, perform operation and increment counter
		   if(collatz % 2 == 0) {
			  collatz = collatz / 2; 
			  counter++;
//if it reaches one and counter exceeds existing record
			  if(counter > record && collatz == 1) {
//set new record and record holder
				   record = counter;
				   recordHolder = i;
			   }
//if odd, perform operation and increment counter
		   } else {
			   collatz = (3 * collatz) + 1;
			   counter++;
//if it reaches one and counter exceeds existing record
			   if(counter > record && collatz == 1) {
//set new record and record holder
				   record = counter;
				   recordHolder = i; 
			   }
		   }
	   }
//After the final iteration, print the record holder and record
	   if(i == 999999){
		   System.out.println(recordHolder + " has the longest collatz sequence under 1 million: " + record); 
		   }
    }
//end timer and output speed
final long endTime = System.currentTimeMillis();
System.out.println("Speed in milliseconds: " + (endTime - startTime));
  }
}
