package userManagement;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class ValidateTestDataFromJson {

    @Test
    public void validateTestData() throws IOException, ParseException {
        Response response;
        String userName = JsonReader.getTestData("username");
        String password = JsonReader.getTestData("password");
        System.out.println("Test data from Json :"+userName+","+password);
        response = given()
                .auth()
                .basic(userName,password)
                .when()
                .get("https://postman-echo.com/basic-auth");

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
