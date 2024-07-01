package httprequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAPITest {

    @Test
    public void testWeatherInLondon() {
        String url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02";
        
        Response response = RestAssured.get(url);

        // Verify that the response code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got " + response.getStatusCode());

        // You can add more assertions here to verify the response body
    }
}
