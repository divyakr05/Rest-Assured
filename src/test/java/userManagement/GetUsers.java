package userManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetUsers {


    @Test
    public void getUserData(){
        given(). //Params,Authorisation,Headers and Body
                when().get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void validateGetResponseBodyEqualTo(){
        baseURI = "https://reqres.in/api";
         given().
                when().
                get("/users/2").
                then().
                statusCode(200).
                body(not(isEmptyString())).
                body("data.first_name",equalTo("Janet"));

    }

    @Test
    public void validateGetResponseBodyHasItems(){
        Response response; //Response interface to save the response
        baseURI = "https://jsonplaceholder.typicode.com";

        response = given().
                when().
                get("/posts");
        //use Hamcrest to check that response body contains specific items
        assertThat(response.jsonPath().getList("title"),hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit","qui est esse"));

    }

    @Test
    public void validateGetResponseBodyHasSize(){
        Response response; //Response interface to save the response
        baseURI = "https://jsonplaceholder.typicode.com";

        response = given().
                when().
                get("/comments");
        //use Hamcrest to check that response body size is 500
        assertThat(response.jsonPath().getList(""),hasSize(500));

    }

    @Test
    public void validateGetResponseBodyContains(){
        Response response; //Response interface to save the response
        baseURI = "https://jsonplaceholder.typicode.com";

        response = given().
                when().
                get("/comments?postId=1");

        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz","Jayne_Kuhic@sydney.com","Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
        //use Hamcrest to check that response body contains specific items in a specific order
        assertThat(response.jsonPath().getList("email"),contains(expectedEmails.toArray(new String[0])));//This will check all the email values
        assertThat(response.jsonPath().getList("email"),hasItems("Nikita@garfield.biz"));

    }

    @Test
    public void validateGetResponseBody() {
        Response response = given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .extract()
                .response();

        System.out.println(response.getBody().asString());

        // Validate that the response body is not empty
        assertThat(response.getBody().asString(), not(isEmptyString()));
        // Validate that the response contains a specific value
        assertThat(response.getBody().asString(), containsString("janet.weaver@reqres.in"));
        // Validate that the response has a specific JSON attribute
        String name = response.jsonPath().get("data.first_name");
        assertThat(response.jsonPath().get("data.first_name"), equalTo("Janet"));
        // Validate that the response has a specific XML element
        //assertThat(response.getBody().xmlPath().get("element"), equalTo("expectedValue"));
        //using enum class status code
        Assert.assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
    }
}
