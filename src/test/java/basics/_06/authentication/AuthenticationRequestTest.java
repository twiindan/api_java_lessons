package basics._06.authentication;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import basics.models.CreateUserBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AuthenticationRequestTest {

    String username = "";

    @BeforeClass
  public void setUp() {

        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        String randomId = Integer.toString(new Random().nextInt(1000000));
        this.username = "AutomationAPI" + randomId;
        String createUserBody = CreateUserBody.GetCreateUserBody(this.username);
        given().body(createUserBody).when().post("v1.0/users").then().assertThat().statusCode(200);

    }


    @Test
    public void getInboxWithoutCredentials(){

        Response response = when().get("v1.0/users/inbox/requests");
        Assert.assertEquals(response.statusCode(), 401);
        when().get("v1.0/users/inbox/requests").then().assertThat().statusCode(401);
        response.prettyPrint();
    }

    @Test
    public void getInboxWithCorrectCredentials(){

        System.out.println(this.username);
        String response = given().auth().preemptive().basic(this.username, "expert").
                            when().get("v1.0/users/inbox/" + this.username).
                            then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = new JsonPath(response);
        String obtainedUsername = js.getString("username");
        List <Object> messages = js.getList("messages");
        Assert.assertEquals(obtainedUsername, this.username);
        Assert.assertEquals(messages.size(), 0);
    }
}
