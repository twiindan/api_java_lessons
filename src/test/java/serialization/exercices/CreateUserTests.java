package serialization.exercices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import serialization.models.exercices.CreateUserRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTests {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api-testing-conference.herokuapp.com/";

    }

    @Test
    public void createUser() {

        CreateUserRequest createUserBody = new CreateUserRequest();
        createUserBody.setEmail("hello@hello.es");
        createUserBody.setName("Chewaka");
        createUserBody.setUsername("chewaka");
        createUserBody.setPassword("han");
        createUserBody.setRole("QA");

        given().contentType(ContentType.JSON).body(createUserBody).
                when().post("v1.0/users").
                then().assertThat().statusCode(200).body(equalTo("user created"));
    }

}
