package controllerInterface;

import java.util.LinkedList;

import model.Seat;

public interface FlightsManager {
	
	boolean reserve(int rownum, String seatnumber);
	
	public int getSeatType(int rownum, String seatnumber);
	
	
}
