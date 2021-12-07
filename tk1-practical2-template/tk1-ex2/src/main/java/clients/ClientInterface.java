package clients;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Flight;
import model.ShoppingCart;
import model.Ticket;
import model.User;

public interface ClientInterface {

	ArrayList<Flight> getFlightfromServer();
	
	void addtoCart(User client,String flightnum, int rownum, String seatnum);
	
	ShoppingCart getMyCart();
	
	void buynow();
	
	void startup();
	
	ArrayList<Ticket> getTicket();
	
	
}
