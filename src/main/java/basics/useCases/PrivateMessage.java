package basics.useCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PrivateMessage {

    public static void SendDefaultPrivateMessage(String username){

        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        String createPrivateMessageBody = " {\"message\": \"hello buddy!\"}\n";

        given().auth().preemptive().basic(username, "expert").body(createPrivateMessageBody).
                when().post("v1.0/users/inbox/" + username).then().assertThat().statusCode(200);
    }

    public static Response GetUserPrivateMessages(String username){

        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        Response response = given().auth().preemptive().basic(username, "expert").
                when().get("v1.0/users/inbox/" + username);

        return response;

    }
}
