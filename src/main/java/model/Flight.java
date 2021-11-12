package model;
import java.io.Serializable;
import java.lang.Object;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	
	private Date scheduledDateTime;
	private String terminal;
	private List<String> gates;
	private Date estimatedDateTime;
	
	
	//check in information
	private int checkinLocation;
	private List<String> checkinCounter;
	private Character flightStatus;
	public String getIata() {
		return iata;
	}
	
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
		
		setDate(RandomUtil.getrandomDate(beginDate, endDate));
		setScheduledDateTime(RandomUtil.getrandomDate(beginDate, endDate));
		setTerminal(RandomUtil.getNumString(2));
		
		List<String> gates=new ArrayList<>();
		for (int i = 0; i <RandomUtil.getRandomNumber(1, 15); i++) {
			gates.add(RandomUtil.getNumString(3));
		}
		setGates(gates);
		setCheckinLocation(RandomUtil.getRandomNumber(1, 20));
		
		List<String> counters=new ArrayList<String>();
		for (int i = 0; i <RandomUtil.getRandomNumber(1, 15); i++) {
			counters.add(RandomUtil.getNumString(3));
		}
		setCheckinCounter(counters);
		setEstimatedDateTime(RandomUtil.getrandomDate(beginDate, endDate));
		setFlightStatus(RandomUtil.gestRandomStatus());
		
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
	public Date getScheduledDateTime() {
		return scheduledDateTime;
	}
	public void setScheduledDateTime(Date scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public List<String> getGates() {
		return gates;
	}
	public void setGates(List<String> gates) {
		this.gates = gates;
	}
	public Date getEstimatedDateTime() {
		return estimatedDateTime;
	}
	public void setEstimatedDateTime(Date estimatedDateTime) {
		this.estimatedDateTime = estimatedDateTime;
	}
	public int getCheckinLocation() {
		return checkinLocation;
	}
	public void setCheckinLocation(int checkinLocation) {
		this.checkinLocation = checkinLocation;
	}
	public List<String> getCheckinCounter() {
		return checkinCounter;
	}
	public void setCheckinCounter(List<String> checkinCounter) {
		this.checkinCounter = checkinCounter;
	}
	public Character getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(Character flightStatus) {
		this.flightStatus = flightStatus;
	}

	@Override
	public String toString() {
		return "Flight [iata=" + iata + ", name=" + name + ", aircraftType=" + aircraftType + ", flightNum=" + flightNum
				+ ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport + ", date=" + date
				+ ", scheduledDateTime=" + scheduledDateTime + ", terminal=" + terminal + ", gates=" + gates
				+ ", estimatedDateTime=" + estimatedDateTime + ", checkinLocation=" + checkinLocation
				+ ", checkinCounter=" + checkinCounter + ", flightStatus=" + flightStatus + "]";
	}
	
	

	
	

}
