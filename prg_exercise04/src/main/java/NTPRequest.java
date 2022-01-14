import java.io.Serializable;


public class NTPRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public long t1;
	public long t2;
	public long t3;
	public long t4;
	public double o;
	public double d;

	public NTPRequest() {
	
	}

	/**
	 * 			---------------T3----------------T2------------ Server
	 * 						  //                   \
	 * 						 /						\
	 * 						/						 \\
	 * 			----------T4---------------------------T1------- Client
	 *
	 */

	public long getT1() {
		return t1;
	}
	public void setT1(long t1) {
		this.t1 = t1;
	}
	public long getT2() {
		return t2;
	}
	public void setT2(long t2) {
		this.t2 = t2;
	}
	public long getT3() {
		return t3;
	}
	public void setT3(long t3) {
		this.t3 = t3;
	}
	public long getT4() {
		return t4;
	}
	public void setT4(long t4) {
		this.t4 = t4;
	}
	
	public void calculateOandD() {
		d=(t1-t2)+(t3-t4);
		o=d/2;

	}

	@Override
	public String toString() {
		return "NTPRequest{" +
				"t1=" + t1 +
				", t2=" + t2 +
				", t3=" + t3 +
				", t4=" + t4 +
				", o=" + o +
				", d=" + d +
				'}';
	}
}
