package model;
import java.io.Serializable;
import java.lang.Object;
import java.nio.IntBuffer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import utils.RandomUtil;

public class Flight implements Serializable{
	
	//Operating Airline
	private String iata;
	private String name;
	
	private String aircraftType;
	private String flightNum;
	
	private String departureAirport;
	private String arrivalAirport;
	private Date date; // No determine using type date or string TODO
	

	private LocalDateTime scheduled_arrival;
	private LocalDateTime scheduled_dep;
	

	private LocalDateTime es_arrival;
	private LocalDateTime es_dep;

	private String arrival_terminal;
	private String dep_terminal;
	private String arrival_gates;
	private String dep_gates;
	
	//check in information
	private String checkinLocation;
	private String checkinCounter;
	private LocalDateTime checkinStart;
	private LocalDateTime checkinEnd;
	private Character flightStatus;

	
	public void createFlightRandom() {
		StringBuffer stringBuffer;
		String beginDate="2021-10-11";
		String endDate="2021-11-11";
		setIata(RandomUtil.getRandomUpperletters(2));
		setName(RandomUtil.getFormatNameString(RandomUtil.getRandomNumber(3, 9)));
		
		stringBuffer=new StringBuffer();
		stringBuffer.append(RandomUtil.getRandomUpperletters(1));
		stringBuffer.append(RandomUtil.getNumString(3));
		setAircraftType(stringBuffer.toString());
		
		setFlightNum(RandomUtil.getNumString(3));
		
		setDepartureAirport(RandomUtil.getRandomUpperletters(3));
		setArrivalAirport(RandomUtil.getRandomUpperletters(3));
		
		setScheduled_arrival(RandomUtil.getrandomDate(beginDate, endDate));
		setScheduled_dep(RandomUtil.getrandomDate(beginDate, endDate));
		setEs_arrival(RandomUtil.getrandomDate(beginDate, endDate));
		setEs_dep(RandomUtil.getrandomDate(beginDate, endDate));
		
//		List<String> gates=new ArrayList<>();
//		for (int i = 0; i <RandomUtil.getRandomNumber(1, 15); i++) {
//			gates.add(RandomUtil.getNumString(3));
//		}
//		setGates(gates);
		
		setArrival_terminal(RandomUtil.getNumString(1));
		setDep_terminal(RandomUtil.getNumString(1));
		setDep_gates(RandomUtil.getNumString(3));
		setArrival_gates(RandomUtil.getNumString(3));
		
		setCheckinLocation(RandomUtil.getNumString(1));
		
		setCheckinCounter(RandomUtil.getNumString(3)+" - "+RandomUtil.getNumString(3));
//		setEstimatedDateTime(RandomUtil.getrandomDate(beginDate, endDate));
		setCheckinStart(RandomUtil.getrandomDate(beginDate, endDate));
		setCheckinEnd(RandomUtil.getrandomDate(beginDate, endDate));
		
		setFlightStatus(RandomUtil.gestRandomStatus());
		
	}

	public String getIata() {
		return iata;
	}
	
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAircraftType() {
		return aircraftType;
	}
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
	public String getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(String string) {
		this.flightNum = string;
	}
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCheckinLocation() {
		return checkinLocation;
	}
	public void setCheckinLocation(String checkinLocation) {
		this.checkinLocation = checkinLocation;
	}
	public String getCheckinCounter() {
		return checkinCounter;
	}
	public void setCheckinCounter(String checkinCounter) {
		this.checkinCounter = checkinCounter;
	}
	public Character getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(Character flightStatus) {
		this.flightStatus = flightStatus;
	}


	public String getArrival_terminal() {
		return arrival_terminal;
	}

	public void setArrival_terminal(String arrival_terminal) {
		this.arrival_terminal = arrival_terminal;
	}

	public String getDep_terminal() {
		return dep_terminal;
	}

	public void setDep_terminal(String dep_terminal) {
		this.dep_terminal = dep_terminal;
	}

	public String getArrival_gates() {
		return arrival_gates;
	}

	public void setArrival_gates(String arrival_gates) {
		this.arrival_gates = arrival_gates;
	}

	public String getDep_gates() {
		return dep_gates;
	}

	public void setDep_gates(String dep_gates) {
		this.dep_gates = dep_gates;
	}

	public LocalDateTime getScheduled_arrival() {
		return scheduled_arrival;
	}

	public void setScheduled_arrival(LocalDateTime scheduled_arrival) {
		this.scheduled_arrival = scheduled_arrival;
	}

	public LocalDateTime getScheduled_dep() {
		return scheduled_dep;
	}

	public void setScheduled_dep(LocalDateTime scheduled_dep) {
		this.scheduled_dep = scheduled_dep;
	}

	public LocalDateTime getEs_arrival() {
		return es_arrival;
	}

	public void setEs_arrival(LocalDateTime es_arrival) {
		this.es_arrival = es_arrival;
	}

	public LocalDateTime getEs_dep() {
		return es_dep;
	}

	public void setEs_dep(LocalDateTime es_dep) {
		this.es_dep = es_dep;
	}
	public LocalDateTime getCheckinStart() {
		return checkinStart;
	}

	public void setCheckinStart(LocalDateTime checkinStart) {
		this.checkinStart = checkinStart;
	}

	public LocalDateTime getCheckinEnd() {
		return checkinEnd;
	}

	public void setCheckinEnd(LocalDateTime checkinEnd) {
		this.checkinEnd = checkinEnd;
	}



	@Override
	public int hashCode() {
		return Objects.hash(aircraftType, arrivalAirport, arrival_gates, arrival_terminal, checkinCounter, checkinEnd,
				checkinLocation, checkinStart, date, dep_gates, dep_terminal, departureAirport, es_arrival, es_dep,
				flightNum, flightStatus, iata, name, scheduled_arrival, scheduled_dep);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(aircraftType, other.aircraftType) && Objects.equals(arrivalAirport, other.arrivalAirport)
				&& Objects.equals(arrival_gates, other.arrival_gates)
				&& Objects.equals(arrival_terminal, other.arrival_terminal)
				&& Objects.equals(checkinCounter, other.checkinCounter) && Objects.equals(checkinEnd, other.checkinEnd)
				&& Objects.equals(checkinLocation, other.checkinLocation)
				&& Objects.equals(checkinStart, other.checkinStart) && Objects.equals(date, other.date)
				&& Objects.equals(dep_gates, other.dep_gates) && Objects.equals(dep_terminal, other.dep_terminal)
				&& Objects.equals(departureAirport, other.departureAirport)
				&& Objects.equals(es_arrival, other.es_arrival) && Objects.equals(es_dep, other.es_dep)
				&& Objects.equals(flightNum, other.flightNum) && Objects.equals(flightStatus, other.flightStatus)
				&& Objects.equals(iata, other.iata) && Objects.equals(name, other.name)
				&& Objects.equals(scheduled_arrival, other.scheduled_arrival)
				&& Objects.equals(scheduled_dep, other.scheduled_dep);
	}

	@Override
	public Flight clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Flight flightClone=new Flight();
		
		flightClone.setAircraftType(aircraftType);
		flightClone.setArrivalAirport(arrivalAirport);
		flightClone.setCheckinCounter(checkinCounter);
		flightClone.setCheckinLocation(checkinLocation);
		flightClone.setDepartureAirport(departureAirport);
		return flightClone;
	}

	@Override
	public String toString() {
		return "Flight [iata=" + iata + ", name=" + name + ", aircraftType=" + aircraftType + ", flightNum=" + flightNum
				+ ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport + ", date=" + date
				+ ", scheduled_arrival=" + scheduled_arrival + ", scheduled_dep=" + scheduled_dep + ", es_arrival="
				+ es_arrival + ", es_dep=" + es_dep + ", arrival_terminal=" + arrival_terminal + ", dep_terminal="
				+ dep_terminal + ", arrival_gates=" + arrival_gates + ", dep_gates=" + dep_gates + ", checkinLocation="
				+ checkinLocation + ", checkinCounter=" + checkinCounter + ", checkinStart=" + checkinStart
				+ ", checkinEnd=" + checkinEnd + ", flightStatus=" + flightStatus + "]";
	}



	/*
	 * print out type of all attributes
	 */
	/*
	public String typecheck() {
		String iataT=getIata().getClass().toString();
		String nameT=getName().getClass().toString();
		String aircT=getAircraftType().getClass().toString();
		String fnT=getFlightNum().getClass().toString();
		String dA=getDepartureAirport().getClass().toString();
		String aA=getArrivalAirport().getClass().toString();
		String date=getDate().getClass().toString();
		String terminal=getTerminal().getClass().toString();
		String gates=getGates().getClass().toString();
		String eDTT=null;
		String cLT=getCheckinLocation().getClass().toString();
		String cCT=getCheckinCounter().getClass().toString();
		String fsT=getFlightStatus().getClass().toString();
		
		String msg="Type of iata is:"+iataT+
				" Type of name is:"+nameT+
				" Type of aircraftype is:"+aircT+
				" Type of flightnum is:"+fnT+
				" Type of dA is:"+dA+
				" Type of aA is:"+aA+
				" Type of date is:"+date+
				" Type of terminal is:"+terminal+
				" Type of gates is:"+gates+
				" Type of eDTT is:"+eDTT+
				" Type of cLT is:"+cLT+
				" Type of cCT is:"+cCT+
				" Type of fsT is:"+fsT;
		return msg;
	}
	*/
	

}
