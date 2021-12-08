package controllerImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram;
import org.apache.commons.math3.stat.inference.MannWhitneyUTest;

import controllerInterface.BookingServcie;
import controllerInterface.CartManager;
import model.AirBus319;
import model.Boeing737;
import model.EmbraerE170;
import model.Flight;
import model.Reservation;
import model.ShoppingCart;
import model.Ticket;
import model.User;
import utilss.RandomGenerator;
import utilss.Transformer;

@XmlRootElement
public class BookingServiceImpl implements BookingServcie {
	private String[] flightypes= {Boeing737.FLIGHTYPE,EmbraerE170.FLIGHTYPE,AirBus319.FLIGHTYPE};
	private ArrayList<Flight> flights;
	private ArrayList<String> destinations=new ArrayList<String>(Arrays.asList("NewYork","Tokyo","Berlin"));
	private ArrayList<Integer> pricebase=new ArrayList<Integer>(Arrays.asList(300,60,5000));
	private ArrayList<ArrayList<LocalDateTime>> date; 
	private static final String STARTBOOKINGDATE="2024-05-20";
	private static final int PERIOD=7;
	private ArrayList<Date> week=new ArrayList();
	private Logger logger=Logger.getLogger(BookingServiceImpl.class.getName());
	private static HashMap<String, ShoppingCart> shoppingCarts = new HashMap<>();
	private static HashMap<String, ArrayList<Ticket>> ticketsMap = new HashMap<>();
	
	public BookingServiceImpl() {
		init();
	}
	
	@Override
	public boolean addtoCart(Flight flight,int rownum,String seatnum,User user) {
		Reservation reservation = new Reservation(flight,rownum,seatnum);
		CartManager mycartManager=new CartManagerImpl(getCart(user));
		//To judge if client book only a seat for a plane
		if (alreadybook(flight, user)) {
			return false;
		}else {
			return mycartManager.addtoCart(reservation);
		}
		
	}
	
	public ShoppingCart getCart(User user) {
		ShoppingCart cart;
		if (!shoppingCarts.containsKey(user.getUsername())) {
			cart = new ShoppingCart(user);
			shoppingCarts.put(user.getUsername(), cart);
		}
		cart = shoppingCarts.get(user.getUsername());
		return cart;
	}
	
	@Override
	public void login(String username, int age) {
		User user=new User(username, age);
		init();
	}
	
	private void init() {
		//initialize object
		flights=new ArrayList();
		
		// set destination and departure time
		date=new ArrayList();
		
		week=utilss.RandomGenerator.getCohDays(STARTBOOKINGDATE, PERIOD);
		ArrayList<LocalDateTime> oneDay;
		for (int i = 0; i < PERIOD; i++) {
			oneDay=new ArrayList<LocalDateTime>();
			for (int j = 0; j < 9; j++) {
				oneDay.add(RandomGenerator.getTimeOnDay(week.get(i)));
			}
			date.add(oneDay);
		}
		
		//initialize flight
		for (int i = 0, m=0; i < PERIOD; i++) {
			m=0;
			for (int j = 0; j < destinations.size(); j++) {
				for (int n = 0; n < flightypes.length; n++) {
					String type=flightypes[n];
					m++;
					String flightnumber=RandomGenerator.getRandomUpperletters(2)+RandomGenerator.getNumString(3);
					
					//TODO Some wrong in time allocation
					Flight flight=Flight.init(type, destinations.get(j),flightnumber, date.get(i).get(j));
					flights.add(flight);
				}
			}
		}
		
	}
	

	public boolean alreadybook(Flight flight, User client) {
		ArrayList<Ticket> tickets=geTickets(client);
		if (tickets.size()==0) {
			return false;
		}
		for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			if (ticket.getClientname()==client.getUsername()
				&& ticket.getReservation().getFlight().equals(flight)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean reserve(User user) {
		CartManager cartmanager=new CartManagerImpl(getCart(user));
		cartmanager.setDestinations(destinations);
		cartmanager.setPricebase(pricebase);
		
		LinkedList<Reservation> failReservations=new LinkedList();
		failReservations= cartmanager.buynow();
		if (failReservations.size()>0) {
			for (Iterator iterator = failReservations.iterator(); iterator.hasNext();) {
				Reservation reservation = (Reservation) iterator.next();
				logger.log(Level.WARNING, "Reservation Failed: "+reservation.toString());
			}
			return false;
		}
		ArrayList<Ticket>cartTickets=cartmanager.getTickets();
		ticketsMap.put(user.getUsername(), cartTickets);
		cartmanager.deleteAll();
//		for (Iterator iterator = cartTickets.iterator(); iterator.hasNext();) {
//			Ticket ticket = (Ticket) iterator.next();
//			tickets.add(ticket);
//		}
		return true;
	}

	@Override
	public ArrayList<Ticket> geTickets(User user) {
		if (!ticketsMap.containsKey(user.getUsername())) {
			ArrayList<Ticket> tmp=new ArrayList<Ticket>();
			ticketsMap.put(user.getUsername(), tmp);
		}
		return ticketsMap.get(user.getUsername());
	}
	

	@Override
	public ArrayList<Flight> getAllFlights() {
		return flights;
	}


	
	public ArrayList<String> getDestinations() {
		return destinations;
	}
	
	public ArrayList<Date> getWeek() {
		return week;
	}

	@Override
	public ArrayList<Flight> getFlightsOnDayandDest(String destination, Date date) {
		ArrayList<Flight> flightsOneDay=new ArrayList<Flight>();
		for (Iterator iterator = flights.iterator(); iterator.hasNext();) {
			Flight flight = (Flight) iterator.next();
			if (flight.getDestination().equals(destination) 
					&& isSameDay(Transformer.dateToLocalDateTime(date), flight.getDeparturetime())) {
				flightsOneDay.add(flight);
			}
		}
		
		return flightsOneDay;
	}
	
	private boolean isSameDay(LocalDateTime day1,LocalDateTime day2) {
		Date date1=Transformer.localDateTimeTODate(day1);
		Date date2=Transformer.localDateTimeTODate(day2);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		boolean result = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
		    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
		return result;
	}

}
