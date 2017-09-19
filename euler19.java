import java.time.DayOfWeek;
import java.time.LocalDate;
/**
 * Project Euler 19
 * "How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?"
 * @version September 18, 2017
 * @author chase-g
 */
public class euler19{
	public static void main(String[] args) {
//set variables for number of Sundays and the start and end dates
		int sundaysCounter = 0;
		LocalDate startDate = LocalDate.of(1901, 1, 1);
		LocalDate endDate = LocalDate.of(2000, 12, 31);
//loop starting at start date until end date, and incrementing by day
		for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
//if the day is Sunday and it's the first of the month, increment counter
			if(date.getDayOfWeek() == DayOfWeek.SUNDAY && date.getDayOfMonth() == 1) {
				sundaysCounter += 1;
			}
		}
		System.out.println(sundaysCounter);
	   }
	 }
