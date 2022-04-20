package basics._02.get;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.when;

public class GetRequest {

    public static void main(String[] args){
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        Response response = when().get("v1.0");

        response.prettyPrint();

        Integer statusCode = response.statusCode();
        System.out.println(statusCode);

        Integer expectedStatusCode = 200;
        Assert.assertEquals(statusCode, expectedStatusCode);

        String body = response.getBody().asString();
        System.out.println(body);

        String expectedBody = "{\n" +
                "    \"product\": \"forum\",\n" +
                "    \"version\": \"0.2.0\"\n" +
                "}";

        Assert.assertEquals(body, expectedBody);

        Headers headers = response.headers();
        for (Header header: headers){
            System.out.println(header.toString());
        }
        String expectedContentTypeHeader = "application/json";
        String contentTypeHeader = headers.getValue("Content-Type");
        Assert.assertEquals(contentTypeHeader, expectedContentTypeHeader);

    }

}
