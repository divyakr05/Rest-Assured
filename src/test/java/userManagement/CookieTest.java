package userManagement;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CookieTest {

    @Test
    public void cookieTest(){
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .cookie("test1","testing1")
                .cookie("test2","testing2")
                .when()
                .get("/users?page=2");

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void fetchCookieTest(){
        Response response;

        response = given()
                .when()
                .get("https://www.check24.de/en/kreditkarte/");

        Map<String,String> cookies = response.getCookies();
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.cookies().size(),3);
        Assert.assertTrue(response.cookies().containsKey("devicetype"));
        Assert.assertTrue(response.cookies().containsValue("desktop"));
    }
}
