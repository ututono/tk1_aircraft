package rest;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controllerImpl.BookingServiceImpl;
import controllerInterface.BookingServcie;
import model.Flight;
import model.User;
import utilss.Transformer;

@Path("/calc")
public class ReservationBookingController {
	
	private BookingServcie bookingServcie=new BookingServiceImpl();
	
	@GET
	@Path("/add/{a}/{b}")
	@Produces(MediaType.TEXT_PLAIN)
	public String addPlainText(@PathParam("a") double a, @PathParam("b") double b) {
        return (a + b) + "";
    }
	
    @GET
    @Path("/add/{a}/{b}")
    @Produces(MediaType.TEXT_XML)
    public String add(@PathParam("a") double a, @PathParam("b") double b) {
        return "<?xml version=\"1.0\"?>" + "<result>" +  (a + b) + "</result>";
    }
    

	@GET
	@Path("/getFlights")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Flight> getFlights(String destination,String date) {
		Date day=Transformer.stringToDate(date);
		ArrayList<Flight> flights=
				bookingServcie.getFlightsOnDayandDest(destination, day);
		return flights;
	}
	

	@GET
	@Path("/getDestination")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<String> getDestination() {
		ArrayList<String> destinations=bookingServcie.getDestinations();
		return destinations;
	}
	

	@GET
	@Path("/getDate")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Date> getDate() {
		ArrayList<Date> dates=bookingServcie.getWeek();
		return dates;
	}


	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	public Response addToCart(@QueryParam("clientname") String clientId, 
							@QueryParam("age")int age , 
							@QueryParam("flightnum")String flightnum, 
							@QueryParam("rownum")int rownum, 
							@QueryParam("seatnum")String seatnum,
							Flight flight) {
		boolean result = bookingServcie.addtoCart(flight, rownum, seatnum, new User(clientId,age));
		return Response.status(201).entity(result + "").build();
	}


	@POST
	@Path("/buy")
	public Response buy(@QueryParam("clientname") String clientId, 
						@QueryParam("age")int age) {
		boolean result = bookingServcie.reserve(new User(clientId,age));
		return Response.status(201).entity(result + "").build();
	}


	
}
