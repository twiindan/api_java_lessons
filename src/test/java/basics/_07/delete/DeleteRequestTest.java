package basics._07.delete;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import basics.useCases.CreateUser;
import basics.useCases.PrivateMessage;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequestTest {

    String username = "";

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        this.username = CreateUser.createRandomUser();

    }

    @Test
    public void deleteAllMessagesTest (){
        PrivateMessage.SendDefaultPrivateMessage(this.username);
        given().auth().preemptive().basic(this.username, "expert").
                when().delete("v1.0/users/inbox/" + this.username).then().assertThat().statusCode(200).body(equalTo("messages deleted"));

        Response response = PrivateMessage.GetUserPrivateMessages(this.username);
        response.then().assertThat().statusCode(200).body("messages.size()", equalTo(0)).
                body("username", equalTo(this.username));

    }

    @Test
    public void deleteAllMessagesWithEmptyInboxTest(){
        given().auth().preemptive().basic(this.username, "expert").
                when().delete("v1.0/users/inbox/" + this.username).then().assertThat().statusCode(200).body(equalTo("messages deleted"));

        Response response = PrivateMessage.GetUserPrivateMessages(this.username);
        response.then().assertThat().statusCode(200).body("messages.size()", equalTo(0)).
                body("username", equalTo(this.username));
    }

    @Test
    public void deleteAllMessagesWithoutAuthentication(){
        PrivateMessage.SendDefaultPrivateMessage(this.username);
        when().delete("v1.0/users/inbox/" + this.username).then().assertThat().statusCode(401);

    }

}
