package server;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final AccountManager accountManager;

    public ClientHandler(Socket socket, AccountManager accountManager) {
        this.clientSocket = socket;
        this.accountManager = accountManager;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String line = in.readLine();
            if (line != null) {
                if (line.startsWith("GET")) {
                    handleGetRequest(out);
                } else if (line.startsWith("POST")) {
                    handlePostRequest(in, out, line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleGetRequest(PrintWriter out) {
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" +
                          "<html><body><h1>Welcome to the Bank</h1></body></html>";
        out.println(response);
        out.flush();
    }

    private void handlePostRequest(BufferedReader in, PrintWriter out, String initialLine) throws IOException {
        int contentLength = getContentLength(in, initialLine);
        char[] buffer = new char[contentLength];
        in.read(buffer, 0, contentLength);
        String requestBody = new String(buffer);
        Map<String, String> params = parsePostData(requestBody);

        try {
            int fromAccount = Integer.parseInt(params.get("account"));
            int toAccount = Integer.parseInt(params.get("toAccount"));
            int amount = Integer.parseInt(params.get("value"));

            accountManager.transferFunds(fromAccount, toAccount, amount);
            out.println("HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\nTransfer successful.");
        } catch (NumberFormatException | NullPointerException e) {
            out.println("HTTP/1.1 400 Bad Request\r\nContent-Length: 0\r\n\r\nInvalid request format.");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("HTTP/1.1 500 Internal Server Error\r\nContent-Length: 0\r\n\r\n" + e.getMessage());
        }
    }

    private int getContentLength(BufferedReader in, String initialLine) throws IOException {
        int contentLength = 0;
        while (!initialLine.isEmpty()) {
            if (initialLine.startsWith("Content-Length:")) {
                contentLength = Integer.parseInt(initialLine.split(" ")[1].trim());
            }
            initialLine = in.readLine();
        }
        return contentLength;
    }

    private Map<String, String> parsePostData(String requestData) {
        Map<String, String> params = new HashMap<>();
        String[] pairs = requestData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                params.put(key, value);
            }
        }
        return params;
    }
}

