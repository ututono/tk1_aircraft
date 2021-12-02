package controllerInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Flight;
import model.ShoppingCart;
import model.Ticket;
import model.User;

public interface BookingServcie {
	
	// TODO: interactive with gui
	
	public void addtoCart(Flight flight,int rownum,String seatnum);
	

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
	 * To satisfy the requirement, A client can book one seat for one plane.
	 * @param flight which plane is booked
	 * @param client who book the plane
	 * @return true : One has already book a seat. false:no
	 */
	public boolean alreadybook(Flight flight, User client);
	
	public void reserve();
	
	public LinkedList<Ticket> geTickets();
	
	
	public ShoppingCart getCart();
	
	

}
