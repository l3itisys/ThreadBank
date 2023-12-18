package server; 

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    private final AccountManager accountManager;
    private final ExecutorService threadPool;

    public WebServer() {
        this.accountManager = new AccountManager();
        this.threadPool = Executors.newFixedThreadPool(1000); // Example: Thread pool of 10 threads
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                System.out.println("Waiting for a client to connect...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected...");

                ClientHandler clientHandler = new ClientHandler(clientSocket, accountManager);
                threadPool.submit(clientHandler);
            }
        } finally {
            threadPool.shutdown();
        }
    }

    public static void main(String[] args) {
        WebServer server = new WebServer();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


