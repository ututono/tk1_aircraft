package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SeatBooking {

	private JFrame frame;
	JButton AddCartBtn;
	JButton ToCartBtn;
	JButton BackBtn;
	private JTable table;
	private int columsnum;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeatBooking window = new SeatBooking();
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
	public SeatBooking() {
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
		


		
		Object[] columns={"A","B","C","D","E","F"};
		columsnum=columns.length;
		table = new JTable();
		
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		table.setModel(model);
		
	    JScrollPane JSP=  new  JScrollPane(table);
	    JSP.setBounds(0, 0, 753, 500);
	    frame.getContentPane().add(JSP);
		
		AddCartBtn = new JButton("Add to Cart");
		AddCartBtn.setBounds(117, 518, 89, 23);
		frame.getContentPane().add(AddCartBtn);
	
		ToCartBtn = new JButton("Go to Cart");
		ToCartBtn.setBounds(323, 518, 89, 23);
		frame.getContentPane().add(ToCartBtn);
		
		BackBtn = new JButton("Back");
		BackBtn.setBounds(532, 518, 89, 23);
		frame.getContentPane().add(BackBtn);
	
		
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

	public JButton getAddCartBtn() {
		return AddCartBtn;
	}

	public void setAddCartBtn(JButton addCartBtn) {
		AddCartBtn = addCartBtn;
	}

	public JButton getToCartBtn() {
		return ToCartBtn;
	}

	public void setToCartBtn(JButton toCartBtn) {
		ToCartBtn = toCartBtn;
	}

	public JButton getBackBtn() {
		return BackBtn;
	}

	public void setBackBtn(JButton backBtn) {
		BackBtn = backBtn;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	

}
