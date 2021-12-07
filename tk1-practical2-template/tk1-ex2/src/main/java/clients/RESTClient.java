package clients;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.Client;


import model.Flight;
import model.ShoppingCart;
import model.Ticket;
import model.User;
import utilss.Transformer;
import view.CustmorConfig;
import view.Login;

public class RESTClient implements ClientInterface {

	private User user;
	static final String REST_URI = "http://localhost:8080/server";
	static final String GET_PRODUCTS_PATH = "rest/getFlights";
	static final String GET_DESTINATION_PATH = "rest/getDestination";
	static final String GET_DATES_PATH = "rest/getDate";
	static final String BUY_PATH = "rest/buy";
	static final String ADD_TO_CART_PATH = "rest/add";
//	private Client client;
	Client client;
	WebResource service;
	private HashMap<String, Integer> seatOrderMap;
	private Logger logger=Logger.getLogger(SoapClient.class.getName());
	
	private ArrayList<Flight> flights;
	
	
	public RESTClient(User user) {
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 10000);
		clientConfig.property(ClientProperties.READ_TIMEOUT, 10000);
		client=client.create(new DefaultClientConfig());
		service=client.resource(REST_URI);
		
		flights=getFlightfromServer();
		this.user=user;
		
		startup();
		
	}
	
	@Override
	public ArrayList<Flight> getFlightfromServer() {
		WebResource orderService = service.path(GET_PRODUCTS_PATH);
		String marshalled = getOutputAsAppXml(orderService);
		System.out.println("orderService Response: "
				+ orderService.getURI().toString() + " : " + marshalled);
		return unmarschallFlights(marshalled);

	}
	
	public ArrayList<Flight> unmarschallFlights(String source) {
		ArrayList<Flight> flights = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(flights.getClass());
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
			ByteArrayInputStream stream = new ByteArrayInputStream(
					source.getBytes());
			flights = (ArrayList<Flight>) jaxbMarshaller.unmarshal(stream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return flights;
	}
	
	public ArrayList<String> unmarschallStrList(String source) {
		ArrayList<String> objects = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(flights.getClass());
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
			ByteArrayInputStream stream = new ByteArrayInputStream(
					source.getBytes());
			objects = (ArrayList<String>) jaxbMarshaller.unmarshal(stream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return objects;
	}
	
	
	public ArrayList<Date> unmarschallDateList(String source) {
		ArrayList<Date> objects = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(flights.getClass());
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
			ByteArrayInputStream stream = new ByteArrayInputStream(
					source.getBytes());
			objects = (ArrayList<Date>) jaxbMarshaller.unmarshal(stream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return objects;
	}
	
	
	private String marshal(Flight product) {
		StringBuilder sb = new StringBuilder();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Flight.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(
					javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(product, stream);
			try {
				stream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sb.append(stream);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println(sb.toString());
		return sb.toString();
	}

	@Override
	public void addtoCart(User client, String flightnum, int rownum, String seatnum) {
		WebResource checkService = this.client.resource(REST_URI + "/"
				+ ADD_TO_CART_PATH + "?clientname=" + client.getUsername()
				+ "?age=" + client.getAge()+ "?flightnum=" + flightnum
				+ "?rownum=" + rownum+ "?seatnum=" + seatnum);
		System.out.println(checkService.toString());
		Flight flight = new Flight();
		for (Iterator iterator = flights.iterator(); iterator.hasNext();) {
			flight = (Flight) iterator.next();
			if (flight.getFlightnumber().equals(flight)) {
				break;
			}
		}
		
		ClientResponse response = checkService.type(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, marshal(flight)); 
		String result = response.getEntity(String.class);
		System.out.println(result);
		
	}
	
	

	@Override
	public ShoppingCart getMyCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buynow() {
		WebResource checkService = client.resource(REST_URI + "/"
				+ BUY_PATH + "?clientId=" + user.getUsername()+"?age=" + user.getAge());
		ClientResponse response = checkService.type(MediaType.APPLICATION_XML)
				.post(ClientResponse.class);
		String result = response.getEntity(String.class);
		System.out.println(result);
		
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
				loginview.getLbclientname().setText(getUser().getUsername());
				
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
	
	private static String getOutputAsAppXml(WebResource resource) {
		return resource.accept(MediaType.APPLICATION_XML).get(String.class);
	}
	
	@Override
	public ArrayList<Ticket> getTicket() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<String> getDestinations() {
		WebResource orderService = service.path(GET_DESTINATION_PATH);
		String marshalled = getOutputAsAppXml(orderService);
		System.out.println("orderService Response: "
				+ orderService.getURI().toString() + " : " + marshalled);
		return unmarschallStrList(marshalled);
	}

	@Override
	public ArrayList<Date> getDates() {
		WebResource orderService = service.path(GET_DATES_PATH);
		String marshalled = getOutputAsAppXml(orderService);
		System.out.println("orderService Response: "
				+ orderService.getURI().toString() + " : " + marshalled);
		return unmarschallDateList(marshalled);
	}

	@Override
	public void initFlightSectView(String destination, Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initSeatBookingView(Flight flight) {
		// TODO Auto-generated method stub
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}


	
}
