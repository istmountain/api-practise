package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C20_UserLogOut extends BaseUrlPet {
    @Test
    public void name() {
        /*
        Curl

curl -X 'GET' \
  'https://petstore.swagger.io/v2/user/logout' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/user/logout
Server response
Code	Details
200
Response body
Download
{
  "code": 200,
  "type": "unknown",
  "message": "ok"
}
         */
        specPet.pathParams("pp1","user","pp2","logout");
        Response response=given()
                .spec(specPet)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals("unknown",response.jsonPath().get("type"));
        Assert.assertEquals("ok",response.jsonPath().get("message"));
    }
}
