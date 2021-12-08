
import java.util.ArrayList;
import java.util.Date;
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
	
	ArrayList<String> getDestinations();
	
	ArrayList<Date> getDates();
	
	void initCustomerConfigView();
	
	void initFlightSectView(String destination, Date date);
	
	void initSeatBookingView(Flight flight);
	
	
}
