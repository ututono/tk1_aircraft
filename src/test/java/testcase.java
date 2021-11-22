import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.print.attribute.standard.PrinterInfo;

import org.junit.jupiter.api.Test;

import model.Flight;
import utils.DataTransform;

class testcase {

//	@Test
//	void test() {
//		Flight flight1=new Flight();
//		flight1.createFlightRandom();
//		System.out.println(flight1.toString());
//	}
	public static void printer(List<Flight> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	
	public static void testModify() {
		int initFlightNum=10;
		List<Flight> flights=new ArrayList<Flight>();
		// initialize with some flights
		for (int i = 0; i < initFlightNum ; i++) {
			Flight flight=new Flight();
			flight.createFlightRandom();
			flights.add(flight);
		}
		Flight flight=new Flight();
		flight.createFlightRandom();
		flights.add(flight);
		
		printer(flights);
		System.out.println("*****************************************************8");
		flights.remove(flight);
		printer(flights);
		System.out.println("*****************************************************8");
		Flight flight2=new Flight();
		flight2.createFlightRandom();
		flights.set(0, flight2);
		printer(flights);
		
		flights.remove(flight);

	}
	
	public static void testDateTransform() {
		String ldtstr="2021-11-05Td22:18:48.424";
		LocalDateTime ldt=DataTransform.string2LocalDateTime(null);
		System.out.println("sdafasdf");
	}
	public static void main(String[] args) {
		testDateTransform();
	}
	


}
