package controller;

import model.Transaction;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receive implements Runnable{
    private Socket socket;
    private  TransferManager transferManager;
    public Receive(Socket socket, TransferManager transferManager) {
        this.socket = socket;
        this.transferManager=transferManager;
    }

    @Override
    public void run() {
        try(InputStream input=socket.getInputStream()){
            handle(input);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handle(InputStream input) throws IOException, ClassNotFoundException {
        ObjectInputStream objInput=new ObjectInputStream(input);
        Transaction transaction= (Transaction) objInput.readObject();


    }
}
