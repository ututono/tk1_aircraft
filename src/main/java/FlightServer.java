import java.beans.beancontext.BeanContextServiceRevokedListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightServer implements IFlightServer {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());

	private List<Flight> flights=new ArrayList<Flight>();

	protected FlightServer() {
		super();

		int initFlightNum=10;
		// initialize with some flights
		for (int i = 0; i < initFlightNum ; i++) {
			Flight flight=new Flight();
			flight.createFlightRandom();
			flights.add(flight);
		}

	}

//	@Override
//	public void login(String clientName, IFlightClient client) {
//		logger.log(Level.INFO, "New client logged in: " + clientName);
//	}
//
//	@Override
//	public void logout(String clientName) {
//		logger.log(Level.INFO, "Client logged out: " + clientName);
//	}
//
	@Override
	public void updateFlight(String clientName, Flight flight) {
		logger.log(Level.INFO, "Update flight: " + flight.toString());
		flights.add(flight);
		System.out.println(clientName+"update successfully");
		for (int i = 0; i < flights.size(); i++) {
			System.out.println(flights.get(i).toString());
		}
		
	}

	@Override
	public void deleteFlight(String clientName, Flight flight) {
		logger.log(Level.INFO, "Delete flight: " + flight.toString());
		int loc=-1;
		for (int i = 0; i < flights.size(); i++) {
			if (flight.toString().equals(flights.get(i).toString())) {
				loc=i;
			}
		}

		if (loc>0) {
			flights.remove(loc);
			logger.log(Level.INFO, "Delete successfully ");
		}else {
			logger.log(Level.WARNING,"No this Flight!!");
		}
	}

	private void informAllClients(Flight flight, boolean deleted) {
		
	}
	
	

	@Override
	public void test(String test) {
		// TODO Auto-generated method stub
		int df;
		System.out.println(test+" test successfully!!!");
		
	}

	public static void main(String[] args) {
		try {
			// generate local registry

			// generate game server
			
			IFlightServer flightServer=new FlightServer();
			IFlightServer skeleton=(IFlightServer)UnicastRemoteObject.exportObject(flightServer, 0);
			Registry registry=LocateRegistry.createRegistry(1099);
			registry.rebind("update", skeleton);
			

			logger.info("Server is ready");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	}

}
