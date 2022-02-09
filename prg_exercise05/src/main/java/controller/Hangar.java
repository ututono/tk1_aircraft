package controller;

import controller.TransferManager;
import ui.MainWindow;
import utilss.RandomGenerator;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hangar implements Runnable{
    private TransferManager transferManager;

    private final String serverIP="127.0.0.1";
    private Integer port;
    private ServerSocket serverSocket;
    private Socket socket;
    private Logger logger;
    private Boolean[] markerReceived = new Boolean[] {false, false};
    private Boolean[] markerSent = new Boolean[] {false, false};
    private Boolean[] enableRecord = new Boolean[] {false, false};
    private boolean sendState = false;
    private String record;
    private String sender;
    private String initiator;
    private List<String> states;
    private HashMap<String, TransferManager> channelsmap;
    private MainWindow mainWindow;


//    public controller.Hangar(Integer port) {
//        transferManager=new TransferManager(VOLUME);
//        this.port=port;
//
//        try {
//            serverSocket= new ServerSocket(this.port); // listen on the port
//
//            while (true){
//                Socket socket=serverSocket.accept();
//                Thread send=new Thread(new Send(socket,transferManager));
//                Thread receive=new Thread(new Receive(socket,transferManager));
//                send.start();
//                receive.start();
//            }
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    // Identifier of this hanger
    String identifier;

    // Name of adjacent hangers
    String h1, h2;

    // Airplanes stored in this Hanger.
    // The default upper bound of the amount of airplane is 10
    private ArrayList<String> airplanes;
    private final Integer VOLUME=10;

    // Each channel has own Transfermanager to manager data
    private TransferManager c1;
    private TransferManager c2;


    public Hangar(String id, String h1, String h2,MainWindow mainWindow){
        // Initialize airplanes
        airplanes=new ArrayList<>();
        for (int i = 0; i < VOLUME; i++) {
            airplanes.add("["+id+" Hanger ] "+"with Number "+i);
        }

        // Initialize Transfermanager for each channel
        c1=new TransferManager(VOLUME);
        c2=new TransferManager(VOLUME);

        // initial identifiers
        identifier=id;
        this.h1=h1;
        this.h2=h2;

        states=new ArrayList<>();

        this.mainWindow=mainWindow;

        channelsmap=new HashMap<>();
        channelsmap.put(h1,c1);
        channelsmap.put(h2,c2);


    }

    /**
     * Send random amount of flight to a random channel
     */
    public void sendFlights(){

        int channelnum= RandomGenerator.getRandomNumber(0,1);

        int transferedSum;
        if (airplanes.size()>0){
            transferedSum=RandomGenerator.getRandomNumber(1,airplanes.size());
        }else{
            return;
        }

        ArrayList<String> transferedAirplanes=new ArrayList<>();

        // select (transferedsum) planes randomly to transfer
        for (int i=0;i<transferedSum;i++){
            int index=RandomGenerator.getRandomNumber(0,airplanes.size()-1);
            transferedAirplanes.add(airplanes.get(index));
            airplanes.remove(index);
        }


        // select a channel randomly to send those airplanes
        if (channelnum==0){
            String msg="Transfer: " + identifier + " -> " + h1 + " (" +transferedSum+ ")";
            mainWindow.getHistoryListModel().addElement(msg);
            c1.enqueue(identifier,h1,airplanes);
        }else {
            String msg="Transfer: " + identifier + " -> " + h2 + " (" +transferedSum+ ")";
            mainWindow.getHistoryListModel().addElement(msg);
            c2.enqueue(identifier,h2,airplanes);
        }



    }


    public void addAirplanes(List<String> airplanes){
        for (String flight:airplanes
             ) {
            this.airplanes.add(flight);
        }
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addStateRecord(String state){
        states.add(state);
    }

    public Integer getQueuesize(String channelname){
        return channelsmap.get(channelname).getSize();
    }

    @Override
    public void run() {
        while (true){
            threadSleep(4000);
            sendFlights();
        }

    }


    public Boolean[] isMarkerSent() {
        return this.markerSent;
    }

    public Boolean[] isEnableRecord() {
        return this.enableRecord;
    }

    public TransferManager getTransferManagerByName(String c){
        return channelsmap.get(c);
    }

    public void setMarkerReceived(Boolean[] markerReceived, String sender) {
        this.markerReceived = markerReceived;
        this.sender = sender;
    }

    public void setMarkerSent(Boolean[] markerSent) {
        this.markerSent = markerSent;
    }

    /**
     *     record the state of hangar
     *     Semantic of record: hanger with the amount of airplanes it owns so far
     *
     */
    public void recordState(String destination) {

        record = identifier + ":<" + Integer.toString(this.airplanes.size()) + ">;";
        record=getRecord() + "C<" + identifier + "," + destination + ">:<>;";
        states.add(record);
        logger.info(identifier+ " state recorded: " + record);
    }

    public void startSnapshot(){
        setMarkerReceived(new Boolean[]{true,true},identifier);
        initiator=identifier;
        enableRecord=new Boolean[]{true,true};
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getRecord() {
        return record;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getH1() {
        return h1;
    }

    public String getH2() {
        return h2;
    }

    public TransferManager getC1() {
        return c1;
    }

    public TransferManager getC2() {
        return c2;
    }

    public Boolean[] getMarkerReceived() {
        return markerReceived;
    }

    public Boolean[] getMarkerSent() {
        return markerSent;
    }

    public Boolean[] getEnableRecord() {
        return enableRecord;
    }

    public boolean isSendState() {
        return sendState;
    }

    public String getSender() {
        return sender;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setEnableRecord(Boolean[] booleans) {
        this.enableRecord=booleans;
    }

    public void setSendState(boolean sendState) {
        this.sendState = sendState;
    }
}
