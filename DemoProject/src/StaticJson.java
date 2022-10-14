import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	@Test
	public void addBook() throws IOException
	{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		//RestAssured.baseURI="http://216.10.245.166";
		String resp=given().
				header("Content-Type","application/json").
				body(GenerateStringFromResource(System.getProperty("user.dir")+ "//resources//Addbookdetails.json")).when().
				post("/Library/Addbook.php").
				then().assertThat().statusCode(200).
				extract().response().asString();;

				JsonPath js= ReUsableMethods.rawToJson(resp);
				String id=js.get("ID");
				System.out.println(id);
				//deleteBOok
	}
	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}

