package dummyTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Practice {
    @Test
    public void req() {
        /*
        API for below request 
https://reqres.in/api/users
{
    "name": "Ece",
    "job": "QA"
}


post request
Json
log request and response
simple assertion

         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users").setContentType(ContentType.JSON).build();
        JSONObject body=new JSONObject();
        body.put("name", "Ece");
        body.put("job","QA");
        Response response=given()
                .spec(req)
                .body(body.toString())
                .log().all()
                .when()
                .post();

        JsonPath actual=response.jsonPath();
        response.then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", Matchers.equalTo("Ece"));

        //second assertion type
        Assert.assertEquals(body.get("name"),actual.get("name"));

    }
}
