package userManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class PostUsers {

    @Test
    public void postWithStringTest(){
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("/users");

        System.out.println(response.body().asString());
        Assert.assertEquals(response.statusCode(), StatusCode.CREATED.code);
        //Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.jsonPath().get("name"),"morpheus");

    }

    @Test
    public void postWithJsonDataTest() throws IOException {
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .header("Content-Type","application/json")
                .body(IOUtils.toString(fileInputStreamMethod("postReqBody.json")))
                .when()
                .post("/users");

        System.out.println(response.body().asString());
        Assert.assertEquals(response.statusCode(), StatusCode.CREATED.code);
        //Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.jsonPath().get("username"),"morpheus");

    }

    private static FileInputStream fileInputStreamMethod(String reqBodyFileName){
        FileInputStream fileInputStream;
        //converting file into file input stream
        try{
            fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/resources/testData/"+ reqBodyFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  fileInputStream;
    }
}
