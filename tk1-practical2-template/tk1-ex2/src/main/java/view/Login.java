package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frame;
	private JLabel lbclientname;
	private JButton btnlogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Login ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setToolTipText(" ");
		lblNewLabel.setBounds(149, 37, 123, 43);
		frame.getContentPane().add(lblNewLabel);
		
		lbclientname = new JLabel("New label");
		lbclientname.setHorizontalAlignment(SwingConstants.CENTER);
		lbclientname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbclientname.setBounds(47, 91, 330, 43);
		frame.getContentPane().add(lbclientname);
		
		btnlogin = new JButton("Login");
		btnlogin.setBounds(149, 191, 123, 36);
		frame.getContentPane().add(btnlogin);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JLabel getLbclientname() {
		return lbclientname;
	}

	public void setLbclientname(JLabel lbclientname) {
		this.lbclientname = lbclientname;
	}

	public JButton getBtnlogin() {
		return btnlogin;
	}

	public void setBtnlogin(JButton btnlogin) {
		this.btnlogin = btnlogin;
	}
	
	
	
	
}
