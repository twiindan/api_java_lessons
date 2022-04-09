package usecases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import serialization.models.exercices.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class createUser {

    public static void createRandomUser(){

        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";
        User createUserBody = new User();
        createUserBody.randomUser();
        given().contentType(ContentType.JSON).body(createUserBody).
                when().post("v1.0/users").
                then().assertThat().statusCode(200).body(equalTo("user created"));
    }
}
