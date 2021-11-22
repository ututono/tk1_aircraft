package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DataTransform {
	
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
}
