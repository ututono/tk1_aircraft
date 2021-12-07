package controllerInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import model.Flight;
import model.ShoppingCart;
import model.Ticket;
import model.User;

public interface BookingServcie {
	
	// TODO: interactive with gui
	
	public boolean addtoCart(Flight flight,int rownum,String seatnum,User user);
	
	//TODO : For test. Delete
	public static ArrayList<Flight> initFlights(int flights_sum){
		ArrayList<Flight> flights=new ArrayList<Flight>();
		Flight flight=Flight.init("Boeing737","NewYork","HS289",LocalDateTime.now());
		Flight flight1=Flight.init("Boeing737","Jersy","AB123",LocalDateTime.now());
		Flight flight2=Flight.init("Boeing737","Berlin","CA123",LocalDateTime.now());
		flights.add(flight2);
		flights.add(flight1);
		flights.add(flight);
		return flights;
	}
	
	/**
	 * to Check whether the user has already booked or reserved a seat in the same plane 
	 * @param flight which plane is booked
	 * @param client who book the plane
	 * @return true : One has already book a seat. false:no
	 */
	public boolean alreadybook(Flight flight, User client);
	
	public boolean reserve(User user);
	
	public ArrayList<Ticket> geTickets(User user);
	
	public ArrayList<Flight> getAllFlights();
	
	ArrayList<String> getDestinations();
	
	ArrayList<Date> getWeek();
	
	void login(String username,int age);
	
	ShoppingCart getCart(User user);
	
	ArrayList<Flight> getFlightsOnDayandDest(String destination, Date date);

}
