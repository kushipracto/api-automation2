package httprequests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class PostAPITest {
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
