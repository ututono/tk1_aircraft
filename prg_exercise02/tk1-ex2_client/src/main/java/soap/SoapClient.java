package soap;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.ws.Service;

import clients.ClientInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.namespace.QName;

import model.Flight;
import model.Seat;
import model.ShoppingCart;
import model.Ticket;
import model.User;
import soap.ReservationBookingServiceInterface;
import utilss.Transformer;
import view.CustmorConfig;
import view.FlightSect;
import view.Login;
import view.SeatBooking;

public class SoapClient implements ClientInterface {

	private User client;
	public Service service;
	private Logger logger=Logger.getLogger(SoapClient.class.getName());
	private ReservationBookingServiceInterface serverInterface;
	private HashMap<String, Integer> seatOrderMap;
	
	public SoapClient(User user) {
		this.client = user;
		URL url = null;
		try {
			url = new URL("http://localhost:9999/aircraft?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://soap/", "ReservationBookingServiceImplService");

		service = Service.create(url, qname);

		serverInterface = service.getPort(ReservationBookingServiceInterface.class);
		
		
		startup();
		
		//LinkedList<Product> products = serverInterface.allProducts().getProducts();

		//View view = new View("SOAP", products, this);

		//view.createAndShowGUI();
	}
	
	@Override
	public ArrayList<Flight> getFlightfromServer() {
//		ReservationBookingServiceInterface serverInterface =
//				service.getPort(ReservationBookingServiceInterface.class);
		ArrayList<Flight> flights=serverInterface.getallFlights();
		Flight flight=flights.get(2);
//		LinkedList<LinkedList<Seat>> list= 
//				serverInterface.getSeatsFromFlight(flight.getFlightnumber());
//		flight.setSeats(list);
		fillSeats(flights);
		System.out.println("[fillSeats3]getFlightfromServer"+flight.toString());
		
		return flights;
	}
	
	/**
	 * Because flight will lose the information about seats when the data
	 * transmitted via soap. The information will be completed, however,
	 * when it transmitted itself only. 
	 * @param flight
	 */
	private void fillSeats(ArrayList<Flight> flights) {
		for (Iterator iterator = flights.iterator(); iterator.hasNext();) {
			Flight flight = (Flight) iterator.next();
			ArrayList<ArrayList<Seat>> seats=
					serverInterface.getSeatsFromFlight(flight.getFlightnumber());
			flight.setSeats(seats);
		}
		
	}
	

	@Override
	public void addtoCart(User user,String flightnum, int rownum, String seatnum) {
//		ReservationBookingServiceInterface serverInterface =
//				service.getPort(ReservationBookingServiceInterface.class);
		if(serverInterface.addCart(user, flightnum, rownum, seatnum)) {
			logger.log(Level.INFO, user.getUsername()+" has added flight "+flightnum
					+" on the seat row "+rownum+" "+seatnum+" to his/her own cart.");
		}

	}

	@Override
	public void buynow() {
		if(serverInterface.purchase(client)) {
			logger.log(Level.INFO, "Client "
						+client.getUsername()
						+" has booked the seat succesfully!\n"
						+"He/She has "+getTicket().size()+" Tickets");
		}else {
			logger.log(Level.WARNING,"Buy failed !"+
						"He/She has "+getTicket().size()+" tickets");
		}
	}


	@Override
	public ShoppingCart getMyCart() {
//		ReservationBookingServiceInterface serverInterface =
//				service.getPort(ReservationBookingServiceInterface.class);
		return serverInterface.getmyCart(client);
	}

	@Override
	public void startup() {
		
		seatOrderMap=new HashMap<String, Integer>();
		seatOrderMap.put("A", 0);
		seatOrderMap.put("B", 1);
		seatOrderMap.put("C", 2);
		seatOrderMap.put("D", 3);
		seatOrderMap.put("E", 4);
		seatOrderMap.put("F", 5);

		
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Login loginview=new Login();
				loginview.getFrame().setVisible(true);
				loginview.getLbclientname().setText(getClient().getUsername());
				
				JButton loginBtn= loginview.getBtnlogin();
				loginBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						initCustomerConfigView();
						loginview.getFrame().dispose();
						
					}
					
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
	
	@Override
	public ArrayList<String> getDestinations() {
		ReservationBookingServiceInterface serverInterface =
				service.getPort(ReservationBookingServiceInterface.class);
		return serverInterface.getDestinations();
	}
	
	@Override
	public ArrayList<Date> getDates() {
		return serverInterface.getDates();
	}
	
	@Override
	public void initCustomerConfigView() {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				CustmorConfig window=new CustmorConfig();
				window.getFrame().setVisible(true);
				
				JComboBox destinationBox=window.getDestinationBox();
				destinationBox.removeAllItems();
				ArrayList<String> destinations=getDestinations();
				for (Iterator iterator = destinations.iterator(); iterator.hasNext();) {
					String destination = (String) iterator.next();
					destinationBox.addItem(destination);
				}
				
				JComboBox<String> dateBox=window.getDateBox();
				dateBox.setEditable(true);
				ArrayList<Date> dates=getDates();
				for (Iterator iterator = dates.iterator(); iterator.hasNext();) {
					Date date = (Date) iterator.next();
					String formatDate=Transformer.DateToString(date, Transformer.ISO_LOCAL_DATE);
					dateBox.addItem(formatDate);
				}
				
				Flight flight=getFlightfromServer().get(0);
				System.out.println("initCustomerConfigView\n"+flight.getSeats().get(0)
						+"\nThe size of it "+flight.getSeats().get(0).getClass().getName());
				
				
				// Initialize button
				JButton next=window.getNextBtn();
				next.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String des=destinationBox.getSelectedItem().toString();
						Date date=dates.get(dateBox.getSelectedIndex());
						initFlightSectView(des,date);
						window.getFrame().dispose();
						
					}
					
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
	
	public User getClient() {
		return client;
	}

	@Override
	public void initFlightSectView(String destination, Date date) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FlightSect window=new FlightSect();
				window.getFrame().setVisible(true);
				
				DefaultListModel<Flight> listModel=new DefaultListModel<Flight>();
				ArrayList<Flight> fList=
						serverInterface.getFlightsOnDayandDest(destination, date);
				fillSeats(fList);
				for (Iterator iterator = fList.iterator(); iterator.hasNext();) {
					Flight flight = (Flight) iterator.next();
					listModel.addElement(flight);
				}
				
				JList list=window.getList();
				list.setModel(listModel);
				
				JButton backBtn = window.getBackBtn();
				backBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						initCustomerConfigView();
						window.getFrame().dispose();
					}
					
				});
				
				JButton nextBtn=window.getNextBtn();
				nextBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Flight flight=fList.get(list.getSelectedIndex());
						initSeatBookingView(flight);
						window.getFrame().dispose();
						
					}
					
				});
				
				
				
			}
		});
	}
	
	@Override
	public void initSeatBookingView(Flight flight) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				SeatBooking window=new SeatBooking();
				window.getFrame().setVisible(true);
				Object[] columns={"A","B","C","D","E","F"};
				DefaultTableModel tableModel=new DefaultTableModel();
				tableModel.setColumnIdentifiers(columns);

				for (int i = 0; i < flight.getSeats().size(); i++) {
					
					String[] seatrow= {"-","-","-","-","-","-"};
					for (int j = 0; j < flight.getSeats().get(i).size(); j++) {
						Seat seat=flight.getSeats().get(i).get(j);
						// If seat is non-existence
						if (!seat.isBlank()) {
							// If seat is free
							if (seat.isStatus()) {
								int index=seatOrderMap.get(seat.getSeatnumber());
								seatrow[index]="1";
							}else {
								// IF the Seat is booked
								int index=seatOrderMap.get(seat.getSeatnumber());
								seatrow[index]="0";
							}
						}
					}
					tableModel.addRow(seatrow);
				}
				String[] seatrow= {"-","-","-","-","-","-"};
				tableModel.addRow(seatrow);
				
				JTable seatsTable=window.getTable();
				seatsTable.setModel(tableModel);
				
				JButton backBtn=window.getBackBtn();
				backBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						initCustomerConfigView();
						window.getFrame().dispose();
						
					}
					
				});
				
			}
			
		});
	}
	
	@Override
	public ArrayList<Ticket> getTicket() {
		return serverInterface.geTickets(client);
	}


}
