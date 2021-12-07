package controllerImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Flight;
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
		System.out.println(resBooking.geTickets(client).get(0).toString());
		
//		ArrayList<Date> days= RandomGenerator.getCohDays("2023-5-20", 7);
//		Date day=days.get(0);
//		
//		for(int i=0;i<10;i++) {
//			System.out.println(RandomGenerator.getTimeOnDay(day));
//		}
		
	}

}
