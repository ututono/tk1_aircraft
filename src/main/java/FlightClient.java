import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightClient implements IFlightClient {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());

	// ui

	// global state

	public FlightClient(String clientName) {
		
	}

	@Override
	public void receiveListOfFlights(List<Flight> flights) {
		logger.log(Level.INFO, "List of flights received: " + flights.size());
	}

	@Override
	public void receiveUpdatedFlight(Flight flight, boolean deleted) {
		logger.log(Level.INFO, "Flight updated: " + flight.toString());
	}

	public void startup() {
		try {
			Registry registry=LocateRegistry.getRegistry("localhost",1099);
			IFlightServer stub=(IFlightServer) registry.lookup("update");
			stub.test("Hello,world");
			Flight flight=new Flight();
			flight.createFlightRandom();
			stub.updateFlight("dsfiajfioajsf", flight);
//			System.out.println("***************************************************************");
			stub.deleteFlight("dsfiajfioajsf", flight);
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) {
		FlightClient client = new FlightClient(UUID.randomUUID().toString());
		client.startup();
		
	}

}
