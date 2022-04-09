package basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;

public class Basics {

    public static void main(String[] args){
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        String response = when().get("v1.0").then().assertThat().statusCode(200).
                body("product", equalTo("forum")).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String obtainedVersion = js.getString("version");
        Assert.assertEquals(obtainedVersion, "0.2.0");
    }


}
