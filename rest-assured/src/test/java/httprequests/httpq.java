package httprequests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class httpq {
	public class GetAPITest {

	    @Test
	    public void testWeatherInLondon() {
	        String url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02";
	        
	        Response response = RestAssured.get(url);

	        // Verify that the response code is 200
	        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got " + response.getStatusCode());

	    }

	@Test
    public void postCityPopulation() {
        RestAssured.baseURI = "https://countriesnow.space";
        String requestBody = "{ \"city\": \"Lagos\" }";
        Response post =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                .when()
                        .post("https://countriesnow.space/api/v0.1/countries/population/cities")
                .then()
                        .statusCode(301) // Expecting 301 (Moved Permanently)
                        .extract().response();
        String redirectUrl = post.getHeader("Location");
        Response get =
                given()
                .when()
                	.get(redirectUrl)
                .then()
                	.statusCode(200)
                	.extract().response();
        get.then()
                .body("data.city", equalTo("Lagos"))
                .body("data.country", equalTo("Nigeria"));
        int finalStatusCode = get.getStatusCode();
        System.out.println("Final status code: " + finalStatusCode);
    }
	}
}

