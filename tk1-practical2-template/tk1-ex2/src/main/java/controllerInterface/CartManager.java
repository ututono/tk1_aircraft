package controllerInterface;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Reservation;
import model.Ticket;

public interface CartManager {
	
	public boolean addtoCart(Reservation reservation);
	
	public boolean deleteEntry(Reservation reservation);
	
	/**
	 * 
	 * @return a list of reservation that are failed to be executed.
	 */
	public LinkedList<Reservation> buynow();
	
	public LinkedList<Ticket> getTickets();
	
	public void deleteAll();

}
