

import javax.jws.WebService;

import soap.ReservationBookingServiceImpl;
import javax.xml.ws.Endpoint;
 
//Service Implementation
@WebService(endpointInterface = "soap.ReservationBookingServiceInterface")


public class ServerMain {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/aircraft", new ReservationBookingServiceImpl());
	}

}