package userManagement;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PathParamTest {

    @Test
    public void pathParamTest(){
        String raceSeasonValue = "2017";
        Response response; //Response interface to save the response
        baseURI = "http://ergast.com/api";

        response = given()
                .pathParams("raceSeason",raceSeasonValue)
                .when()
                .get("/f1/{raceSeason}/circuits.json");
        Assert.assertEquals(response.statusCode(),200);
        System.out.println(response.body().asString());
        assertThat(response.jsonPath().get("MRData.total"),equalTo("20"));
    }
}
