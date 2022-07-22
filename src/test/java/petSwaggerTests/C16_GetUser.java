package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_GetUser extends BaseUrlPet {
    @Test
    public void name() {
        /*
        curl -X 'GET' \
  'https://petstore.swagger.io/v2/user/Arya' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/user/Arya
Server response
Code	Details
200
Response body
Download
         */
        // 1 -Request url ve body'sini hazirlamak
        specPet.pathParams("pp1","user","pp2","Arya");
        Response response=given()
                .spec(specPet)
                .when()
                .get("/{pp1}/{pp2}");
        // 2- Expected Data'yi hazirla
        JSONObject expData=new JSONObject();
        expData.put("id", 7483956774362107823L);
        expData.put("username", "Arya");
        expData.put("firstName", "Sarkisyan");
        expData.put("email", "string@gmail.com");
        expData.put("password", "string1234");
        expData.put("phone", "50775758475");
        expData.put("userStatus", 0);
        // 3- Response'u kaydet
        JsonPath actual=response.jsonPath();
        /*
        {
  "id": 7483956774362107823,
  "username": "Arya",
  "firstName": "Sarkisyan",
  "email": "string@gmail.com",
  "password": "string1234",
  "phone": "50775758475",
  "userStatus": 0
}
         */
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expData.get("id"),actual.get("id"));
        Assert.assertEquals(expData.get("username"),actual.get("username"));
        Assert.assertEquals(expData.get("firstName"),actual.get("firstName"));
        Assert.assertEquals(expData.get("email"),actual.get("email"));
        Assert.assertEquals(expData.get("phone"),actual.get("phone"));
        Assert.assertEquals(expData.get("password"),actual.get("password"));
        Assert.assertEquals(expData.get("userStatus"),actual.get("userStatus"));

    }
}
