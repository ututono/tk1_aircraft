package ui;

import controller.Hangar;
import controller.Receive;
import controller.Send;
import controller.Coordinator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

	private DefaultListModel<String> historyListModel;
	private JList<String> historyList;

	private JLabel snapshot1Label;
	private JButton snapshot1Button;

	private JLabel snapshot2Label;
	private JButton snapshot2Button;

	private JLabel snapshot3Label;
	private JButton snapshot3Button;

	private Hangar h1,h2,h3;
	private Coordinator o1, o2, o3;
	private Receive r12,r13,r21,r23,r31,r32;
	private Send s12,s13,s21,s23,s31,s32;
	
	public static void main(String[] args) throws IOException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}

	public MainWindow() throws IOException {
		setSize(400, 300);
		setTitle("TK1-EX6");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// history list
		historyListModel = new DefaultListModel<String>();
		historyList = new JList<String>(historyListModel);
		historyList.setAutoscrolls(true);
		
		JScrollPane historyScroll = new JScrollPane(historyList);
		add(historyScroll, BorderLayout.CENTER);

		// slide panel
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(3, 1));

		// snapshot 1
		snapshot1Label = new JLabel("controller.Hangar 1 (#0)");
		snapshot1Button = new JButton("Snapshot");
		snapshot1Button.addActionListener(x -> snapshot(1));

		JPanel snapshot1 = new JPanel();
		snapshot1.setLayout(new GridLayout(2, 1));
		snapshot1.add(snapshot1Label);
		snapshot1.add(snapshot1Button);
		sidePanel.add(snapshot1);

		// snapshot 2
		snapshot2Label = new JLabel("controller.Hangar 2 (#0)");
		snapshot2Button = new JButton("Snapshot");
		snapshot2Button.addActionListener(x -> snapshot(2));

		JPanel snapshot2 = new JPanel();
		snapshot2.setLayout(new GridLayout(2, 1));
		snapshot2.add(snapshot2Label);
		snapshot2.add(snapshot2Button);
		sidePanel.add(snapshot2);

		// snapshot 3
		snapshot3Label = new JLabel("controller.Hangar 3 (#0)");
		snapshot3Button = new JButton("Snapshot");
		snapshot3Button.addActionListener(x -> snapshot(3));

		JPanel snapshot3 = new JPanel();
		snapshot3.setLayout(new GridLayout(2, 1));
		snapshot3.add(snapshot3Label);
		snapshot3.add(snapshot3Button);
		sidePanel.add(snapshot3);

		add(sidePanel, BorderLayout.EAST);

		// create 3 hangars and start the threads
		// Each hanger has two port used to connect adjacent nodes
		h1 = new Hangar("H1", "H2", "H3",this);
		new Thread(h1).start();
		h2 = new Hangar("H2", "H1", "H3",this);
		new Thread(h2).start();
		h3 = new Hangar("H3", "H1", "H2",this);
		new Thread(h3).start();

		/*

			 Hangar_1 port:10002								Hangar_2 port: 10000
			 		|				----------------------->           |
			 		|												   |
			 		|				<------------------------          |



		 */

		// create UDP Receiver for each channel
		// r12 -> hangar 1 receives from hangar 2
		r12 = new Receive(10002, h1, "H2",this);
		new Thread(r12).start();
		r13 = new Receive(10004, h1, "H3",this);
		new Thread(r13).start();

		r21 = new Receive(10000, h2, "H1",this);
		new Thread(r21).start();
		r23 = new Receive(10005, h2, "H3",this);
		new Thread(r23).start();

		r31 = new Receive(10001, h3, "H1",this);
		new Thread(r31).start();
		r32 = new Receive(10003, h3, "H2",this);
		new Thread(r32).start();

		// create UDP client for each channel
		// s12 -> hangar 1 sends to hangar 2
		// tuple (rij, sji) use the same channel
		s12 = new Send(10000); //s21
		s13 = new Send(10001); //s31

		s21 = new Send(10002); //s12
		s23 = new Send(10003); //s32

		s31 = new Send(10004); //s13
		s32 = new Send(10005); //s23

		// create 3 dispatchers for each hangar with their sending channels
		o1 = new Coordinator(h1, s12, s13,this);
		new Thread(o1).start();
		o2 = new Coordinator(h2, s21, s23,this);
		new Thread(o2).start();
		o3 = new Coordinator(h3, s31, s32,this);
		new Thread(o3).start();


	}

	public DefaultListModel<String> getHistoryListModel() {
		return historyListModel;
	}

	private void snapshot(int snapshot) {
		switch(snapshot) {
			case 1:
				historyListModel.addElement("Snapshot: H1 initiator");
				h1.startSnapshot();
				break;
			case 2:
				historyListModel.addElement("Snapshot: H2 initiator");
				h2.startSnapshot();
				break;
			case 3:
				historyListModel.addElement("Snapshot: H3 initiator");
				h2.startSnapshot();
				break;
		}
	}

}
