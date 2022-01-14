import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

public class TimeClient {
	private static String hostUrl = "127.0.0.1";
	private static int PORT = 27780;
	private Double minD;
	private NTPRequest minNTPrequest;
	private Socket socket;

	public TimeClient() {

		try {

			for (int i = 0; i < 10; i++) {
				socket = new Socket(InetAddress.getByName(hostUrl), PORT);
				try(InputStream input=socket.getInputStream()){
					try(OutputStream output=socket.getOutputStream()){
						handle(input,output);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}

			}

			
			socket.close();
			threadSleep(350);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handle(InputStream input, OutputStream output) throws IOException, ClassNotFoundException {

		// Initialization
		NTPRequest ntpRequest=new NTPRequest();
		Random randomGen=new Random();
		ObjectOutputStream objOutput=new ObjectOutputStream(output);
		ObjectInputStream objectInputStream=new ObjectInputStream(input);
		long t4=Instant.now().toEpochMilli();
		ntpRequest.setT4(t4);
		threadSleep(randomGen.nextInt(50)+50); // Randomly generate a integer in range of [50,100]
		objOutput.writeObject(ntpRequest);

		threadSleep(randomGen.nextInt(50)+50);
		NTPRequest ntpResponse= (NTPRequest) objectInputStream.readObject();
		ntpResponse.setT1(Instant.now().toEpochMilli());

		ntpResponse.calculateOandD();
		System.out.println(ntpResponse);
		System.out.println("d is :"+ntpResponse.d+"\t o is: "+ntpResponse.o);


	}

	private void sendNTPRequest(NTPRequest request) throws IOException {
		try (OutputStream output=socket.getOutputStream()){
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(output);
			objectOutputStream.writeObject(request);

		}

	}

	private void threadSleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TimeClient();
	}

}
