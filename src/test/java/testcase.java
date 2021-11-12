import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Flight;

class testcase {

//	@Test
//	void test() {
//		Flight flight1=new Flight();
//		flight1.createFlightRandom();
//		System.out.println(flight1.toString());
//	}
	
	public static void main(String[] args) {
		Flight flight1=new Flight();
		flight1.createFlightRandom();
		System.out.println(flight1.toString());
	}

}
