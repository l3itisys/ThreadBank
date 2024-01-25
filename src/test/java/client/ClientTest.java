package client;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientTest {

    private SimpleWebClient client;

    @Before
    public void setUp() {
        client = new SimpleWebClient();
    }

    @Test
    public void testGetRequest() {
        // Testing the GET request builder
        String getRequest = HttpRequestBuilder.buildGetRequest("localhost", 5000, "/test");
        assertNotNull("GET request should not be null", getRequest);
        assertTrue("GET request should start with 'GET'", getRequest.startsWith("GET"));
    }

    @Test
    public void testPostRequest() {
        // Testing the POST request builder
        Map<String, String> params = new HashMap<>();
        params.put("testKey", "testValue");
        String postRequest = HttpRequestBuilder.buildPostRequest("localhost", 5000, "/test", params);
        assertNotNull("POST request should not be null", postRequest);
        assertTrue("POST request should start with 'POST'", postRequest.startsWith("POST"));
        assertTrue("POST request should contain encoded parameters", postRequest.contains("testKey=testValue"));
    }
}

