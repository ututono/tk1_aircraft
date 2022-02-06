import controller.Receive;
import controller.Send;
import controller.TransferManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Hanger {
    private TransferManager transferManager;
    private final Integer VOLUME=10;
    private final String serverIP="127.0.0.1";
    private Integer port;
    private ServerSocket serverSocket;
    private Socket socket;
    private Logger logger;


    public Hanger(Integer port) {
        transferManager=new TransferManager(VOLUME);
        this.port=port;

        try {
            serverSocket= new ServerSocket(this.port); // listen on the port

            while (true){
                Socket socket=serverSocket.accept();
                Thread send=new Thread(new Send(socket,transferManager));
                Thread receive=new Thread(new Receive(socket,transferManager));
                send.start();
                receive.start();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }






}
