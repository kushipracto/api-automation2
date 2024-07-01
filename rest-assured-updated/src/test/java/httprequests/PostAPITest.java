package httprequests;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
public class PostAPITest {
	@Test
    public void postCityPopulation() {
		 Response response = null ;
        RestAssured.baseURI = "https://countriesnow.space";
        String requestBody = "{ \"city\": \"Lagos\" }";
        Response post =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                .when()
                        .post("/api/v0.1/countries/population/cities")
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
		
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("get-api-schema.json"));
    }

	}

