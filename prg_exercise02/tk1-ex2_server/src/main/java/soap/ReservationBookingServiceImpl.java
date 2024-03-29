package soap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;

import controllerImpl.BookingServiceImpl;
import controllerInterface.BookingServcie;
import model.Flight;
import model.Seat;
import model.ShoppingCart;
import model.Ticket;
import model.User;
import utilss.Transformer;

//Service Implementation
@WebService(endpointInterface = "soap.ReservationBookingServiceInterface")

public class ReservationBookingServiceImpl implements ReservationBookingServiceInterface {

	private User client;
	private BookingServcie bookingServcie;
	private Logger logger=Logger.getLogger(ReservationBookingServiceImpl.class.getName());
	
	
	public ReservationBookingServiceImpl() {
		bookingServcie=new BookingServiceImpl();
	}

	@Override
	public String getHelloWorldAsString(String name) {

		return "hello world"+name;
	}

	@Override
	public ArrayList<String> getDestinations() {
		return bookingServcie.getDestinations();
	}

	@Override
	public ArrayList<Date> getDates() {
		return bookingServcie.getWeek();
	}


	@Override
	public ArrayList<Flight> getallFlights() {
		return bookingServcie.getAllFlights();
	}
	
	@Override
	public boolean addCart(User user, String flightnum, int rownum, String seatnum) {
		ArrayList<Flight> flihts=bookingServcie.getAllFlights();
		for (Iterator iterator = flihts.iterator(); iterator.hasNext();) {
			Flight flight = (Flight) iterator.next();
			if(flight.getFlightnumber().equals(flightnum)) {
				return bookingServcie.addtoCart(flight, rownum, seatnum,user);
			}
		}
		logger.log(Level.WARNING, "Flight Not Found Error!");
		return false;
	}


	@Override
	public boolean purchase(User user) {
		if(bookingServcie.reserve(user)) {
			return true;
		}
		return false;
	}

	@Override
	public ShoppingCart getmyCart(User user) {
		return bookingServcie.getCart(user);
	}

	@Override
	public ArrayList<Ticket> geTickets(User user) {
		return bookingServcie.geTickets(user);
	}

	@Override
	public ArrayList<Flight> getFlightsOnDayandDest(String destination, Date date) {
		return bookingServcie.getFlightsOnDayandDest(destination, date);
	}

	@Override
	public HashMap<String, ArrayList<ArrayList<Seat>>> getSeats() {
		ArrayList<Flight> flights=bookingServcie.getAllFlights();
		HashMap<String, ArrayList<ArrayList<Seat>>> seatsMap=
				new HashMap<String, ArrayList<ArrayList<Seat>>>();
		for (Iterator iterator = flights.iterator(); iterator.hasNext();) {
			Flight flight = (Flight) iterator.next();
			seatsMap.put(flight.getFlightnumber(), flight.getSeats());
		}
		return seatsMap;
	}

	
	
	@Override
	public ArrayList<ArrayList<Seat>> getSeatsFromFlight(String flightnum) {
		ArrayList<Flight> flights=bookingServcie.getAllFlights();
		for (Iterator iterator = flights.iterator(); iterator.hasNext();) {
			Flight flight = (Flight) iterator.next();
			if (flight.getFlightnumber().equals(flightnum)) {
				return flight.getSeats();
			}
		}
		return null;
	}
	
//
//	@Override
//	public LinkedList<Flight> getFlightsForDesAndDate(String destination, LocalDateTime depaturetime) {
//		return null;
//	}




	
	
	
	
	

}