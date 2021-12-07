package soap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.glassfish.jersey.internal.guava.Ticker;

import model.Flight;
import model.Seat;
import model.ShoppingCart;
import model.Ticket;
import model.User;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface ReservationBookingServiceInterface {

	@WebMethod String getHelloWorldAsString(String name);
	
	@WebMethod ArrayList<String> getDestinations();
	
	@WebMethod ArrayList<Date> getDates();
	
	@WebMethod ArrayList<Flight> getallFlights();
	
	@WebMethod HashMap<String, ArrayList<ArrayList<Seat>>> getSeats();
	
	@WebMethod ArrayList<ArrayList<Seat>> getSeatsFromFlight(String flightnum);
	
	@WebMethod ArrayList<Flight> getFlightsOnDayandDest(String destination, Date date);
	
	@WebMethod boolean addCart(User user, String flightnum, int rownum, String seatnum);

	@WebMethod boolean purchase(User user);
	
	@WebMethod ShoppingCart getmyCart(User user);
	
	@WebMethod ArrayList<Ticket> geTickets(User user);
	
	//@WebMethod ArrayList<Flight> getFlightsForDesAndDate(String destination, LocalDateTime depaturetime)
	
}
