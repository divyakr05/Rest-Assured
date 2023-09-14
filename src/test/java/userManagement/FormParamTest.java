package userManagement;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FormParamTest {

    @Test
    public void formParamTest(){
        Response response;
        baseURI = "";

        response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username","user")
                .formParam("password","password")
                .when()
                .post("https://example.com/login")
                .then()
                .statusCode(201)
                .extract().response();

        response.then().body("username",equalTo("user"));
        //Assert.assertEquals(response.body().jsonPath().get("username"),equalTo("username"));
    }
}
