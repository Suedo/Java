package DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DayDiff {
	public static void main(String[] args) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c2.add(Calendar.DATE, 10);
		c2.add(Calendar.HOUR, 1);
		System.out.println(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(c2.getTime()));
		
		System.out.println((c2.getTimeInMillis()-c1.getTimeInMillis())/(86400*1000));
		
	}
}
