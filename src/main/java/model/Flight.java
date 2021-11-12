package model;
import java.lang.Object;
import java.util.Date;
import java.util.List;

public class Flight {
	
	//Operating Airline
	private String iata;
	private String name;
	
	private String aircraftType;
	private int flightNum;
	
	private String departureAirport;
	private String arrivalAirport;
	private String date; // No determine using type date or string TODO
	
	private Date scheduledDateTime;
	private String terminal;
	private List<String> gates;
	private Date estimatedDateTime;
	
	
	//check in information
	private int checkinLocation;
	private int[] checkinCounter;
	private String flightStatus;
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
	public int getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	public int[] getCheckinCounter() {
		return checkinCounter;
	}
	public void setCheckinCounter(int[] checkinCounter) {
		this.checkinCounter = checkinCounter;
	}
	public String getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}
	
	

	
	

}
