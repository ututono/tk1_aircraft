package utilss;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class RandomGenerator {
	public static String getRandomUpperletters(int length) {
		String rangeString="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer result=new StringBuffer();
		Random random=new Random();
		if (length<=0) {
			return null;
		}
		for (int i = 0; i < length; i++) {
			result.append(rangeString.charAt(random.nextInt(rangeString.length())));
		}
		return result.toString();
	}
	
	
	/*
	 * A format name is the word whose first letter is upper and other letter following is lower.
	 */
	public static String getFormatNameString(int length) {
		String rangeString="abcdefghijklmnopqrstuvwkyz";
		Random random = new Random();
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append(getRandomUpperletters(1));
		for (int i = 1; i < length; i++) {
			stringBuffer.append(rangeString.charAt(random.nextInt(rangeString.length())));
		}
		return stringBuffer.toString();
		
	}
	
	public static int getRandomNumber(int min, int max) {
		Random random=new Random();
		int result=random.nextInt(max-min+1)+min;
		return result;
	}
	
	public static String getNumString(int length) {
		String range="0123456789";
		Random random=new Random();
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(range.charAt(random.nextInt(range.length())));
		}
		return stringBuffer.toString();
		
	}
	public static Character gestRandomStatus() {
		String range="BDILMSXYZ";
		Random random=new Random();
		return range.charAt(random.nextInt(range.length()));
	}
	
	 public static LocalDateTime getrandomDate(String beginDate, String endDate) {
	    try {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date start = format.parse(beginDate);
	        Date end = format.parse(endDate);
	        if (start.getTime() >= end.getTime()) {
	            return null;
	        }
	        long date = random(start.getTime(), end.getTime());
	        Date date_converted=new Date(date);
	        
	     
	        //convert Date to LocalDate
	        LocalDateTime ldt = date_converted.toInstant()
	                .atZone( ZoneId.systemDefault() )
	                .toLocalDateTime();
	        
	        return ldt;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	   
	 }
	 
	 public static long random(long begin, long end) {
	    long rtn = begin + (long) (Math.random() * (end - begin));
	    if (rtn == begin || rtn == end) {
	        return random(begin, end);
	    }
	    return rtn;
	}
	 
	 
	   
}