package serialization.exercices;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import serialization.models.exercices.UserListResponse;

import static io.restassured.RestAssured.given;

public class ListUserTests {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";

    }

    @Test
    public void getSecurityMessagesTest(){

        UserListResponse response = given().expect().defaultParser(Parser.JSON).
                when().get("v1.0/users").as(UserListResponse.class);

        System.out.println(response.getUsers().get(0).getName());
    }

}
