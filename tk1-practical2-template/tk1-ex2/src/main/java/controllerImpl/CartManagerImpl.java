/**
 * 
 */
package controllerImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import controllerInterface.CartManager;
import controllerInterface.FlightsManager;
import model.Reservation;
import model.ShoppingCart;
import model.Ticket;

/**
 * @author ututono
 *
 */
public class CartManagerImpl implements CartManager {

	private ShoppingCart cart;
	private LinkedList<Ticket> tickets;
	private ArrayList<String> destinations;
	private ArrayList<Integer> pricebase;
	
	public CartManagerImpl(ShoppingCart cart) {
		this.cart=cart;
		tickets=new LinkedList<Ticket>();
		destinations=new ArrayList<String>(Arrays.asList("NewYork","Tokyo","Berlin"));
		pricebase=new ArrayList<Integer>(Arrays.asList(300,60,5000));
	}
	@Override
	public boolean addtoCart(Reservation reservation) {
		try {
			LinkedList<Reservation> reservationsList=cart.getReservationsOrder();
			reservationsList.add(reservation);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Override
	public boolean deleteEntry(Reservation reservation) {
		LinkedList<Reservation> reservationsList=cart.getReservationsOrder();
		for (Iterator iterator = reservationsList.iterator(); iterator.hasNext();) {
			Reservation res = (Reservation) iterator.next();
			if (res.equals(reservation)) {
				return reservationsList.remove(res);
			}
		}
		return true;
	}
	
	@Override
	public LinkedList<Reservation> buynow() {
		LinkedList<Reservation> reservationsList=cart.getReservationsOrder();
		LinkedList<Reservation> failedreservation=new LinkedList<Reservation>();
		for (Iterator iterator = reservationsList.iterator(); iterator.hasNext();) {
			Reservation reservation = (Reservation) iterator.next();
			FlightsManager flightsManager=new FlightsmanagerImpl(reservation.getFlight());
			if (!flightsManager.reserve(reservation.getSeatRow(), reservation.getSeatNum())) {
				//IF failed
				failedreservation.add(reservation);
			}else {
				Ticket ticket=new Ticket();
				ticket.setReservation(reservation);
				ticket.setClientname(cart.getClientID());
				ticket.setPrice(cal_Price(reservation));
				tickets.add(ticket);
			}
		}
		return failedreservation;
		
	}
	
	private float cal_Price(Reservation res) {
		String destination=res.getFlight().getDestination();
		int seatype=res.getSeatype();
		float price=(float) 0.0;
		for (int i = 0; i < destinations.size(); i++) {
			if (destination.equals(destinations.get(i))) {
				price=(float)pricebase.get(i);
			}
		}
		price=price*seatype;
		return price;
	}
	
	public LinkedList<Ticket> getTickets() {
		return tickets;
	}
	@Override
	public void deleteAll() {
		cart.getReservationsOrder().clear();
	}

}
