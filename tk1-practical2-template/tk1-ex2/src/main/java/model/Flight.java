package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

import org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram;
import utilss.*;

/**
 * 
 * @author ututono
 * This class is to manage seat for different flight type
 * Main key is {flight type}
 *
 */
public class Flight {
	
	private String flightType;
	private String destination;
	private String flightnumber;
	private LocalDateTime departuretime;
	private LinkedList<LinkedList<Seat>> seats=new LinkedList<LinkedList<Seat>>();
	private int rowsum;
	private static final int FIRSTCLASS=3;
	private static final int ECONPLUS=2;
	private static final int ECONOMYPCLASS=1;
	
	public Flight() {
		this.seats=new LinkedList<LinkedList<Seat>>();
	}
	
	public Flight(String type,String destination, String flightnumber, LocalDateTime departuretimel) {
		this.seats=new LinkedList<LinkedList<Seat>>();
		setFlightType(type);
		setDestination(destination);
		setDeparturetime(departuretimel);
		setFlightnumber(flightnumber);
	}
	
	public static Flight init(String type,String destination, String flightnumber, LocalDateTime departuretimel) {
		if (type.equals("Boeing737")) {
			Boeing737 boeing737=new Boeing737(type,destination,flightnumber,departuretimel);
			return boeing737;
		}else if (type.equals("AirBus319")) {
			AirBus319 airBus319=new AirBus319(type, destination, flightnumber, departuretimel);
			return airBus319;
		}else if (type.equals("EmbraerE170")) {
			EmbraerE170 embraerE170=new EmbraerE170(type, destination, flightnumber, departuretimel);
			return embraerE170;
		}
		return null;
	}
	
	
	
	public int getRowsum() {
		return rowsum;
	}

	public void setRowsum(int rowsum) {
		this.rowsum = rowsum;
	}

	public static int getFirstclass() {
		return FIRSTCLASS;
	}

	public LinkedList<LinkedList<Seat>> getSeats() {
		return seats;
	}

	public void setSeats(LinkedList<LinkedList<Seat>> seats) {
		this.seats = seats;
	}
	public void addSeats(LinkedList<Seat> row) {
		seats.add(row);
	}

	public static int getEconplus() {
		return ECONPLUS;
	}

	public static int getEconomypclass() {
		return ECONOMYPCLASS;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public LocalDateTime getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(LocalDateTime departuretime) {
		this.departuretime = departuretime;
	}


	
	@Override
	public int hashCode() {
		return Objects.hash(departuretime, destination, flightType, flightnumber, rowsum, seats);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(departuretime, other.departuretime) && Objects.equals(destination, other.destination)
				&& Objects.equals(flightType, other.flightType) && Objects.equals(flightnumber, other.flightnumber)
				&& rowsum == other.rowsum;
	}

	

	 
	

}
