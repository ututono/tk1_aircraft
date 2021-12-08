package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ticket {
	private String ticketID;
	private String clientname;
	private Reservation reservation;
	private float price;
	private LocalDateTime dateofticket;
	
	public Ticket() {
		this.dateofticket=LocalDateTime.now();
		this.ticketID=UUID.randomUUID().toString();
	}

	public String getTicketID() {
		return ticketID;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDateTime getDateofticket() {
		return dateofticket;
	}

	private void setDateofticket(LocalDateTime dateofticket) {
		this.dateofticket = dateofticket;
	}

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ", clientname=" + clientname + ", reservation=" + reservation
				+ ", price=" + price + ", dateofticket=" + dateofticket + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientname, dateofticket, price, reservation, ticketID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(clientname, other.clientname) && Objects.equals(dateofticket, other.dateofticket)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(reservation, other.reservation) && Objects.equals(ticketID, other.ticketID);
	}
	
	
	
	
}
