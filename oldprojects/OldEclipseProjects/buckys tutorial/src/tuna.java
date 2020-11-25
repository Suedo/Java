
public class tuna {
	private int hour,min,sec;
	
	public void setTime(int h,int m,int s){
		hour = ((h>0 && h<24) ? h : 0);
		min = ((m>0 && m<60) ? m : 0);
		sec = ((s>0 && s<60) ? s : 0);
	}
	
	public String toMilitary(){
		return String.format("%02d:%02d:%02d",hour,min,sec);
	}
	
	String amPm = "AM";
	boolean AM = true;
	
	public String toNormal(){
		
		amPm = checkAmPm(hour);
		return String.format("%02d:%02d:%02d %s",(hour % 12 ),min,sec,amPm);
	}
	
	public String checkAmPm(int h){
		
		if(h >= 12 && AM){
			return "PM";
		}
		return "AM";
	}

}
