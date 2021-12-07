package utilss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.LinkedList;

import model.Seat;

public class Transformer {
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
				Date date=(Date) format.parse(str);
				  //convert Date to LocalDate
		        LocalDateTime ldt = date.toInstant()
		                .atZone( ZoneId.systemDefault() )
		                .toLocalDateTime();
		        return ldt;
			} catch (ParseException e1) {
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
	
	public static LocalDateTime dateToLocalDateTime(Date date) {
		  //convert Date to LocalDate
        LocalDateTime ldt = date.toInstant()
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        return ldt;
	}
	
	public static Date localDateTimeTODate(LocalDateTime ldt) {
		 return java.util.Date
			      .from(ldt.atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	
	public static String DateToString(Date date, int flag) {
		switch (flag) {
		case ISO_LOCAL_DATE:
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date);
			
		case ORIGIN_LDT:
			format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			return format.format(date);

		default:
			return null;
		}
	}
	
	public static Date stringToDate(String str) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date=(Date) format.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
				
	}
	
	  public static byte[] serialize(LinkedList<LinkedList<Seat>> object) {
		    byte[] bytes=null;
		    ByteArrayOutputStream bos=new ByteArrayOutputStream();
		    try{
		      ObjectOutputStream oos=new ObjectOutputStream(bos);
		      oos.writeObject(object);
		      oos.flush();
		      bytes=bos.toByteArray();
		      oos.close();
		      bos.close();
		    }catch (IOException ex){
		      ex.printStackTrace();
		    }
		    return bytes;
		  }
	  
	  
	  public static LinkedList<LinkedList<Seat>> deserialize(byte[] data) {
		    Object obj=null;
		    try{
		      ByteArrayInputStream bis=new ByteArrayInputStream(data);
		      ObjectInputStream ois=new ObjectInputStream(bis);
		      obj=ois.readObject();
		      ois.close();
		      bis.close();
		    }catch (IOException ex){
		      ex.printStackTrace();
		    }catch (ClassNotFoundException ex){
		      ex.printStackTrace();
		    }
		    return (LinkedList<LinkedList<Seat>>) obj;
		  }
}
