package DataProviders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import serialization.models.ForumMessageRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataProviders {

    RequestSpecification req;

    @BeforeClass
    public void setUp() {

        this.req =new RequestSpecBuilder().setBaseUri("https://api-testing-conference.herokuapp.com/")
                .setContentType(ContentType.JSON).setBasePath("v1.0/forum").build();

    }


    @org.testng.annotations.DataProvider
    public Object [][] getMessagesData (){

        return new Object [][] {{"Security Message", "First Security Message with Data Provider", "Security"},
                                {"Development Message", "First Development Message with Data Provider", "Development"},
                                {"Automation Message", "First Automation Message with Data Provider", "Automation"},
                                {"Testing Message", "First Testing Message with Data Provider", "Testing"}};
    }

    @Test(dataProvider="getMessagesData")
    public void createForumMessage(String subject, String message, String theme){

        ForumMessageRequest messageForum = new ForumMessageRequest(message, subject, theme);

        given().spec(this.req).body(messageForum).
                when().post().
                then().assertThat().statusCode(200).body(equalTo("message created"));
    }

}
