package basics._04.post;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostRequestTest {



    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
    }

    @Test
    public void postTest1(){

        Response response = given().log().all().when().post("v1.0");

        response.prettyPrint();

        Integer statusCode = response.statusCode();
        System.out.println(statusCode);

        Integer expectedStatusCode = 405;
        Assert.assertEquals(statusCode, expectedStatusCode);
    }

    @Test
    public void postTest2(){

        Response response = when().post("v1.0/users");

        response.prettyPrint();

        Integer statusCode = response.statusCode();
        System.out.println(statusCode);

        Integer expectedStatusCode = 400;
        Assert.assertEquals(statusCode, expectedStatusCode);

    }
    @Test
    public void postTest3() {

        String createUserBody = "{\n" +
                "  \"name\": \"toni\",\n" +
                "  \"username\": \"Antonio5\",\n" +
                "  \"password\": \"toni\",\n" +
                "  \"role\": \"QA\",\n" +
                "  \"email\": \"twiindan@gmail.com\"\n" +
                "}";
        given().body(createUserBody).when().post("v1.0/users").then().assertThat().statusCode(200);


    }

    @Test
    public void postTest4() {

        String createUserBody = "{\n" +
                "  \"name\": \"toni\",\n" +
                "  \"username\": \"Antonio8\",\n" +
                "  \"password\": \"toni\",\n" +
                "  \"role\": \"QA\",\n" +
                "  \"email\": \"twiindan@gmail.com\"\n" +
                "}";

        Response response = given().body(createUserBody).when().post("v1.0/users");
        Integer statusCode = response.statusCode();
        Integer expectedStatusCode = 200;
        Assert.assertEquals(statusCode, expectedStatusCode);

        String body = response.getBody().asString();
        Assert.assertEquals(body, "user created");
    }
}
