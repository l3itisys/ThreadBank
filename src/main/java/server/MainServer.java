
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.net.URLDecoder; 

// ****** Pseudocode for Multithreaded Server ******

// Create the web server class to recieve multiple client requests 
// ---- Class WebServer ----

// ----- Method startServer ----- 
// Create a ServerSocket listening on a specific port (e.g., 5000)


// Handling multiple clients by refactoring the code with ** Thread pool ** 

// **** Thread pool **** // 
// Initialize an ExecutorService for managing the pool threads 
// (e.g., Using Executors.newFixedThreadPool(NUMBER_OF_THREADS))

// while true (Infinite loop to continuously accept client connections)
// Accept a client connection to get a Socket object (clientSocket)

// Create a Runnable (clientTask) that encapsulate the client request handling logic 
//     Obtain input and output streams from clientSocket 
//     Read the client's request (HTTP GET or POST)
//     Based in request type, call handleGetRequest or HandlePostRequest methods 
//     Close the input and output streams
//     Close the ClientSocket 

// Submit the clientTask to the ExecutorService for execution 

// end while 
// end Methode 

// Class Main 
//    Main Method 
//        Create an instance of WebServer 
//        Call startServer method on the instance 
//    End Method 
// End Class 
