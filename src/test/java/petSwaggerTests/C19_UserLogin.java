package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_UserLogin extends BaseUrlPet {
    @Test
    public void name() {
        /*
        curl -X 'GET' \
  'https://petstore.swagger.io/v2/user/login?username=arya&password=1234567' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/user/login?username=arya&password=1234567
Server response
Code	Details
200
Response body
Download
{
  "code": 200,
  "type": "unknown",
  "message": "logged in user session:1658494308728"
}
         */
        specPet.pathParams("pp1","user","pp2","login")
                .queryParams("username","arya","password",1234567);
        Response response=given()
                .spec(specPet)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        //save response
        JsonPath act=response.jsonPath();
        //Assertions
        response.then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals("unknown",act.get("type"));
    }
}
