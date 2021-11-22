package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ClientApp {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JLabel lblNewLabel;
	private JTextField iata;
	private JLabel lblNewLabel_1;
	private JTextField operatingAirline;
	private JLabel lblNewLabel_2;
	private JTextField airModelName;
	private JLabel lblNewLabel_3;
	private JTextField trackNum;
	private JLabel lblNewLabel_4;
	private JTextField depAirport;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JLabel lblNewLabel_5;
	private JTextField originDate;
	private JLabel lblNewLabel_6;
	private JTextField scheduled_Dep;
	private JPanel panel_1;
	private JLabel lblNewLabel_7;
	private JTextField dep_Terminal;
	private JLabel lblNewLabel_8;
	private JTextField dep_Gates;
	private JLabel lblNewLabel_9;
	private JTextField es_Dep;
	private JLabel lblNewLabel_10;
	private JTextField checkin_loc;
	private JLabel lblNewLabel_11;
	private JTextField checkin_Counter;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_12;
	private JTextField c_start;
	private JLabel lblNewLabel_13;
	private JButton btnApply;
	private JButton btnCancel;
	private JLabel lblNewLabel_14;
	private JTextField arrAirport;
	private JLabel lblNewLabel_15;
	private JTextField scheduled_arrival;
	private JLabel lblNewLabel_16;
	private JTextField arrival_terminal;
	private JLabel lblNewLabel_17;
	private JTextField arrvial_Gates;
	private JLabel lblNewLabel_18;
	private JTextField es_Arrival;
	private JLabel lblNewLabel_19;
	private JTextField c_End;
	private JComboBox status_box;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApp window = new ClientApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int HGAP=20;
		int VGAP=5;
		int lBWMAX=80;  // MAX wide of label : roughly 2 words wide
		int IBHMAX=15;   //MAX height of label
		int LAYOUTSTYLE=FlowLayout.LEADING;
		int COULUMSNUM=20;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 734, 728);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING,20,5));
		frame.getContentPane().add(panel);
		
		lblNewLabel = new JLabel("IATA CODE");
		lblNewLabel.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel.add(lblNewLabel);
		
		iata = new JTextField();
		iata.setEditable(false);
		panel.add(iata);
		iata.setColumns(COULUMSNUM);
		
		lblNewLabel_1 = new JLabel("Operating Airline");
		lblNewLabel_1.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel.add(lblNewLabel_1);
		
		operatingAirline = new JTextField();
		panel.add(operatingAirline);
		operatingAirline.setColumns(COULUMSNUM);
		
		panel_10 = new JPanel();
		panel_10.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_10);
		
		lblNewLabel_2 = new JLabel("Aircraft Model Name");
		lblNewLabel_2.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_10.add(lblNewLabel_2);
		
		airModelName = new JTextField();
		panel_10.add(airModelName);
		airModelName.setColumns(COULUMSNUM);
		
		panel_11 = new JPanel();
		panel_11.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_11);
		
		lblNewLabel_3 = new JLabel("Tracking Number");
		lblNewLabel_3.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_11.add(lblNewLabel_3);
		
		trackNum = new JTextField();
		trackNum.setEditable(false);
		panel_11.add(trackNum);
		trackNum.setColumns(COULUMSNUM);
		
		panel_12 = new JPanel();
		panel_12.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_12);
		
		lblNewLabel_4 = new JLabel("Depatrure Airport");
		lblNewLabel_4.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_12.add(lblNewLabel_4);
		
		depAirport = new JTextField();
		panel_12.add(depAirport);
		depAirport.setColumns(COULUMSNUM);
		
		lblNewLabel_14 = new JLabel("Arrival Airport");
		lblNewLabel_14.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_12.add(lblNewLabel_14);
		
		arrAirport = new JTextField();
		panel_12.add(arrAirport);
		arrAirport.setColumns(COULUMSNUM);
		
		panel_13 = new JPanel();
		panel_13.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_13);
		
		lblNewLabel_5 = new JLabel("Origin Date");
		lblNewLabel_5.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_13.add(lblNewLabel_5);
		
		originDate = new JTextField();
		originDate.setText("1900-01-01T00:00:00.001");
		panel_13.add(originDate);
		originDate.setColumns(COULUMSNUM);
		
		panel_14 = new JPanel();
		panel_14.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_14);
		
		lblNewLabel_6 = new JLabel("Scheduled Departure");
		lblNewLabel_6.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_14.add(lblNewLabel_6);
		
		scheduled_Dep = new JTextField();
		scheduled_Dep.setText("1900-01-01T00:00:00.001");
		panel_14.add(scheduled_Dep);
		scheduled_Dep.setColumns(COULUMSNUM);
		
		lblNewLabel_15 = new JLabel("Scheduled_Arrival");
		lblNewLabel_15.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_14.add(lblNewLabel_15);
		
		scheduled_arrival = new JTextField();
		scheduled_arrival.setText("1900-01-01T00:00:00.001");
		panel_14.add(scheduled_arrival);
		scheduled_arrival.setColumns(COULUMSNUM);
		
		panel_15 = new JPanel();
		panel_15.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_15);
		
		lblNewLabel_7 = new JLabel("Departure Terminal");
		lblNewLabel_7.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_15.add(lblNewLabel_7);
		
		dep_Terminal = new JTextField();
		panel_15.add(dep_Terminal);
		dep_Terminal.setColumns(COULUMSNUM);
		
		lblNewLabel_16 = new JLabel("Arrival Terminal");
		lblNewLabel_16.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_15.add(lblNewLabel_16);
		
		arrival_terminal = new JTextField();
		panel_15.add(arrival_terminal);
		arrival_terminal.setColumns(COULUMSNUM);
		
		panel_16 = new JPanel();
		panel_16.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_16);
		
		lblNewLabel_8 = new JLabel("Departure Gates");
		lblNewLabel_8.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_16.add(lblNewLabel_8);
		
		dep_Gates = new JTextField();
		panel_16.add(dep_Gates);
		dep_Gates.setColumns(COULUMSNUM);
		
		lblNewLabel_17 = new JLabel("Arrival Gates");
		lblNewLabel_17.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_16.add(lblNewLabel_17);
		
		arrvial_Gates = new JTextField();
		panel_16.add(arrvial_Gates);
		arrvial_Gates.setColumns(COULUMSNUM);
		
		panel_17 = new JPanel();
		panel_17.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_17);
		
		lblNewLabel_9 = new JLabel("Estimated Departure");
		lblNewLabel_9.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_17.add(lblNewLabel_9);
		
		es_Dep = new JTextField();
		es_Dep.setText("1900-01-01T00:00:00.001");
		panel_17.add(es_Dep);
		es_Dep.setColumns(COULUMSNUM);
		
		lblNewLabel_18 = new JLabel("Estimated Arrival");
		lblNewLabel_18.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_17.add(lblNewLabel_18);
		
		es_Arrival = new JTextField();
		es_Arrival.setText("1900-01-01T00:00:00.001");
		panel_17.add(es_Arrival);
		es_Arrival.setColumns(COULUMSNUM);
		
		panel_18 = new JPanel();
		panel_18.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_18);
		
		lblNewLabel_10 = new JLabel("check-in Location");
		lblNewLabel_10.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_18.add(lblNewLabel_10);
		
		checkin_loc = new JTextField();
		panel_18.add(checkin_loc);
		checkin_loc.setColumns(COULUMSNUM);
		
		panel_19 = new JPanel();
		panel_19.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_19);
		
		lblNewLabel_11 = new JLabel("check-in Counter");
		lblNewLabel_11.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_19.add(lblNewLabel_11);
		
		checkin_Counter = new JTextField();
		panel_19.add(checkin_Counter);
		checkin_Counter.setColumns(COULUMSNUM);
		
		panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_1);
		
		lblNewLabel_12 = new JLabel("check-in Start");
		lblNewLabel_12.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_1.add(lblNewLabel_12);
		
		c_start = new JTextField();
		c_start.setText("1900-01-01T00:00:00.001");
		panel_1.add(c_start);
		c_start.setColumns(COULUMSNUM);
		
		lblNewLabel_19 = new JLabel("check-in End");
		lblNewLabel_19.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_1.add(lblNewLabel_19);
		
		c_End = new JTextField();
		c_End.setText("1900-01-01T00:00:00.001");
		panel_1.add(c_End);
		c_End.setColumns(COULUMSNUM);
		
		panel_2 = new JPanel();
		panel_2.setLayout(new FlowLayout(LAYOUTSTYLE,HGAP,VGAP));
		frame.getContentPane().add(panel_2);
		
		lblNewLabel_13 = new JLabel("Flight_Status");
		lblNewLabel_13.setPreferredSize(new Dimension(lBWMAX,IBHMAX));
		panel_2.add(lblNewLabel_13);
		
		status_box = new JComboBox();
		panel_2.add(status_box);
		
		panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT,HGAP,VGAP));
		frame.getContentPane().add(panel_3);
		
		btnApply = new JButton("Apply");
		panel_3.add(btnApply);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
			
		});
		panel_3.add(btnCancel);
	}

	
	/**
	 * Getter and Setter
	 * @return
	 */
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getIata() {
		return iata;
	}

	public void setIata(JTextField iata) {
		this.iata = iata;
	}

	public JTextField getOperatingAirline() {
		return operatingAirline;
	}

	public void setOperatingAirline(JTextField operatingAirline) {
		this.operatingAirline = operatingAirline;
	}

	public JTextField getAirModelName() {
		return airModelName;
	}

	public void setAirModelName(JTextField airModelName) {
		this.airModelName = airModelName;
	}

	public JTextField getTrackNum() {
		return trackNum;
	}

	public void setTrackNum(JTextField trackNum) {
		this.trackNum = trackNum;
	}

	public JTextField getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(JTextField depAirport) {
		this.depAirport = depAirport;
	}

	public JTextField getOriginDate() {
		return originDate;
	}

	public void setOriginDate(JTextField originDate) {
		this.originDate = originDate;
	}

	public JTextField getScheduled_Dep() {
		return scheduled_Dep;
	}

	public void setScheduled_Dep(JTextField scheduled_Dep) {
		this.scheduled_Dep = scheduled_Dep;
	}

	public JTextField getDep_Terminal() {
		return dep_Terminal;
	}

	public void setDep_Terminal(JTextField dep_Terminal) {
		this.dep_Terminal = dep_Terminal;
	}

	public JTextField getDep_Gates() {
		return dep_Gates;
	}

	public void setDep_Gates(JTextField dep_Gates) {
		this.dep_Gates = dep_Gates;
	}

	public JTextField getEs_Dep() {
		return es_Dep;
	}

	public void setEs_Dep(JTextField es_Dep) {
		this.es_Dep = es_Dep;
	}

	public JTextField getCheckin_loc() {
		return checkin_loc;
	}

	public void setCheckin_loc(JTextField checkin_loc) {
		this.checkin_loc = checkin_loc;
	}

	public JTextField getCheckin_Counter() {
		return checkin_Counter;
	}

	public void setCheckin_Counter(JTextField checkin_Counter) {
		this.checkin_Counter = checkin_Counter;
	}

	public JTextField getC_start() {
		return c_start;
	}

	public void setC_start(JTextField c_start) {
		this.c_start = c_start;
	}

	public JButton getBtnApply() {
		return btnApply;
	}

	public void setBtnApply(JButton btnApply) {
		this.btnApply = btnApply;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JTextField getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(JTextField arrAirport) {
		this.arrAirport = arrAirport;
	}

	public JTextField getScheduled_arrival() {
		return scheduled_arrival;
	}

	public void setScheduled_arrival(JTextField scheduled_arrival) {
		this.scheduled_arrival = scheduled_arrival;
	}

	public JTextField getArrvial_Gates() {
		return arrvial_Gates;
	}

	public void setArrvial_Gates(JTextField arrvial_Gates) {
		this.arrvial_Gates = arrvial_Gates;
	}

	public JTextField getEs_Arrival() {
		return es_Arrival;
	}

	public void setEs_Arrival(JTextField es_Arrival) {
		this.es_Arrival = es_Arrival;
	}

	public JTextField getC_End() {
		return c_End;
	}

	public void setC_End(JTextField c_End) {
		this.c_End = c_End;
	}

	public JComboBox getStatus_box() {
		return status_box;
	}

	public void setStatus_box(JComboBox status_box) {
		this.status_box = status_box;
	}

	public JTextField getArrival_terminal() {
		return arrival_terminal;
	}

	public void setArrival_terminal(JTextField arrival_terminal) {
		this.arrival_terminal = arrival_terminal;
	}

	
	
}
