package builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import serialization.models.exercices.UserListResponse;
import usecases.createUser;

import static io.restassured.RestAssured.given;

public class ResponseBuilder {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void setUp(){
        createUser.createRandomUser();
        this.reqSpec =new RequestSpecBuilder().setBaseUri("https://api-testing-conference.herokuapp.com/")
                .setBasePath("v1.0/users").build();

        this.resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @Test
    public void getUsers(){
        UserListResponse response =  given().spec(this.reqSpec).
                                     when().get().
                                     then().spec(resSpec).extract().response().as(UserListResponse.class);

        Assert.assertTrue(response.getUsers().size() >= 0);
    }

}
