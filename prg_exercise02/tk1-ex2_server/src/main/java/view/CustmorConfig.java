package view;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CustmorConfig {

	private JFrame frame;
	private JComboBox dateBox;
	private JComboBox destinationBox;
	private JButton logoutBtn;
	private JButton nextBtn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustmorConfig window = new CustmorConfig();
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
	public CustmorConfig() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 526, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Destination");
		panel.add(lblNewLabel);
		
		destinationBox = new JComboBox();
		destinationBox.setPreferredSize(new Dimension(250,20));
		panel.add(destinationBox);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		panel_1.add(lblNewLabel_1);
		
		dateBox = new JComboBox();
		dateBox.setPreferredSize(new Dimension(250,20));
		panel_1.add(dateBox);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		
		nextBtn = new JButton("Next");
		panel_2.add(nextBtn);
		
		logoutBtn = new JButton("Logout");
		panel_2.add(logoutBtn);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JComboBox getDateBox() {
		return dateBox;
	}

	public void setDateBox(JComboBox dateBox) {
		this.dateBox = dateBox;
	}

	public JComboBox getDestinationBox() {
		return destinationBox;
	}

	public void setDestinationBox(JComboBox destinationBox) {
		this.destinationBox = destinationBox;
	}

	public JButton getLogoutBtn() {
		return logoutBtn;
	}

	public void setLogoutBtn(JButton logoutBtn) {
		this.logoutBtn = logoutBtn;
	}

	public JButton getNextBtn() {
		return nextBtn;
	}

	public void setNextBtn(JButton nextBtn) {
		this.nextBtn = nextBtn;
	}
	
	

}
