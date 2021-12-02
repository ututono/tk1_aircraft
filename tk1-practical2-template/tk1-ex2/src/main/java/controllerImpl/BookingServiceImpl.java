package controllerImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import controllerInterface.BookingServcie;
import controllerInterface.CartManager;
import model.Flight;
import model.Reservation;
import model.ShoppingCart;
import model.Ticket;
import model.User;

public class BookingServiceImpl implements BookingServcie {
	private String clientname;
	private User user;
	private ShoppingCart myCart;
	private ArrayList<Flight> flights=new ArrayList<Flight>();
	private LinkedList<Ticket> tickets;
	
	public BookingServiceImpl(User user) {
		this.user=user;
		this.clientname=user.getUsername();
		tickets=new LinkedList<Ticket>();
		myCart=new ShoppingCart(user);
	}
	
	@Override
	public void addtoCart(Flight flight,int rownum,String seatnum) {
		CartManager cartmanager=new CartManagerImpl(myCart);
		Reservation reservation = new Reservation(flight,rownum,seatnum);
		
		//To judge if client book only a seat for a plane
		if (alreadybook(flight, user)) {
			//TODO:This booking is denied
		}else {
			cartmanager.addtoCart(reservation);
		}

		
		
	}
	

	public boolean alreadybook(Flight flight, User client) {
		if (tickets.size()==0) {
			return false;
		}
		for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			if (ticket.getClientname()==client.getUsername()
				&& ticket.getReservation().getFlight().equals(flight)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void reserve() {
		CartManager cartmanager=new CartManagerImpl(myCart);
		cartmanager.buynow();
		LinkedList<Ticket>cartTickets=cartmanager.getTickets();
		cartmanager.deleteAll();
		for (Iterator iterator = cartTickets.iterator(); iterator.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			tickets.add(ticket);
		}
	}

	@Override
	public LinkedList<Ticket> geTickets() {
		return tickets;
	}

	@Override
	public ShoppingCart getCart() {
		return myCart;
	}

	
	
}
