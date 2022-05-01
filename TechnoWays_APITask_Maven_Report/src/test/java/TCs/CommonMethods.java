package TCs;


import static io.restassured.RestAssured.given;

import java.util.Random;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class CommonMethods {
	static utils.ReadingPropertiesFile data;
	static utils.LogInFile Logger = new utils.LogInFile(CommonMethods.class);
	int randomNum;
	
	public static utils.ReadingPropertiesFile Call_ReadPropFile() {

		try {
			data = new utils.ReadingPropertiesFile(".\\Input\\Configuration.properities");

		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
		return data;
	}
		
	public int GenRandomeNum() {
		try {
			
		Random rand = new Random(); // instance of random class
		int min = 1;
		int upperbound = 10;

		randomNum = rand.nextInt((upperbound - min) + 1) + min;
		// For Tester
		System.out.println("The randome user id is: " + randomNum + "\n------------------");
	} catch (Exception e) {
		e.printStackTrace();
		Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
	}
		return randomNum;

	}
	
	public Response doGetRequest(String endpoint) {
		try {
			
		RestAssured.defaultParser = Parser.JSON;

			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
		return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).when().get(endpoint).then()
				.contentType(ContentType.JSON).extract().response();
	}

}
