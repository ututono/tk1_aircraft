/**
 * 
 */
package controllerImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import controllerInterface.CartManager;
import controllerInterface.FlightsManager;
import model.Reservation;
import model.ShoppingCart;
import model.Ticket;

/**
 * @author ututono
 * CartManager depends on user, i.e. each user owns a cartmanger.
 */
public class CartManagerImpl implements CartManager {

	private ShoppingCart cart;
	private ArrayList<Ticket> tickets;
	
	//TODO: destination and pricebase to be editable
	public ArrayList<String> destinations;
	public ArrayList<Integer> pricebase;
	
	public CartManagerImpl(ShoppingCart cart) {
		this.cart=cart;
		tickets=new ArrayList<Ticket>();
	}
	
	
		
	@Override
	public boolean addtoCart(Reservation reservation) {
		try {
			LinkedList<Reservation> reservationsList=cart.getReservationsOrder();
			
			// Check whether the reservation (same flight) existed in the cart.
			for (Iterator iterator = reservationsList.iterator(); iterator.hasNext();) {
				Reservation res = (Reservation) iterator.next();
				if (res.getFlightnumber().equals(reservation.getFlightnumber())
						&& res.getFlightdate().equals(reservation.getFlightdate())) {
					return true;
				}
			}
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
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	@Override
	public void deleteAll() {
		cart.getReservationsOrder().clear();
	}



	public ArrayList<String> getDestinations() {
		return destinations;
	}



	public void setDestinations(ArrayList<String> destinations) {
		this.destinations = destinations;
	}



	public ArrayList<Integer> getPricebase() {
		return pricebase;
	}



	public void setPricebase(ArrayList<Integer> pricebase) {
		this.pricebase = pricebase;
	}


	
	
	

}
