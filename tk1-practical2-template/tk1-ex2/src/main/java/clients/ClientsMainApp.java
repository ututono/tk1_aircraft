package clients;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import model.Flight;
import model.ShoppingCart;
import model.User;
import soap.ReservationBookingServiceInterface;
public class ClientsMainApp {

	public static void main(String[] args) throws Exception {
		   
//		URL url = new URL("http://localhost:9999/aircraft?wsdl");
//		
//	        //1st argument service URI, refer to wsdl document above
//		//2nd argument is service name, refer to wsdl document above
//	        QName qname = new QName("http://soap/", "ReservationBookingServiceImplService");
//
//	        Service service = Service.create(url, qname);
//
//	        ReservationBookingServiceInterface hello = service.getPort(ReservationBookingServiceInterface.class);
//
//	        System.out.println(hello.getHelloWorldAsString("mkyong"));
		
		User user1=new User("redGuy", 36);
		User user2=new User("blueGuy", 58);
		SoapClient soapClient=new SoapClient(user1);
		Flight flight=soapClient.getFlightfromServer().get(2);
//		Flight flight3=soapClient.getFlightfromServer().get(3);
		System.out.println(flight.toString()+"\n The size of seats is "+ flight.getSeats().get(0).size());
//		ArrayList<Flight> flights=soapClient.getFlightfromServer();
//		soapClient.addtoCart(user1,flight.getFlightnumber(), 0, "A");
//		soapClient.addtoCart(user1,flight3.getFlightnumber(), 0, "A");
//		soapClient.buynow();
		
		
	}
}
