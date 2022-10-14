import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class SessionFilterHandle {

	//Cookie Authentication and defining path parameter in Rest Assured
	@Test
	public void sessionFilterHandling(String isbn,String aisle){
		RestAssured.baseURI= "https://rahulshettyacademy.com";

		SessionFilter session = new SessionFilter();

		given().header("Content-Type","application/json").
		body(payload.AddPlace()).log().all().
		filter(session).
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response().asString();

		given().pathParam("key", "10101").log().all().
		header("Content-Type","application/json").
		body(payload.AddPlace()).log().all().
		filter(session).
		when().post("/Library/{key}/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response().asString();

	}

}
