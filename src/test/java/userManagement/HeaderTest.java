package userManagement;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HeaderTest {

    @Test
    public void headerTest(){
       baseURI = "https://reqres.in/api";

        given()
               .header("Content-Type","application/json")
               .when()
               .get("/users?page=2")
               .then()
               .statusCode(200)
               .log().all();
    }

    @Test
    public void multiHeaderTest(){
        baseURI = "https://reqres.in/api";
        String token = "";

        given()
                .header("Authorisation","Bearer <your token here>")
                .header("Content-Type","application/json")
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void multiHeaderAsMapTest(){
        baseURI = "https://reqres.in/api";
        String token = "";

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorisation","Bearer <your token here>");
        headers.put("Content-Type","application/json");

        given()
                .headers(headers)
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void fetchHeadersFromServerTest(){
        Response response = null;
        baseURI = "https://reqres.in/api";

        response = given()
                .when()
                .get("/users?page=2");

        Headers headers = response.getHeaders();
        for (Header h:headers) {
            if(h.getName().contains("Server")){
                System.out.println(h.getName()+" : "+h.getValue());
                Assert.assertEquals(h.getValue(),"cloudflare");
            }


        }

    }
}
