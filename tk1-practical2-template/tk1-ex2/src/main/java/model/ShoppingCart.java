package model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

public class ShoppingCart {
	private User client;
	private LinkedList<Reservation> reservationsOrder;
	private LocalDateTime dateofReservation;
	
	public ShoppingCart(User client) {
		this.client=client;
		this.reservationsOrder=new LinkedList<Reservation>();
	}

	public String getClientID() {
		return client.getUsername();
	}
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public LinkedList<Reservation> getReservationsOrder() {
		return reservationsOrder;
	}

	public void setReservationsOrder(LinkedList<Reservation> reservationsOrder) {
		this.reservationsOrder = reservationsOrder;
	}

	public LocalDateTime getDateofReservation() {
		return dateofReservation;
	}

	public void setDateofReservation(LocalDateTime dateofReservation) {
		this.dateofReservation = dateofReservation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, dateofReservation, reservationsOrder);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(client, other.client) && Objects.equals(dateofReservation, other.dateofReservation)
				&& Objects.equals(reservationsOrder, other.reservationsOrder);
	}
	
	
	
	
}
