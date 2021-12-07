/**
 * 
 */
package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ututono
 *
 */
@XmlRootElement
public class Seat {
	// Example A - F
	private String seatnumber;
	private int type;
	private boolean status; // The seat can be reserved or not; true: free; false: reserved
	private boolean isermengency; // To judge if the seat is a emergency exit seat
	
	public Seat() {
		
	}
	
	public void init(String seatnumber,int type,boolean isemergency) {
		this.seatnumber=seatnumber;
		this.type=type;
		status=true;
		this.isermengency=isemergency;
	}

	public String getSeatnumber() {
		return seatnumber;
	}

	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 
	 * @return true: free; false: reserved
	 */
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isIsermengency() {
		return isermengency;
	}

	public void setIsermengency(boolean isermengency) {
		this.isermengency = isermengency;
	}
	
	public boolean isBlank() {
		if (seatnumber.isBlank()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Seat [seatnumber=" + seatnumber + ", type=" + type + ", status=" + status + ", isermengency="
				+ isermengency + "]";
	}
	
	

}
