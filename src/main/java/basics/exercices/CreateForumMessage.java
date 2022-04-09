package basics.exercices;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateForumMessage {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
    }

    @Test
    public void createForumMessageTest() {

        String createForumMessageBody = " {\"theme\": \"Testing\",\n" +
                "     \"subject\": \"First message\",\n" +
                "     \"message\": \"First message in forum\"}";

        Response response = given().body(createForumMessageBody).when().post("v1.0/forum");
        Integer statusCode = response.statusCode();
        Integer expectedStatusCode = 200;
        Assert.assertEquals(statusCode, expectedStatusCode);

        String body = response.getBody().asString();
        Assert.assertEquals(body, "message created");
    }
}
