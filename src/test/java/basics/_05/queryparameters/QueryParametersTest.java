package basics._05.queryparameters;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import basics.models.CreateMessageForumBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class QueryParametersTest {

    List<String> topicsList = new ArrayList<>(List.of("Security", "Development", "Automation", "Testing"));

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        for (int i = 0; i < 4; ++i) {
            String createForumMessageBody = CreateMessageForumBody.GetCreateMessageForumBody(topicsList.get(i),
                    Integer.toString(i));
            given().body(createForumMessageBody).
                    when().post("v1.0/forum").
                    then().assertThat().statusCode(200).body(equalTo("message created"));

        }
        Response response = when().get("v1.0/forum");
        //response.prettyPrint();
    }


    @Test
    public void queryParametersTest(){
        Response response = given().queryParam("theme", "Security").get("v1.0/forum");
        String responseBody = response.body().asString();
        JsonPath js = new JsonPath(responseBody);
        List <HashMap> messageList = js.getList("Security");
        Assert.assertTrue(messageList.size() > 0);
        for (HashMap message: messageList){
            Assert.assertEquals(message.get("theme"), "Security");
        }
    }
}
