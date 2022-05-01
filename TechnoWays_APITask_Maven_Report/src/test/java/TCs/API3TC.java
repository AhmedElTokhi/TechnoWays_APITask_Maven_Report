package TCs;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import utils.ReadingPropertiesFile;
import org.json.simple.JSONObject;

import java.util.List;

public class API3TC {

	ReadingPropertiesFile data = CommonMethods.Call_ReadPropFile();
	utils.LogInFile Logger = new utils.LogInFile(API3TC.class);

	int randomUserID = new CommonMethods().GenRandomeNum();

	Response response;
	int temp = 0;

	@Test(priority = 01)
	public void TC01_printUserEmail() {
		try {

			Logger.WriteLog(
					" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":TC01_printUserEmail");

			response = new CommonMethods().doGetRequest(data.GetPrintUserEmailURL() + randomUserID);
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":response node: "
					+ data.GetPrintUserEmailURL() + randomUserID);

			String Email = response.jsonPath().getString(data.GetprintValue());
			System.out.println("The User Email is: " + Email + "\n------------------");
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "The User Email is: "
					+ Email + "\n------------------");

			response = null;

		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
	}

	@Test(priority = 02)
	void TC02_GetUserPosts() {
		try {

			Logger.WriteLog(
					" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":TC02_GetUserPosts");

			response = new CommonMethods().doGetRequest(data.GetUserPostsURL() + randomUserID);
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":response node: "
					+ data.GetUserPostsURL() + randomUserID);

			List<Integer> jsonResponse = response.jsonPath().getList(data.GetcheckPostValue());
			System.out.println("The user posts list size: " + jsonResponse.size());
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":The user posts list size: " + jsonResponse.size());

			for (int test : jsonResponse) {
				assertTrue(
						test >= Integer.parseInt(data.GetMinValue()) && test <= Integer.parseInt(data.GetMaxValue()));
				// boolean check= Test>=1 && Test=<100;

				if (test > temp)
					temp = test;

				// for Tester
				System.out.println("list value is: " + test);
				Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
						+ ":list value is: " + test);
			}

			System.out.println("All user posts ids in 1 - 100");
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":All user posts ids in 1 - 100");
			response = null;

		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Test(priority = 03)
	void TC03_UserPostNew() {
		try {

			Logger.WriteLog(
					" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + ":TC02_GetUserPosts");
			JSONObject request = new JSONObject();

			request.put("userId", randomUserID);
			request.put("Id", temp + 1);
			request.put("title", "New Post Title");
			request.put("body", "New Post body for API Task");
			System.out.println("The new post parameter is: " + request.toJSONString());
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":The new post parameter is: " + request.toJSONString());

			given().body(request.toJSONString()).when().post(data.GetAddPostURL()).then().statusCode(201);
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ":The new post done successfully");

		} catch (Exception e) {
			e.printStackTrace();
			Logger.WriteLog(" Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n", e);
		}
	}

}
