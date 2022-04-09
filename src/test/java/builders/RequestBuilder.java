package builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import serialization.models.exercices.CreateUserRequest;
import serialization.models.exercices.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestBuilder {

    RequestSpecification req;

    @BeforeClass
    public void setUp() {

        this.req =new RequestSpecBuilder().setBaseUri("https://api-testing-conference.herokuapp.com/")
                .setContentType(ContentType.JSON).setBasePath("v1.0/users").build();

    }


    @Test
    public void createQAUser(){
        CreateUserRequest createUserBody = new CreateUserRequest();
        createUserBody.setEmail("hello@hello.es");
        createUserBody.setName("Chewaka");
        createUserBody.setUsername("chewaka1");
        createUserBody.setPassword("han");
        createUserBody.setRole("QA");

        given().spec(this.req).body(createUserBody).
                when().post().
                then().assertThat().statusCode(200).body(equalTo("user created"));
    }


    @Test
    public void createRandomUser(){
        User userBody = new User();
        userBody.randomUser();

        given().spec(this.req).body(userBody).
                when().post().
                then().assertThat().statusCode(200).body(equalTo("user created"));

    }

}
