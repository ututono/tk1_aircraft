package model;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class EmbraerE170 extends Flight {
	private static final int ROWSUM = 24;
	private static final String FLIGHTYPE="EmbraerE170";
	
	public EmbraerE170(String type,String destination, String flightnumber, LocalDateTime departuretimel) {
		super(type,destination,flightnumber,departuretimel);
		super.setRowsum(ROWSUM);
		init();
	}
	public void init() {
		
		LinkedList<Seat> row=new LinkedList<Seat>();
		
		// First Class
		// Row 1-2 Seatnum A C-D
		String[] seatnumbers_F={"A","C","D"};
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
		// Row 07-10
		rowsum=4;
		String[] seatnumbers_E = {"A","B","C","D"};
		for (int i = 0; i < rowsum; i++) {
			row=new LinkedList<Seat>();
			for(int j=0;j<seatnumbers_E.length;j++) {
				Seat seat=new Seat();
				seat.init(seatnumbers_E[j], super.getEconplus(), false);
				row.add(seat);
			}
			super.addSeats(row);
		}
		
		// Economy  Class
		// Row 11-24
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
