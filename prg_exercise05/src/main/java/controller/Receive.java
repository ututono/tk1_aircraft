package controller;

import ui.MainWindow;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.List;

public class Receive implements Runnable{

    private static final int PACKETSIZE=256;
    private DatagramSocket socket;
    // listen on the port
    private int port;
    private String source, destination;
    private Hangar hangar;
    private MainWindow mainWindow;

    /**
     *
     * @param port listen on this port
     * @param hangar operation on this hanger
     * @param destination
     * @throws SocketException
     */

    public Receive(int port, Hangar hangar, String destination, MainWindow mainWindow) throws SocketException {
        this.port = port;
        this.destination = destination;
        this.hangar = hangar;
        this.source=hangar.getIdentifier();
        this.socket=new DatagramSocket(this.port);
        this.mainWindow=mainWindow;
    }

    private void handle() throws IOException {
        System.out.println("Running Server at " + InetAddress.getLocalHost() + ":" + port);

        while (true){
            DatagramPacket packet=new DatagramPacket(new byte[PACKETSIZE],PACKETSIZE);

            // Blocking receive
            socket.receive(packet);

            // Extract data from packet and deserialize them
            byte[] data=packet.getData();
            List<String> payload= (List<String>) deserialize(data);

            // Check if the message is airplanes , marker or state
            String header=payload.get(0);
            if (header.equals(Send.MARKERCODE)){
                // If it is marker
                System.out.println(hangar.getIdentifier() + " received marker message from " + destination);
                hangar.setInitiator(payload.get(1));
                handleMarkerMessage();

            }else if(header.equals(Send.STATECODE)){
                // if it is state massage
                // record  next element from payload as state
                hangar.addStateRecord(payload.get(1));

                //TODO Add recorded states to historyListModel
                mainWindow.getHistoryListModel().addElement("Global State: " + hangar.getRecord());

            }else {
                // if it is airplanes
                hangar.addAirplanes(payload);
                TransferManager tm=hangar.getTransferManagerByName(destination);
                tm.enqueue(destination,hangar.identifier,payload);

                // Record this transaction if enable recording on the incoming channel
                if (hangar.isEnableRecord()[0]){
                    String record="From "+hangar.identifier+" to "+hangar.getH1()+" : "+ payload.size();
                    hangar.addStateRecord(record);
                }else if(hangar.isEnableRecord()[1]){
                    String record="From "+hangar.identifier+" to "+hangar.getH2()+" : "+ payload.size();
                    hangar.addStateRecord(record);
                }
            }

            threadSleep(500);
        }

    }

    @Override
    public void run() {
        try {
            handle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Object deserialize(byte[] data){
        try(ByteArrayInputStream bin = new ByteArrayInputStream(data)) {
            try(ObjectInputStream ois = new ObjectInputStream(bin)) {
                return ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void handleMarkerMessage() {

        if (!hangar.isMarkerSent()[0] && !hangar.isMarkerSent()[1]) {
            // If neither c1 nor c2 has not sent marker i.e. hanger has never record its state
            //record current state of the hangar
            hangar.recordState(destination);
//            String record=hangar.getRecord() + "C<" + hangar.getIdentifier() + "," + destination + ">:<>;";
//            hangar.setRecord(record);

            hangar.setMarkerReceived(new Boolean[] {true, true}, destination);

            if (hangar.getH1().equals(destination)) {
                // if adjacent node h1 is the receiver of the marker message
                // then, set record flag of h1 to false
                hangar.setEnableRecord(new Boolean[] {false, hangar.isEnableRecord()[1]});
            } else {
                // if adjacent node h2 is the receiver of the marker message
                // then, set record flag of h2 to false
                hangar.setEnableRecord(new Boolean[] {hangar.isEnableRecord()[0], false});
            }

        } else {
            // If c1 or c2 has sent marker
            hangar.setMarkerReceived(new Boolean[] {false, false}, destination);

            if (hangar.getH1().equals(destination)) {
                hangar.setEnableRecord(new Boolean[] {false, hangar.isEnableRecord()[1]});
            } else {
                hangar.setEnableRecord(new Boolean[] {hangar.isEnableRecord()[0], false});
            }
//            logger.info(identifier + " first channel: "+ !hangar.isEnableRecord()[0] + "; second channel:" + !hangar.isEnableRecord()[1]);
//            logger.info("C<" + destination + "," + hangar.getIdentifier() + ">" + " state recorded.");

            //
            if (!hangar.isEnableRecord()[0] && !hangar.isEnableRecord()[1]) {
                if (hangar.getInitiator().equals(hangar.getIdentifier())) {
                    mainWindow.getHistoryListModel().addElement("Global State: " + hangar.getRecord());
                } else {
                    hangar.setSendState(true);
                }
            }
        }

    }
//    @Override
//    public void run() {
//        try(InputStream input=socket.getInputStream()){
//            handle(input);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handle(InputStream input) throws IOException, ClassNotFoundException {
//        ObjectInputStream objInput=new ObjectInputStream(input);
//        Transaction transaction= (Transaction) objInput.readObject();
//        transferManager.add(transaction);
//        System.out.println(transaction.toString());
//
//
//    }
}
