import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Random;

public class TimeServer {
	private static int PORT = 27780;
	private ServerSocket serverSocket;
	private Socket socket;
	private static Integer OFFSET=1100; // Artificial offset for the server

	public TimeServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server started on port: " + PORT);

			// The Server will wait here until the request coming

			// TODO Using ThreadPool
			while (true){
				socket=serverSocket.accept();
				Thread t=new Thread(new NTPRequestHandler(socket));
				t.start();
			}




		} catch (IOException e) {
			e.printStackTrace();
			try {
				serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
		new TimeServer();
	}

	private class NTPRequestHandler implements Runnable {
		private Socket client;
		public volatile boolean running = true; // Running Status of thread. If the thread is interrupted, the values set to false

		public NTPRequestHandler(Socket client) {
			this.client = client;
		}

		@Override
		public void run() {
			System.out.println("Connected from "+ client.getRemoteSocketAddress());
			try(InputStream input=client.getInputStream()) {
				try(OutputStream output=client.getOutputStream()) {
					handle(input,output);
				}
			} catch (Exception e) {
				try {
					client.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				e.printStackTrace();
			}
		}

		private void handle(InputStream input, OutputStream output) throws IOException, ClassNotFoundException {

			ObjectInputStream objectInputStream=new ObjectInputStream(input);
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(client.getOutputStream());
			NTPRequest ntpRequest= (NTPRequest) objectInputStream.readObject();
			ntpRequest.setT3(Instant.now().toEpochMilli()+OFFSET);
			threadSleep(500); // Simulate processing time
			ntpRequest.setT2(Instant.now().toEpochMilli()+OFFSET);

			objectOutputStream.writeObject(ntpRequest);

		}

		private void sendNTPAnswer(NTPRequest request) {
			///
		}

	}

}
