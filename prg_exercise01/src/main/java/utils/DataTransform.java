package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DataTransform {
	
	/**
	 * format: 2018-11-03
	 */
	public static final int ISO_LOCAL_DATE=0;
	
	/**
	 * format: 2018-11-03T12:45:30
	 */
	public static final int ORIGIN_LDT=1;
	
	/**
	 * Convert string to Local data. If incoming string is not suitable for format, return null
	 * @param str
	 * @return
	 */
	public static LocalDateTime string2LocalDateTime(String str) {
		try {
			LocalDateTime ldt= LocalDateTime.parse(str);
			return ldt;
		} catch (DateTimeParseException |NullPointerException e) {
			// TODO: handle exception
			//e.printStackTrace();
			return null;
		}
	}
	
	public static LocalDateTime string2LocalDateTime(String str, int flag) {
		switch (flag) {
		case ISO_LOCAL_DATE:
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date=format.parse(str);
				  //convert Date to LocalDate
		        LocalDateTime ldt = date.toInstant()
		                .atZone( ZoneId.systemDefault() )
		                .toLocalDateTime();
		        return ldt;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
			
			
		case ORIGIN_LDT:
			try {
				LocalDateTime ldt= LocalDateTime.parse(str);
				return ldt;
			} catch (DateTimeParseException |NullPointerException e) {
				// TODO: handle exception
				//e.printStackTrace();
				return null;
			}

		default:
			return null;
		}

	}
}
