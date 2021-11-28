package view;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.HorizontalDirection;

import model.Flight;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

public class FlightsOverview {

	private JFrame frame;
	JButton btnModify;
	JButton btnDelete;
	JButton btnAdd;
	private JButton btnlogout;
	private JTable table;
	private int columsnum;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightsOverview window = new FlightsOverview();
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
	public FlightsOverview() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 753, 654);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		


		
		Object[] columns={"Operating Airpline","IATA Code","Tracking Number","Departure Airport","Arrival Airport","Terminal","Scheduled Time","Estimated Time"};
		columsnum=columns.length;
		table = new JTable();
		
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		table.setModel(model);
		
	    JScrollPane JSP=  new  JScrollPane(table);
	    JSP.setBounds(0, 0, 737, 479);
	    frame.getContentPane().add(JSP);
		
		btnModify = new JButton("Modify");
		btnModify.setBounds(117, 518, 89, 23);
		frame.getContentPane().add(btnModify);
	
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(323, 518, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(529, 518, 89, 23);
		frame.getContentPane().add(btnAdd);
			
		btnlogout = new JButton("Logout");
		btnlogout.setBounds(323, 581, 89, 23);
		frame.getContentPane().add(btnlogout);
	
		
//		
//		JList flightsRecap = new JList();
//		flightsRecap.setModel(listModel);
//		flightsRecap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		flightsRecap.setLayoutOrientation(JList.HORIZONTAL_WRAP);
////		flightsRecap.setVisibleRowCount(-1);
//		flightsRecap.setBounds(0, 0, 1, 1);
//		frame.getContentPane().add(flightsRecap);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnlogout() {
		return btnlogout;
	}

	public void setBtnlogout(JButton btnlogout) {
		this.btnlogout = btnlogout;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public int getColumsnum() {
		return columsnum;
	}

	public void setColumsnum(int columsnum) {
		this.columsnum = columsnum;
	}
	
	
	
}
