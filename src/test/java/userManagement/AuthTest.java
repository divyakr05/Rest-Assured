package userManagement;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class AuthTest {

    @Test
    public void basicAuthTest(){
        Response response;
        response = given()
                .auth()
                .basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.jsonPath().get("authenticated"));
        System.out.println(response.body().asString());
    }

    @Test
    public void digestAuthTest(){
        Response response;
        response = given()
                .auth()
                .digest("postman","password")
                .when()
                .get("https://postman-echo.com/digest-auth");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.jsonPath().get("authenticated"));
        System.out.println(response.body().asString());
    }
}
