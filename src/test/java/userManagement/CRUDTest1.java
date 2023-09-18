package userManagement;

import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.*;


public class CRUDTest1 {

    @Test(description = "Get Operation")
    public void getOperation(){
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .when()
                .get("/users");


        Assert.assertEquals(response.statusCode(),200);
        System.out.println("Get operation is successful!!");
    }

    @Test(description = "Post Operation")
    public void postOperation() throws IOException, ParseException {
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .header("Content-Type","application/json")
                .body(getJsonData("crudTestData.json","post"))
                .when()
                .post("/users?page=2");

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.jsonPath().get("name"),"John");
        System.out.println(response.body().asString());
        System.out.println("Post operation is successful!!");
    }

    @Test(description = "Put Operation")
    public void putOperation() throws IOException, ParseException {
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .header("Content-Type","application/json")
                .body(getJsonData("crudTestData.json","put"))
                .when()
                .put("/users/2");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().get("name"),"Peter");
        System.out.println(response.body().asString());
        System.out.println("Put operation is successful!!");
    }

    @Test(description = "Patch Operation")
    public void patchOperation() throws IOException, ParseException {
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .header("Content-Type","application/json")
                .body(getJsonData("crudTestData.json","patch"))
                .when()
                .patch("/users/2");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().get("job"),"Teacher");
        System.out.println(response.body().asString());
        System.out.println("Patch operation is successful!!");
    }

    @Test(description = "Delete Operation")
    public void deleteOperation() {
        Response response;
        baseURI = "https://reqres.in/api";

        response = given()
                .when()
                .delete("/users/2");

        Assert.assertEquals(response.statusCode(),204);
        System.out.println("Delete operation is successful!!");
    }

    public JSONObject getJsonData(String fileName,String key) throws IOException, ParseException {
        File file = new File(System.getProperty("user.dir")+"/resources/testData/"+fileName);
        String json = FileUtils.readFileToString(file,"UTF-8");
        Object obj = new JSONParser().parse(json);
        JSONObject jsonObject = (JSONObject) obj;
        return (JSONObject) jsonObject.get(key);
    }
}
