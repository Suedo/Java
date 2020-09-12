package DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DaysInMonth {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("21/02/2001"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cal.getActualMaximum(Calendar.DATE));
	}
}
