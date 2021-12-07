package model;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class AirBus319 extends Flight {

	private static final int ROWSUM = 35;
	public static final String FLIGHTYPE="AirBus319";
	
	public AirBus319(String type,String destination, String flightnumber, LocalDateTime departuretimel) {
		super(type,destination,flightnumber,departuretimel);
		super.setRowsum(ROWSUM);
		init();
	}
	
	public void init() {
		
		LinkedList<Seat> row=new LinkedList<Seat>();
		
		// First Class
		// Row 1-2 Seatnum A-B E-F
		String[] seatnumbers_F={"A","B","E","F"};
		int rowsum=2;
		for (int i = 0; i < rowsum; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_F.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_F[j], super.getFirstclass(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		// Empty rows from 3-6
		// TODO: how to identify empty row when show up on the GUI
		rowsum=4;
		for(int i=0;i<rowsum;i++) {
			row=new LinkedList<Seat>();
			super.addSeats(row);
		}
		
		// Economy Plus Class
		// Row 07-12
		rowsum=6;
		String[] seatnumbers_E = {"A","B","C","D","E","F"};
		for (int i = 0; i < rowsum; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconplus(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		// Empty rows from 13-19
		// TODO: how to identify empty row when show up on the GUI
		rowsum=7;
		for(int i=0;i<rowsum;i++) {
			row=new LinkedList<Seat>();
			super.addSeats(row);
		}
		
		// Economy Plus Class
		// Row 20-21
		rowsum=2;
		for (int i = 0; i < rowsum; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconplus(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		// Economy Class
		// Row 22-35
		rowsum=14;
		for (int i = 0; i < rowsum; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconomypclass(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		
		
	}
	
}
