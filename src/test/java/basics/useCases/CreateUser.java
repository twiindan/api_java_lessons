package basics.useCases;

import io.restassured.RestAssured;
import basics.models.CreateUserBody;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateUser {

    public static String createRandomUser(){

        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        String randomId = Integer.toString(new Random().nextInt(1000000));
        String username = "AutomationAPI" + randomId;
        String createUserBody = CreateUserBody.GetCreateUserBody(username);
        given().body(createUserBody).when().post("v1.0/users").then().assertThat().statusCode(200);
        return username;
    }

}
