package client;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class SimpleWebClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread.sleep(1000); // 1 second

            Map<String, String> params = new HashMap<>();
            params.put("account", "123");
            params.put("value", "1000");
            params.put("toAccount", "456");
            params.put("toValue", "500");

            String httpRequest = HttpRequestBuilder.buildPostRequest("localhost", 5000, "/submit", params);

            out.println(httpRequest);
            out.flush();

            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println("Server replied: " + responseLine);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

