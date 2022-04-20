package basics._08.cookies;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CookiesTest {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";

    }

    @Test
    public void noCookiesTest(){

        Response response = when().get("v1.0/welcome");
        response.then().assertThat().statusCode(200);
        String responseBody = response.body().asString();
        Assert.assertEquals(responseBody, "Hello there! Nice to meet you");
    }

    @Test
    public void CookiesTest(){

        Response response = given().cookies("visited", "yes").when().get("v1.0/welcome");
        response.then().assertThat().statusCode(200);
        String responseBody = response.body().asString();
        Assert.assertEquals(responseBody, "Welcome back! Nice to see you again");
    }

    @Test
    public void CookieE2ETest(){

        Response firstResponse = when().get("v1.0/welcome");
        Cookies visitedCookie = firstResponse.detailedCookies();
        Assert.assertEquals(visitedCookie.getValue("visited"), "yes");
        Assert.assertEquals(firstResponse.body().asString(), "Hello there! Nice to meet you");

        Response secondResponse = given().cookies(visitedCookie).when().get("v1.0/welcome");
        String responseBody = secondResponse.body().asString();
        Assert.assertEquals(responseBody, "Welcome back! Nice to see you again");

    }

}
