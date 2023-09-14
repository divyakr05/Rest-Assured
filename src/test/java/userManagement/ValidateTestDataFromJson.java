package userManagement;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ValidateTestDataFromJson {

    @Test
    public void validateTestData() throws IOException, ParseException {
        Response response;
        String userName = JsonReader.getTestData("testData.json","username");
        String password = JsonReader.getTestData("testData.json","password");
        System.out.println("Test data from Json :"+userName+","+password);
        response = given()
                .auth()
                .basic(userName,password)
                .when()
                .get("https://postman-echo.com/basic-auth");

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void validateJsonArrayTestData() throws IOException, ParseException {
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        list1 = (List<Object>) JsonReader.getJsonArrayData("arrayTestData.json","languages");
        list2 = (List<Object>) JsonReader.getJsonArrayData("arrayTestData.json","contact");
        System.out.println(list2);
    }
}
