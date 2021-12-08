import clients.SoapClient;
import model.Flight;
import model.User;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		User user1=new User("redGuy", 36);
		User user2=new User("blueGuy", 58);
		SoapClient soapClient=new SoapClient(user1);
		Flight flight=soapClient.getFlightfromServer().get(2);
		System.out.println(flight.toString()+"\n The size of seats is "+ flight.getSeats().get(0).size());
	}
}
