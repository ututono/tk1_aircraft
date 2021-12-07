package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class FlightSect {

	private JFrame frame;
	JList list;
	JButton backBtn;
	JButton nextBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightSect window = new FlightSect();
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
	public FlightSect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		list= new JList();
		frame.getContentPane().add(list, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		backBtn = new JButton("Back");
		panel.add(backBtn);
		
		nextBtn = new JButton("Next");
		panel.add(nextBtn);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public JButton getBackBtn() {
		return backBtn;
	}

	public void setBackBtn(JButton backBtn) {
		this.backBtn = backBtn;
	}

	public JButton getNextBtn() {
		return nextBtn;
	}

	public void setNextBtn(JButton nextBtn) {
		this.nextBtn = nextBtn;
	}
	
	

}
