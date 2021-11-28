package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Flight;

public interface IFlightClient extends Serializable,Remote{
	
	public static final String SERVICENAME="TKAirport";
	public static final int PORT=10999;
	public static final String HOSTNAME="localhost";
	
	public String getClientname() throws RemoteException;
	public void receiveListOfFlights(List<Flight> flights) throws RemoteException;
	
	public void receiveUpdatedFlight(Flight flight, boolean deleted) throws RemoteException;
	

}
