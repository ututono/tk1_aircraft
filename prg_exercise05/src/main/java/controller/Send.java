package controller;

import model.Transaction;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Send implements Runnable{
    private Socket socket;
    private TransferManager transferManager;

    public Send(Socket socket, TransferManager transferManager) {
        this.socket = socket;
        this.transferManager = transferManager;
    }


    @Override
    public void run() {
        try(OutputStream outputStream=socket.getOutputStream()){
            handle(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(OutputStream outputStream) throws IOException {
        ObjectOutputStream objout=new ObjectOutputStream(outputStream);
        Random random=new Random();
        random.setSeed(10);
        Integer increment=random.nextInt(0)+10;
        Transaction transaction=transferManager.addAndGet(socket.getRemoteSocketAddress().toString(),
                socket.getLocalSocketAddress().toString(),increment);

        objout.writeObject(transaction);



    }
}
