package clients;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import soap.ReservationBookingServiceInterface;
public class ClientsMainApp {

	public static void main(String[] args) throws Exception {
		   
		URL url = new URL("http://localhost:9999/aircraft?wsdl");
		
	        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://soap/", "ReservationBookingServiceImplService");

	        Service service = Service.create(url, qname);

	        ReservationBookingServiceInterface hello = service.getPort(ReservationBookingServiceInterface.class);

	        System.out.println(hello.getHelloWorldAsString("mkyong"));
	}
}
