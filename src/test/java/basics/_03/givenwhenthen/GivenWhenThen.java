package basics._03.givenwhenthen;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class GivenWhenThen {

    // Given --> Preconditions
    // When --> Actions
    // Then --> Expectations

    public static void main(String[] args){

        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";

        given().header("Accept", "*/*").when().get("v1.0");

        given().log().all().when().get("v1.0");

        when().get("v1.0").then().assertThat().statusCode(200);

        when().get("v1.0").then().assertThat().statusCode(200).body("product", equalTo("forum"));

        String response = when().get("v1.0").then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String obtainedVersion = js.getString("version");
        String obtainedProduct = js.getString("product");
        Assert.assertEquals(obtainedVersion, "0.2.0");
        Assert.assertEquals(obtainedProduct, "forum");

    }
}
