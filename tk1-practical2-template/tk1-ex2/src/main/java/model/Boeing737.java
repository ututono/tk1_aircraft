package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Boeing737 extends Flight {

	private static final int ROWSUM = 39;
	public static final String FLIGHTYPE="Boeing737";
	
	public Boeing737() {
		super();
		super.setRowsum(ROWSUM);
		
	}
	
	public Boeing737(String type,String destination, String flightnumber, LocalDateTime departuretimel) {
		super(type,destination,flightnumber,departuretimel);
		super.setRowsum(ROWSUM);
		init();
	}

	public void init() {
		LinkedList<Seat> row=new LinkedList<Seat>();
		
		// United First Seat
		// row 1-5; A-B E-F
		String[] seatnumbers_F={"A","B","E","F"};
		for (int i = 0; i < 5; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_F.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_F[j], super.getFirstclass(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		// Empty row no.6
		row=new LinkedList<Seat>();
		super.addSeats(row);
		
		// Economy Plus
		// from row 07D to 12C
		String[] seatnumbers_E = {"A","B","C","D","E","F"};
		for (int i = 3; i < seatnumbers_E.length; i++) {
			Seat seat=new Seat();
			seat.init(seatnumbers_E[i], super.getEconplus(), false);
			row.add(seat);
		}
		super.addSeats(row);
		for (int i = 0; i < 4; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconplus(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		row=new LinkedList<Seat>();
		for (int i = 0; i < 3; i++) {
			Seat seat=new Seat();
			seat.init(seatnumbers_E[i], super.getEconplus(), false);
			row.add(seat);
		}
		super.addSeats(row);
		
		
		//Economy Class
		// From 12D to the whole 15
		row=new LinkedList<Seat>();
		for (int i = 3; i < 3; i++) {
			Seat seat=new Seat();
			seat.init(seatnumbers_E[i], super.getEconplus(), false);
			row.add(seat);
		}
		super.addSeats(row);
		
		for (int i = 0; i < 2; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconomypclass(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		// Empty rows from 16-19
		// TODO: how to identify empty row when show up on the GUI
		for(int i=0;i<4;i++) {
			row=new LinkedList<Seat>();
			super.addSeats(row);
		}
		
		
		
		// Exit Row and EP 
		// From 20A - 21F
		for (int i = 0; i < 2; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconplus(), true);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		// Economy Class
		// From 22A - 39F
		for (int i = 0; i < 18; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconplus(), true);
				row.add(seat);
			}
			super.addSeats(row);
		}
	
	}

}
