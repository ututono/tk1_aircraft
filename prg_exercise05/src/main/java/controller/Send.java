package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Send {
    public static String MARKERCODE ="marker";
    public static String STATECODE="state";

    private static String hostURL = "127.0.0.1";
    private InetAddress serverAddress;
    private DatagramSocket socket;
    private TransferManager transferManager;

    // Destination port
    private int port;

    public Send(DatagramSocket socket, TransferManager transferManager) {
        this.socket = socket;
        this.transferManager = transferManager;
    }

    public Send(int port) throws IOException {
        this.port = port;
        this.serverAddress=InetAddress.getByName(hostURL);
        System.out.println("HOST address"+serverAddress.getHostName());
        socket=new DatagramSocket();
    }

    public void sendAirplanes(List<String> airplanes) throws IOException {
        byte[] data = serialize(airplanes);
        DatagramPacket packet=new DatagramPacket(data,data.length,serverAddress,port);

        this.socket.send(packet);
    }

    public void sendMarkerMessage(Object o) throws IOException {
        List<String> payload=new ArrayList<>();
        payload.add(MARKERCODE);
        byte[] data = serialize(payload);
        DatagramPacket packet=new DatagramPacket(data,data.length,serverAddress,port);

        this.socket.send(packet);
    }

    public void sendState(String state) throws IOException {
        List<String> payload=new ArrayList<>();
        payload.add(MARKERCODE);
        payload.add(state);
        byte[] data = serialize(payload);
        DatagramPacket packet=new DatagramPacket(data,data.length,serverAddress,port);

        this.socket.send(packet);
    }

    private byte[] serialize(Object o) {

        try(ByteArrayOutputStream bos=new ByteArrayOutputStream()){
            try(ObjectOutputStream oos=new ObjectOutputStream(bos)) {
                // Serialization
                oos.writeObject(o);
                // Convert to byte stream and pack it to a udp packet.
                byte[] data =bos.toByteArray();
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }




//    @Override
//    public void run() {
//        try(OutputStream outputStream=socket.getOutputStream()){
//            handle(outputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handle(OutputStream outputStream) throws IOException {
//        ObjectOutputStream objout=new ObjectOutputStream(outputStream);
//        Random random=new Random();
//        random.setSeed(10);
//        int volume=transferManager.getVolume();
//        Integer increment=random.nextInt(0)+volume;
//        Transaction transaction=transferManager.transferAndGet(socket.getRemoteSocketAddress().toString(),
//                socket.getLocalSocketAddress().toString(),increment);
//
//        objout.writeObject(transaction);
//        System.out.println(transaction.toString());
//
//
//    }
}
