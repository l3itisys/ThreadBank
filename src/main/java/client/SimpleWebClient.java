import java.io.*; 
import java.net.*; 

// Client Class 
public class SimpleWebClient {
  
  public static void main(String[] args)
{
    // Establish a connnection with the server 
    try (Socket socket = new Socket("localhost", 5000)){

      // Write to server 
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 

      //  Reading from server 
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

      // Simulate a delay 
      Thread.sleep(1000); // 1 second 

      // Prepare POST request data 
      String postData = "account=123&value=1000&toAccount=456&toValue=500";
            String httpRequest =  httpRequestBuilder.buildPostRequest("localhost", 5000, "/submit", postData); 

            // Send HTTP request to the server
            out.println(httpRequest);
            out.flush();

      // Read and print the server response 
      String responseLine; 
      while ((responseLine = in.readLine()) != null ) {
        
        // Display server response 
        System.out.println("Server replied" + responseLine); 
      }
    } catch (IOException | InterruptedException e){
      e.printStackTrace(); 
    }
  }
} 


