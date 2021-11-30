package soap;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "soap.ReservationBookingServiceInterface")

public class ReservationBookingServiceImpl implements ReservationBookingServiceInterface {

	@Override
	public String getHelloWorldAsString(String name) {

		return "hello world"+name;
	}

}