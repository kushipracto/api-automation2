package httprequests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class GetAPITest {
	@Test
	void checkSecond()
	{
		
		given()
		
		.when()
			.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02")
			
		.then()
			.statusCode(200)
			.body("base", equalTo("stations"))
			.body("name", equalTo("London"));
			
	
	}
}