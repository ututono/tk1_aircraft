package controllerImpl;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

import controllerInterface.FlightsManager;
import model.Seat;
import model.Flight;
import model.Reservation;

public class FlightsmanagerImpl implements FlightsManager {

	Flight flight;
	
	public FlightsmanagerImpl(Flight flight) {
		this.flight=flight;
	}
	
	
	@Override
	public boolean reserve(int rownum, String seatnumber) {
		
		//check if row is a valid integer
		//TODO: to fix the mapping problem 1(logical)->0(index)  
		if (rownum<0 || rownum>=flight.getRowsum()) { 
			return false;
		}
		
		LinkedList<Seat> row=flight.getSeats().get(rownum);
		Seat seat=findSeatinRow(row, seatnumber);
		if (seat!=null && seat.isStatus() ) { // the seat is free and not blank
			// reserve the seat
			seat.setStatus(false);
			return true;
		}else {
			return false;
		}
	}

	private Seat getSeat(int rownum, String seatnumber) {
		Seat seat=new Seat();
		if (rownum<0) {
			return null;
		}
		seat=findSeatinRow(flight.getSeats().get(rownum), seatnumber);
		return seat;
	}


	private Seat findSeatinRow(LinkedList<Seat> row, String seatnumber) {
		Seat seat=new Seat();
		for (Iterator iterator = row.iterator(); iterator.hasNext();) {
			seat = (Seat) iterator.next();
			if(seat.getSeatnumber().equals(seatnumber)) {
				return seat;
			}
		}
		return null;
	}


	@Override
	public int getSeatType(int rownum, String seatnumber) {
		return getSeat(rownum, seatnumber).getType();
	}

}
