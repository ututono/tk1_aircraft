import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;


import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;
import utils.DataTransform;
import view.ClientApp;
import view.FlightsOverview;
import view.LoginWin;

public class FlightClient implements IFlightClient {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());
	

	private String clientname;
	private List<Flight> flights;
	FlightsOverview overView;

	public void initView(List<Flight>flights) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				overView = new FlightsOverview();
				overView.getFrame().setVisible(true);
				
				DefaultTableModel defaultTableModel=new DefaultTableModel();
				Object[] columns={"Operating Airpline","IATA Code","Tracking Number","Departure Airport","Arrival Airport","Terminal","Scheduled Time","Estimated Time"};
				defaultTableModel.setColumnIdentifiers(columns);
				JTable table=overView.getTable();
				
				JButton modifyButton=overView.getBtnModify();
				JButton addButton=overView.getBtnAdd();
				JButton deleteButton=overView.getBtnDelete();
				JButton logoutButton=overView.getBtnlogout();
				
				for(int i=0;i<flights.size();i++) {
					String[] rows=new String[table.getColumnCount()];
					
					rows[0]=flights.get(i).getName();
					rows[1]=flights.get(i).getIata();
					rows[2]=flights.get(i).getFlightNum();
					rows[3]=flights.get(i).getDepartureAirport();
					rows[4]=flights.get(i).getArrivalAirport();
					rows[5]=flights.get(i).getDep_terminal();
					rows[6]=flights.get(i).getScheduled_dep().toString();
					rows[7]=flights.get(i).getEs_dep().toString();
					defaultTableModel.addRow(rows);
					
				}
				
				table.setModel(defaultTableModel);
				modifyButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(table.getSelectedRow()==-1){
							//Prompt a dialog if user does not select any flight
							JOptionPane.showMessageDialog(overView.getFrame(), "Please select a flight");
						}else {
							initDetailView(flights.get(table.getSelectedRow()));	
							overView.getFrame().dispose();
							overView=null;
						}
					}
					
				});
				
				deleteButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(table.getSelectedRow()==-1){
							//Prompt a dialog if user does not select any flight
							JOptionPane.showMessageDialog(overView.getFrame(), "Please select a flight");
						}else {
							// call remote service
							try {
								Flight flight=flights.get(table.getSelectedRow());
								Registry registry = LocateRegistry.getRegistry(HOSTNAME,PORT);
								IFlightServer stub=(IFlightServer) registry.lookup(SERVICENAME);
								stub.deleteFlight(clientname, flight);
								logger.log(Level.INFO, "Modify Flight "+flight.toString());
								
							} catch (RemoteException | NotBoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}


						}
					}
					
				});
				
				addButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						initDetailView();	
						overView.getFrame().dispose();
						overView=null;

					}
					
				});
				
				logoutButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Registry registry = LocateRegistry.getRegistry(HOSTNAME,PORT);
							IFlightServer stub=(IFlightServer) registry.lookup(SERVICENAME);
							stub.logout(clientname);
							overView.getFrame().dispose();
							System.gc();
						    System.runFinalization();
						    main(null);
						} catch (RemoteException | NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
	
	/**
	 * create and show GUI of flights detail
	 * To modify a existing flight
	 */
	public void initDetailView(Flight flight) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApp window = new ClientApp();
					window.getFrame().setVisible(true);
					window.getIata().setText(flight.getIata());
					window.getOperatingAirline().setText(flight.getName());
					window.getAirModelName().setText(flight.getAircraftType());
					window.getTrackNum().setText(flight.getFlightNum());
					window.getDepAirport().setText(flight.getDepartureAirport());
					window.getArrAirport().setText(flight.getArrivalAirport());
					//TODO date
					//window.getOriginDate()
					LocalDateTime scheduled_arrive=flight.getScheduled_arrival();
					LocalDateTime scheduled_dep=flight.getScheduled_dep();
					LocalDateTime es_arrival=flight.getEs_arrival();
					LocalDateTime es_dep=flight.getEs_dep();
					LocalDateTime checkinStart=flight.getCheckinStart();
					LocalDateTime checkinEnd=flight.getCheckinEnd();
					
					window.getScheduled_arrival().setText(flight.getScheduled_arrival().toString());
					window.getScheduled_Dep().setText(flight.getScheduled_dep().toString());
					window.getDep_Terminal().setText(flight.getDep_terminal());
					window.getArrival_terminal().setText(flight.getArrival_terminal());
					window.getArrvial_Gates().setText(flight.getArrival_gates());
					window.getDep_Gates().setText(flight.getDep_gates());
					window.getEs_Arrival().setText(flight.getEs_arrival().toString());
					window.getEs_Dep().setText(flight.getEs_dep().toString());;
					window.getCheckin_loc().setText(flight.getCheckinLocation());
					window.getCheckin_Counter().setText(flight.getCheckinCounter().toString());
					
					window.getC_End().setText(flight.getCheckinEnd().toString());
					window.getC_start().setText(flight.getCheckinStart().toString());
					
					JButton cancelButton=window.getBtnCancel();
					cancelButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							initView(flights);
							window.getFrame().dispose();
						}
						
					});
					
					JButton applyButton=window.getBtnApply();
					applyButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							Flight flight=new Flight();
							flight.setIata(window.getIata().getText());
							flight.setName(window.getOperatingAirline().getText());
							flight.setAircraftType(window.getAirModelName().getText());
							flight.setFlightNum(window.getTrackNum().getText());
							flight.setDepartureAirport(window.getDepAirport().getText());
							flight.setArrivalAirport(window.getArrAirport().getText());
							//TODO date scheduled/estimated/checkinstart
							//window.getOriginDate()
							LocalDateTime tmp;
							tmp=DataTransform.string2LocalDateTime(window.getScheduled_arrival().getText());
							if(tmp==null) {
								flight.setScheduled_arrival(scheduled_arrive);
								JOptionPane.showMessageDialog(overView.getFrame(), "Enter wrong date format,Please check");
							}else flight.setScheduled_arrival(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getScheduled_Dep().getText());
							if(tmp==null) {
								flight.setScheduled_dep(scheduled_dep);
								JOptionPane.showMessageDialog(overView.getFrame(), "Enter wrong date format,Please check");
							}else flight.setScheduled_dep(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getEs_Arrival().getText());
							if(tmp==null) {
								flight.setEs_arrival(es_arrival);
								JOptionPane.showMessageDialog(overView.getFrame(), "Enter wrong date format,Please check");
							}else flight.setEs_arrival(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getEs_Dep().getText());
							if(tmp==null) {
								flight.setEs_dep(es_dep);
								JOptionPane.showMessageDialog(overView.getFrame(), "Enter wrong date format,Please check");
							}else flight.setEs_dep(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getC_start().getText());
							if(tmp==null) {
								flight.setCheckinStart(checkinStart);
							}else flight.setCheckinStart(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getC_End().getText());
							if (tmp==null) {
								flight.setCheckinEnd(checkinEnd);
								JOptionPane.showMessageDialog(overView.getFrame(), "Enter wrong date format,Please check");
							}else flight.setCheckinEnd(tmp);
							
							flight.setCheckinCounter(window.getCheckin_Counter().getText());
							flight.setArrival_terminal(window.getArrival_terminal().getText());
							flight.setDep_terminal(window.getDep_Terminal().getText());
							flight.setArrival_gates(window.getArrvial_Gates().getText());
							flight.setDep_gates(window.getDep_Gates().getText());
							flight.setCheckinLocation(window.getCheckin_loc().getText());
							
							
							
							
							//create and show a confirmdialog; 
							//Yes is 0, No is 1
							int n = JOptionPane.showConfirmDialog(
								    window.getFrame(),
								    "Are you sure you want to modify this flight?",
								    "The execution can not be undo",
								    JOptionPane.YES_NO_OPTION);
							if(n==0) {
								//The answer is yes
								//call remote update flights on the server
								try {
									Registry registry=LocateRegistry.getRegistry(HOSTNAME,PORT);
									IFlightServer stub=(IFlightServer) registry.lookup(SERVICENAME);
									logger.log(Level.INFO, "Modify Flight "+flight.toString());
									stub.updateFlight(clientname, flight);
									window.getFrame().dispose();

								} catch (RemoteException | NotBoundException er) {
									er.printStackTrace();
								}
								
							}else {
								//select cancel
								
							}

						}
						
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	
	/**
	 * Creat an show GUI of flight details
	 * to ADD a new flight. 
	 */
	public void initDetailView() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApp window = new ClientApp();
					window.getFrame().setVisible(true);
					window.getIata().setEditable(true);
					window.getAirModelName().setEditable(true);
					JButton applyButton=window.getBtnApply();
					JButton cancelButton=window.getBtnCancel();
					cancelButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							initView(flights);
							window.getFrame().dispose();
						}
						
					});
					applyButton.addActionListener(new ActionListener() {
					String defaultTime="1900-01-01T00:00:00.001";
					LocalDateTime defaultLDT=DataTransform.string2LocalDateTime(defaultTime);
						@Override
						public void actionPerformed(ActionEvent e) {
							Flight flight=new Flight();
							flight.setIata(window.getIata().getText());
							flight.setName(window.getOperatingAirline().getText());
							flight.setAircraftType(window.getAirModelName().getText());
							flight.setFlightNum(window.getTrackNum().getText());
							flight.setDepartureAirport(window.getDepAirport().getText());
							flight.setArrivalAirport(window.getArrAirport().getText());
							//TODO date scheduled/estimated/checkinstart
							//window.getOriginDate()
							LocalDateTime tmp;
							tmp=DataTransform.string2LocalDateTime(window.getScheduled_arrival().getText());
							flight.setScheduled_arrival(tmp);
							if(tmp==null) {
								flight.setScheduled_arrival(defaultLDT);
								JOptionPane.showMessageDialog(window.getFrame(), "Enter wrong date format,Please check");
							}else flight.setScheduled_arrival(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getScheduled_Dep().getText());
							flight.setScheduled_dep(tmp);
							if(tmp==null) {
								flight.setScheduled_dep(defaultLDT);
								JOptionPane.showMessageDialog(window.getFrame(), "Enter wrong date format,Please check");
							}else flight.setScheduled_dep(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getEs_Arrival().getText());
							if(tmp==null) {
								flight.setEs_arrival(defaultLDT);
								JOptionPane.showMessageDialog(window.getFrame(), "Enter wrong date format,Please check");
							}else flight.setEs_arrival(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getEs_Dep().getText());
							if(tmp==null) {
								flight.setEs_dep(defaultLDT);
								JOptionPane.showMessageDialog(window.getFrame(), "Enter wrong date format,Please check");
							}else flight.setEs_dep(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getC_start().getText());
							if(tmp==null) {
								flight.setCheckinStart(defaultLDT);
							}else flight.setCheckinStart(tmp);
							tmp=DataTransform.string2LocalDateTime(window.getC_End().getText());
							if (tmp==null) {
								flight.setCheckinEnd(defaultLDT);
								JOptionPane.showMessageDialog(window.getFrame(), "Enter wrong date format,Please check");
							}else flight.setCheckinEnd(tmp);
							
							flight.setCheckinCounter(window.getCheckin_Counter().getText());
							flight.setArrival_terminal(window.getArrival_terminal().getText());
							flight.setDep_terminal(window.getDep_Terminal().getText());
							flight.setArrival_gates(window.getArrvial_Gates().getText());
							flight.setDep_gates(window.getDep_Gates().getText());
							flight.setCheckinLocation(window.getCheckin_loc().getText());
							
							
							
							
							//create and show a confirmdialog; 
							//Yes is 0, No is 1
							int n = JOptionPane.showConfirmDialog(
								    window.getFrame(),
								    "Are you sure you want to modify this flight?",
								    "The execution can not be undo",
								    JOptionPane.YES_NO_OPTION);
							if(n==0) {
								//The answer is yes
								//call remote update flights on the server
								try {
									Registry registry=LocateRegistry.getRegistry(HOSTNAME,PORT);
									IFlightServer stub=(IFlightServer) registry.lookup(SERVICENAME);
									logger.log(Level.INFO, "Modify Flight "+flight.toString());
									stub.updateFlight(clientname, flight);
									window.getFrame().dispose();

								} catch (RemoteException | NotBoundException er) {
									er.printStackTrace();
								}
								
							}else {
								//select cancel
								
							}
						}
					});

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initLoginView() {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				LoginWin window = new LoginWin();
				window.getFrame().setVisible(true);
				JLabel clientname=window.getLbclientname();
				JButton loginBT=window.getBtnlogin();
				clientname.setText(getClientname());
				loginBT.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						connectRMIserver();
						window.getFrame().dispose();
					}
					
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}

	public FlightClient(String clientName) {
		this.clientname=clientName;
		this.flights=new ArrayList<Flight>();
	}

	@Override
	public void receiveListOfFlights(List<Flight> flights) {
		logger.log(Level.INFO, "List of flights received: " + flights.size());
		if (overView==null) {
			// create and show GUI
			initView(flights);			
		}else {
			
			JTable table=overView.getTable();
			DefaultTableModel defaultTableModel=new DefaultTableModel();
			Object[] columns={"Operating Airpline","IATA Code","Tracking Number","Departure Airport","Arrival Airport","Terminal","Scheduled Time","Estimated Time"};
			defaultTableModel.setColumnIdentifiers(columns);
			for(int i=0;i<flights.size();i++) {
				String[] rows=new String[table.getColumnCount()];
				
				rows[0]=flights.get(i).getName();
				rows[1]=flights.get(i).getIata();
				rows[2]=flights.get(i).getFlightNum();
				rows[3]=flights.get(i).getDepartureAirport();
				rows[4]=flights.get(i).getArrivalAirport();
				rows[5]=flights.get(i).getDep_terminal();
				rows[6]=flights.get(i).getScheduled_dep().toString();
				rows[7]=flights.get(i).getEs_dep().toString();
				defaultTableModel.addRow(rows);
				
			}
			table.setModel(defaultTableModel);
		}
	}

	@Override
	public void receiveUpdatedFlight(Flight flight, boolean deleted) {
		logger.log(Level.INFO, "Flight updated: " + flight.toString());
	}


	
	public void startup() {
		initLoginView();

		
		// register the client on RMI server
		
		
             	
		
	}
	
	
	private void connectRMIserver() {
		try {
			Registry registry=LocateRegistry.getRegistry(HOSTNAME,PORT);
			IFlightServer stub=(IFlightServer) registry.lookup(SERVICENAME);
			stub.login(clientname, this);
			logger.log(Level.INFO, "List of set flights: " + this.getFlights().size());
			for (int i = 0; i < flights.size(); i++) {
				System.out.println(flights.get(i).toString());
			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(clientname, flights);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightClient other = (FlightClient) obj;
		return Objects.equals(clientname, other.clientname) && Objects.equals(flights, other.flights);
	}
	
	
	
	

	@Override
	public String toString() {
		return "FlightClient [clientname=" + clientname + "]";
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		
		for (int i = 0; i < flights.size(); i++) {
		this.flights.add(flights.get(i));
		System.out.println("setFlights()"+this.flights.get(i).toString());
	}
	}

	public static void main(String[] args) {
		FlightClient client = new FlightClient(UUID.randomUUID().toString());
		client.startup();
	}

}
