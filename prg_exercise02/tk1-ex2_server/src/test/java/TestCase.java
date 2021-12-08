import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import controllerImpl.BookingServiceImpl;
import controllerImpl.CartManagerImpl;
import controllerImpl.FlightsmanagerImpl;
import controllerInterface.BookingServcie;
import controllerInterface.CartManager;
import controllerInterface.FlightsManager;
import model.AirBus319;
import model.Flight;
import model.Reservation;
import model.ShoppingCart;
import model.User;

class TestCase {


@Test
public void reserverandflightTest() {
	Flight boeing=Flight.init("Boeing737","NewYork","HS289",LocalDateTime.now());
	Flight airbus=Flight.init("AirBus319", "ToKyo", "AB789", LocalDateTime.now());
	Flight embraer=Flight.init("EmbraerE170", "Shanghai", "EE456", LocalDateTime.now());
	
	
	FlightsManager flightsManagerBoeing=new FlightsmanagerImpl(boeing);
	assertTrue(flightsManagerBoeing.reserve(0,"A"));
	assertTrue(flightsManagerBoeing.reserve(0,"B"));
	assertTrue(flightsManagerBoeing.reserve(24,"B"));
	assertTrue(flightsManagerBoeing.reserve(27,"E"));
	assertTrue(flightsManagerBoeing.reserve(38,"C"));
	assertFalse(flightsManagerBoeing.reserve(0,"C"));
	assertFalse(flightsManagerBoeing.reserve(50,"A"));
	assertFalse(flightsManagerBoeing.reserve(-1,"A"));
	assertFalse(flightsManagerBoeing.reserve(0, "A"));	//repeat reserve
	assertFalse(flightsManagerBoeing.reserve(0,"G"));
	assertFalse(flightsManagerBoeing.reserve(5,"A")); // non-existence row and seat
	
	
	FlightsManager flightsManagerAirBus=new FlightsmanagerImpl(airbus);
	assertTrue(flightsManagerAirBus.reserve(0,"A"));
	assertTrue(flightsManagerAirBus.reserve(6,"D"));
	assertTrue(flightsManagerAirBus.reserve(30,"A"));
	assertEquals(35, airbus.getSeats().size());
	assertFalse(flightsManagerAirBus.reserve(35,"A"));
	assertFalse(flightsManagerAirBus.reserve(0,"C"));
	assertFalse(flightsManagerAirBus.reserve(50,"A"));
	assertFalse(flightsManagerAirBus.reserve(-1,"A"));
	assertFalse(flightsManagerAirBus.reserve(0, "A"));
	assertFalse(flightsManagerAirBus.reserve(0,"G"));
	assertFalse(flightsManagerAirBus.reserve(4,"A"));
	
	FlightsManager flightsManagerEmbraer=new FlightsmanagerImpl(embraer);
	assertTrue(flightsManagerEmbraer.reserve(0,"A"));
	assertTrue(flightsManagerEmbraer.reserve(6,"D"));
	assertFalse(flightsManagerEmbraer.reserve(30,"A"));
	assertEquals(24, embraer.getSeats().size());
	assertTrue(flightsManagerEmbraer.reserve(0,"C"));
	assertFalse(flightsManagerEmbraer.reserve(24,"C"));
	assertFalse(flightsManagerEmbraer.reserve(50,"A"));
	assertFalse(flightsManagerEmbraer.reserve(-1,"A"));
	assertFalse(flightsManagerEmbraer.reserve(0, "A"));
	assertFalse(flightsManagerEmbraer.reserve(0,"G"));
	assertFalse(flightsManagerEmbraer.reserve(4,"A"));
}


/**
 * test service provided to client :
 * Add to Cart
 * Reserve
 * Get Ticket
 */
//@Test
//public void cartTest() {
//	User client=new User("ututono", 18);
//	ArrayList<Flight> flights=BookingServcie.initFlights(3);
//	Flight flight=flights.get(0);
//	BookingServcie bookingServcie=new BookingServiceImpl(client);
//	bookingServcie.addtoCart(flight, 0, "A");
//
//	
//	Reservation res=new Reservation(flight, 0, "A");
//	ShoppingCart testCart=new ShoppingCart(client);
//	CartManager cartManager=new CartManagerImpl(testCart);
//	cartManager.addtoCart(res);
//
//	
//	assertEquals(testCart, bookingServcie.getCart());
//	
//	bookingServcie.reserve();
//	FlightsManager flightsManager=new FlightsmanagerImpl(flight);
//	
//	
//	assertFalse(flightsManager.reserve(0, "A"));
//}
//
//@Test
//public void oneUser4onePlaneTest() {
//	User client=new User("ututono", 18);
//	ArrayList<Flight> flights=BookingServcie.initFlights(3);
//	Flight flight=flights.get(0);
//	Flight flight2=flights.get(1);
//	
//	BookingServcie bookingServcie=new BookingServiceImpl(client);
//	bookingServcie.addtoCart(flight, 0, "A");
//	bookingServcie.reserve();
//	assertTrue(bookingServcie.alreadybook(flight, client));
//	bookingServcie.alreadybook(flight2, client);
//	assertFalse(bookingServcie.alreadybook(flight2, client));
//	
//	
//}

public static void main(String[] args) {

}


}
