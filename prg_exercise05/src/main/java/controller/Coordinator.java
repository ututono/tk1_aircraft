package controller;

import controller.Send;
import controller.Hangar;
import model.Transaction;
import ui.MainWindow;
import utilss.RandomGenerator;

import java.io.IOException;
import java.util.List;

public class Coordinator implements Runnable {
    private Hangar hangar;
    private String h1,h2;
    private String id;
    private Send c1,c2;
    private MainWindow mainWindow;

    public Coordinator(Hangar hangar, Send c1, Send c2) {
        this.hangar = hangar;
        this.h1 = hangar.getH1();
        this.h2 = hangar.getH2();
        this.id = hangar.getIdentifier();
        this.c1 = c1;
        this.c2 = c2;
    }

    public Coordinator(Hangar hangar, Send c1, Send c2, MainWindow mainWindow) {
        this.hangar = hangar;
        this.h1 = hangar.getH1();
        this.h2 = hangar.getH2();
        this.id = hangar.getIdentifier();
        this.c1 = c1;
        this.c2 = c2;
        this.mainWindow=mainWindow;
    }

    @Override
    public void run() {


        while (true){

            int channal= RandomGenerator.getRandomNumber(0,1);

            // Sending marker message iff received a marker message over any channel && not sent a marker message
            if (hangar.getMarkerReceived()[0]){
                // If over the first channel the hangar has received a marker

                if (!hangar.isMarkerSent()[0]){
                    // if the hangar has not sent marker over the first channel
                    // then, send marker via thsi channel
                    sendMarkerMessage(c1,h1);
                }

                // Set received flag to false
                hangar.setMarkerReceived(new Boolean[]{false,hangar.getMarkerReceived()[1]},hangar.getSender());

            }
            else if (hangar.getMarkerReceived()[1]){

                // Set received flag to false
                hangar.setMarkerReceived(new Boolean[] {hangar.getMarkerReceived()[0],false},hangar.getSender());

            }
            else if (hangar.isSendState()){
                // if the operation is sending state
                System.out.println("Initiator: " +hangar.getInitiator() + "; Identifier: " + id);
                String initiator=hangar.getInitiator();
                try {
                    if (initiator.equals(h1)){
                        c1.sendState(hangar.getRecord());
                    }else if (initiator.equals(h2)){
                        c2.sendState(hangar.getRecord());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else if(channal==0 && hangar.getQueuesize(h1)>0){

                Transaction transaction=hangar.getC1().dequeue();
                List<String> airplanes=transaction.getAirplanes();
                mainWindow.getHistoryListModel().addElement("Transfer: " + id + " -> " + h1 + " (" +airplanes.size()+ ")");
                try {
                    c1.sendAirplanes(airplanes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (channal==1 && hangar.getQueuesize(h2)>0){
                Transaction transaction=hangar.getC2().dequeue();
                List<String> airplanes=transaction.getAirplanes();
                try {
                    c2.sendAirplanes(airplanes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            threadSleep(3000);
        }


    }
    private void threadSleep(long millis) {
        try {
//            System.out.println("D" +identifier+" goes to sleep for " + millis + "ms...");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void sendMarkerMessage(Send client, String h) {

//        logger.info("D" +identifier+ " sends marker message to " + h + "...");
        mainWindow.getHistoryListModel().addElement("Marker: " + id + " -> " + h);
        try {
            client.sendMarkerMessage(hangar.getInitiator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        hangar.setMarkerSent(new Boolean[] {true, hangar.isMarkerSent()[1]});

    }
}
