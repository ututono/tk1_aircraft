package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cart {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart window = new Cart();
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
	public Cart() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 630, 445);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultListModel<String> listModel=new DefaultListModel();
		JList flightsRecap = new JList();
		
		listModel.addElement("sdfafda");
		listModel.addElement("sdfafda");
		flightsRecap.setModel(listModel);
		flightsRecap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		flightsRecap.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		flightsRecap.setVisibleRowCount(-1);
		flightsRecap.setBounds(0, 0, 1, 1);
//		frame.getContentPane().add(flightsRecap);
		
	    JScrollPane JSP=  new  JScrollPane(flightsRecap);
	    JSP.setBounds(0, 0, 200, 200);
	    frame.getContentPane().add(JSP);
	    
	    JPanel panel_1 = new JPanel();
	    Dimension panel_1_D=new Dimension(635, 35);
	    panel_1.setMaximumSize(panel_1_D);
	    panel_1.setPreferredSize(panel_1_D);
	    frame.getContentPane().add(panel_1);
	    
	    JButton buyBtn = new JButton("Buy");
	    panel_1.add(buyBtn);
	    
	    JButton deleteBtn = new JButton("DeleteItem");
	    deleteBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    panel_1.add(deleteBtn);
	    
	    JButton tobookingBtn = new JButton("To Booking");
	    panel_1.add(tobookingBtn);
	}

}
