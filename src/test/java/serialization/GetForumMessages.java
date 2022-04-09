package serialization;

import serialization.models.FilteredSecurityMessageResponse;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetForumMessages {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";

    }

    @Test
    public void getSecurityMessagesTest(){

        FilteredSecurityMessageResponse response = given().queryParam("theme", "Security").expect().defaultParser(Parser.JSON).
                when().get("v1.0/forum").as(FilteredSecurityMessageResponse.class);

        System.out.println(response.getSecurity().get(0).getMessage());
        Assert.assertEquals(response.getSecurity().get(0).getTheme(), "Security");
    }

}
