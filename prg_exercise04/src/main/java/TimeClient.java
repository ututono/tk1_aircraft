import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
				System.out.println("\nClient "+socket.getLocalSocketAddress()+" is going to connect server");
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

		// Simulate transport delay
		Integer delay=randomGen.nextInt(50)+50;
		System.out.println("Artificial delay: "+delay);

		threadSleep(delay); // Randomly generate an integer in range of [50,100]
		objOutput.writeObject(ntpRequest);

		NTPRequest ntpResponse= (NTPRequest) objectInputStream.readObject();
		threadSleep(delay);
		ntpResponse.setT1(Instant.now().toEpochMilli());

		// Visual inspection
		LocalDateTime t4_sendReq =
				LocalDateTime.ofInstant(Instant.ofEpochMilli(ntpResponse.getT4()), ZoneId.systemDefault());
		LocalDateTime t3_RcReq =
				LocalDateTime.ofInstant(Instant.ofEpochMilli(ntpResponse.getT3()), ZoneId.systemDefault());
		LocalDateTime t2_sendReponse =
				LocalDateTime.ofInstant(Instant.ofEpochMilli(ntpResponse.getT2()), ZoneId.systemDefault());
		LocalDateTime t1_RecReponse =
				LocalDateTime.ofInstant(Instant.ofEpochMilli(ntpResponse.getT1()), ZoneId.systemDefault());
		System.out.println("T4_sendReq:"+t4_sendReq);
		System.out.println("t3_RcReq:"+t3_RcReq);
		System.out.println("t2_sendReponse:"+t2_sendReponse);
		System.out.println("t1_RecReponse:"+t1_RecReponse);

		ntpResponse.calculateOandD();
		System.out.println("d is :"+ntpResponse.d+"\t o is: "+ntpResponse.o);


	}

	private void sendNTPRequest(NTPRequest request) throws IOException {
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
