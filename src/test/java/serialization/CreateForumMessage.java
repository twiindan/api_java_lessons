package serialization;

import serialization.models.ForumMessageRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateForumMessage {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";

    }

    @Test
    public void createForumMessage(){

        String subject = "First Serialization";
        String message = "Hello Is my first serialization";
        String theme = "Development";

        ForumMessageRequest messageForum = new ForumMessageRequest();
        messageForum.setTheme(theme);
        messageForum.setMessage(message);
        messageForum.setSubject(subject);


        given().contentType(ContentType.JSON).body(messageForum).
                when().post("v1.0/forum").
                then().assertThat().statusCode(200).body(equalTo("message created"));

    }

    @Test
    public void createQAMessage(){
        String subject = "First QA Message";
        String message = "Hello Is my first QA message";
        ForumMessageRequest messageForum = new ForumMessageRequest();
        messageForum.createQAMessage(subject, message);
        given().contentType(ContentType.JSON).body(messageForum).
                when().post("v1.0/forum").
                then().assertThat().statusCode(200).body(equalTo("message created"));
    }


    @Test
    public void createRandomMessage(){

        ForumMessageRequest messageForum = new ForumMessageRequest();
        messageForum.createRandomMessage();
        given().contentType(ContentType.JSON).body(messageForum).
                when().post("v1.0/forum").
                then().assertThat().statusCode(200).body(equalTo("message created"));
    }

}
