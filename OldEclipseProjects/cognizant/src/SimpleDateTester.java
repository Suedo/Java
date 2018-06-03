import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleDateTester {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeformat = new SimpleDateFormat("DDDD/MM/yyyy HH:mm:ss:SSS a z");
		
		System.out.println(timeformat.format(Calendar.getInstance().getTime()));
		
		Calendar c2 = Calendar.getInstance();
		try {
			c2.setTime(sdf.parse("10/02/2000"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c2.getActualMaximum(Calendar.DATE));
	}
}
