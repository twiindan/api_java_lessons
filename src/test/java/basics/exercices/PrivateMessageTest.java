package basics.exercices;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import basics.useCases.CreateUser;
import static org.hamcrest.Matchers.greaterThan;


import static io.restassured.RestAssured.given;

public class PrivateMessageTest {

    /*  E2E Test Case
     Create Message Body
     Send Message to user with Basic Authentication
     Get all messages
    */

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
    }

    @Test
    public void sendPrivateMessage() {

        String username = CreateUser.createRandomUser();
        String createPrivateMessageBody = " {\"message\": \"hello buddy!\"}\n";

        given().auth().preemptive().basic(username, "expert").body(createPrivateMessageBody).
                when().post("v1.0/users/inbox/" + username).then().assertThat().statusCode(200);

        String response = given().auth().preemptive().basic(username, "expert").
                when().get("v1.0/users/inbox/" + username).
                then().assertThat().statusCode(200).body("messages.size()", greaterThan(0)).extract().body().asString();

        System.out.println(response);
//        JsonPath js = new JsonPath(response);
//        List<Object> messages = js.getList("messages");
//        Assert.assertEquals(messages.size(), 0);
    }

}
