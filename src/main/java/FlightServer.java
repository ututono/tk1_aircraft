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

	private List<Flight> flights;
	
	private List<IFlightClient> clients;
	


	protected FlightServer() {
		super();
		flights=new ArrayList<Flight>();
		clients=new ArrayList<IFlightClient>();
		int initFlightNum=10;
		// initialize with some flights
		for (int i = 0; i < initFlightNum ; i++) {
			Flight flight=new Flight();
			flight.createFlightRandom();
			flights.add(flight);
		}

	}

	@Override
	public void login(String clientName, IFlightClient client) {
		try {
			client.receiveListOfFlights(flights);
			clients.add(client);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		logger.log(Level.INFO, "New client logged in: " + clientName);
		
	}
	

	@Override
	public void logout(String clientName) {
		try {
			for (int i = 0; i < clients.size(); i++) {
				if(clients.get(i).getClientname().equals(clientName)) {
					clients.remove(i);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		logger.log(Level.INFO, "Client logged out: " + clientName+". "+"There are "+clients.size()+" clients on server");

		
	}

	@Override
	public void updateFlight(String clientName, Flight flight) {
		int tag=0;
		for (int i = 0; i < flights.size(); i++) {
			if (flight.getIata().equals(flights.get(i).getIata())
					&& flight.getFlightNum().equals(flights.get(i).getFlightNum())) {
				//update an existing flight
				flights.set(i, flight);
				tag=1;
			}
		}
		if(tag==0) {
			//add a new flight
			flights.add(flight);
		}
		logger.log(Level.INFO, "Update flight: " + flight.toString());
//		//TODO delete test print loop
//		for (int i = 0; i < flights.size(); i++) {
//			System.out.println(flights.get(i).toString());
//		}
		
		informAllClients(flight, false);
	
		
	}

	@Override
	public void deleteFlight(String clientName, Flight flight) {
		logger.log(Level.INFO, "Delete flight: " + flight.toString());
		
		if(flights.remove(flight)) {
			logger.log(Level.INFO, "Delete successfully "+flights.size());
			informAllClients(flight, true);
			//TODO delete test print loop
			for (int i = 0; i < flights.size(); i++) {
				System.out.println(flights.get(i).toString());
			}
			
		}else {
			logger.log(Level.WARNING,"No this Flight!!");
		}
		
	}

	private void informAllClients(Flight flight, boolean deleted) {
			try {
				for(int i=0;i<clients.size();i++) {
					clients.get(i).receiveListOfFlights(flights);
					System.out.println(clients.get(i).toString());
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		
	}
	
	

	@Override
	public void test(String test) {
		int df;
		System.out.println(test+" test successfully!!!");
		
	}
	
	

	public static void main(String[] args) {
		try {
			// generate local registry

			// generate game server
			
			IFlightServer flightServer=new FlightServer();
			IFlightServer skeleton=(IFlightServer)UnicastRemoteObject.exportObject(flightServer, 0);
			Registry registry=LocateRegistry.createRegistry(flightServer.PORT);
			registry.rebind(flightServer.SERVICENAME, skeleton);
			

			logger.info("Server is ready");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	}

}
