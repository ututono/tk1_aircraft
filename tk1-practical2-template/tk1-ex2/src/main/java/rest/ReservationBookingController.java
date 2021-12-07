package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/calc")
public class ReservationBookingController {

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
    
    
	
}
