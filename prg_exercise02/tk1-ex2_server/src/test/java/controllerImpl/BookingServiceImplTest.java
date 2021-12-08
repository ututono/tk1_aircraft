package controllerImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import model.Flight;
import model.Seat;
import model.User;
import soap.ReservationBookingServiceImpl;
import soap.ReservationBookingServiceInterface;
import utilss.RandomGenerator;

class BookingServiceImplTest {

	public static void main(String[] args) {
		ReservationBookingServiceInterface resBooking=new ReservationBookingServiceImpl();
		User client=new User("utotono",22);
		Flight flight =resBooking.getallFlights().get(0);
		System.out.println(flight+"\n The size of seats is "+flight.getSeats().size());
		resBooking.addCart(client, flight.getFlightnumber(), 0, "A");
		resBooking.purchase(client);
		
		
//		ArrayList<Date> days= RandomGenerator.getCohDays("2023-5-20", 7);
//		Date day=days.get(0);
//		
//		for(int i=0;i<10;i++) {
//			System.out.println(RandomGenerator.getTimeOnDay(day));
//		}
		
	}
	
//	@Test
//	public void serilaizeTest() {
//		ReservationBookingServiceInterface resBooking=new ReservationBookingServiceImpl();
//		User client=new User("utotono",22);
//		Flight flight =resBooking.getallFlights().get(0);
//		byte[] bytes=serialize(flight.getSeats());
//		LinkedList<LinkedList<Seat>> output=deserialize(bytes);
//		assertEquals(flight.getSeats(), output);
//		assertEquals(flight.getSeats().get(0).get(0), output.get(0).get(0));
//
//    }
//	
	  public byte[] serialize(LinkedList<LinkedList<Seat>> object) {
	    // TODO: implement this method
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
	  
	  public LinkedList<LinkedList<Seat>> deserialize(byte[] data) {
	    // TODO: implement this method
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
