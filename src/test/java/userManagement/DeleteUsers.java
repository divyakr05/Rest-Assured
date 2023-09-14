package userManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DeleteUsers {

    @Test
    public void deleteUsers(){
        Response response;

        response = given()
        .delete("https://reqres.in/api/users/2");

        Assert.assertEquals(response.statusCode(),204);
        //using enum class StatusCode
        Assert.assertEquals(response.statusCode(), StatusCode.NO_CONTENT.code);
        System.out.println(response.body().asString());
    }
}
