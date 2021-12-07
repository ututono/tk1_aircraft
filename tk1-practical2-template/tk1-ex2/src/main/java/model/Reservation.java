/**
 * 
 */
package model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import controllerImpl.FlightsmanagerImpl;
import controllerInterface.FlightsManager;
import utilss.RandomGenerator;

/**
 * @author ututono
 * This class is to record each order. i.e. what a customer select
 * Main key is {flight number, seat number,data and time of the reserved flight }
 */
@XmlRootElement
public class Reservation {
	private String flightnumber;
	private Flight flight;
	private int seatRow;
	private String seatNum;
	private int seatype;
	private LocalDateTime flightdate;
	private String meal;
	
	public Reservation() {
	}
	
	public Reservation(Flight flight,int row,String seatnum) {
		this.flightnumber=flight.getFlightnumber();
		this.seatNum=seatnum;
		this.seatRow=row;
		this.flightdate=flight.getDeparturetime();
		this.flight=flight;
		FlightsManager flightsManager=new FlightsmanagerImpl(flight);
		this.seatype=flightsManager.getSeatType(row, seatnum);
		setrandomMeal();
	}



	@Override
	public int hashCode() {
		return Objects.hash(flight, flightdate, flightnumber, meal, seatNum, seatRow, seatype);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(flight, other.flight) && Objects.equals(flightdate, other.flightdate)
				&& Objects.equals(flightnumber, other.flightnumber) && Objects.equals(meal, other.meal)
				&& Objects.equals(seatNum, other.seatNum) && seatRow == other.seatRow && seatype == other.seatype;
	}



	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public LocalDateTime getFlightdate() {
		return flightdate;
	}

	public void setFlightdate(LocalDateTime flightdate) {
		this.flightdate = flightdate;
	}

	public void setrandomMeal() {
		String[] meals= {"Standard","Vegetarian","Vegan"};
		meal=meals[RandomGenerator.getRandomNumber(0, 2)];
	}
	
	public String getMeal() {
		return meal;
	}
	
	public int getSeatype() {
		return seatype;
	}

	public void setSeatype(int seatype) {
		this.seatype = seatype;
	}



	@Override
	public String toString() {
		return "Reservation [flightnumber=" + flightnumber + ", flight=" + flight + ", seatRow=" + seatRow
				+ ", seatNum=" + seatNum + ", seatype=" + seatype + ", flightdate=" + flightdate + ", meal=" + meal
				+ "]";
	}
	
	
	
	
}
