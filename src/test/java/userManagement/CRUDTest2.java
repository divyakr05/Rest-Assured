package userManagement;

import core.StatusCode;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class CRUDTest2 {


    @Test(description = "")
    public void getTest(){
        Response response;

        response = given()
                .when()
                .get("https://www.check24.de/kreditkarte/");

        Map<String, String> cookies = response.getCookies();
        for (Map.Entry<String,String> entry:cookies.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        System.out.println("*********************");
        Headers headers = response.getHeaders();
        for (Header h:headers) {
            System.out.println(h.getName()+" : "+h.getValue());
        }
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.getCookies().get("ppset"),"kreditkarte");
        System.out.println("Get operation successful!!");
    }

    @Test(description = "")
    public void postTest(){
        Response response;
        baseURI = "https://reqres.in/api";
        JSONObject reqBody = new JSONObject();
        reqBody.put("name", "morpheus");
        reqBody.put("job", "leader");

        response = given()
                .header("Content-Type","application/json")
                .body( reqBody.toString())
                .accept(ContentType.JSON)
                .when()
                .post("/users?page=2");

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.statusCode(), StatusCode.CREATED.code,StatusCode.CREATED.msg);
        Assert.assertEquals(response.jsonPath().get("name"),"morpheus");
        Assert.assertEquals(response.jsonPath().get("job"),"leader");
        System.out.println(response.body().asString());
        System.out.println("Post operation successful!!");
    }
}
