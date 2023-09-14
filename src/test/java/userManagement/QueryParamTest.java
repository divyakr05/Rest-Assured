package userManagement;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QueryParamTest {

    @Test
    public void queryParamTest(){
        Response response; //Response interface to save the response
        baseURI = "https://reqres.in/api";

        response = given()
                .queryParam("page","2")
                .when()
                .get("/users");
        Assert.assertEquals(response.statusCode(),200);
        assertThat(response.statusCode(),equalTo(200));//This will check all the email values


    }

    @Test
    public void multiQueryParamTest(){
        Response response; //Response interface to save the response
        baseURI = "https://reqres.in/api";

        response = given()
                .queryParam("page","2")
                .queryParam("per_page","3")
                .when()
                .get("/users");


        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(),200);
        assertThat(response.statusCode(),equalTo(200));//This will check all the email values

    }
}

