package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class C17_UserPutUpdate extends BaseUrlPet {
    @Test
    public void name() {
        /*
        Curl

curl -X 'PUT' \
  'https://petstore.swagger.io/v2/user/Arya' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '
Request URL
https://petstore.swagger.io/v2/user/Arya
Server response
Code	Details
200
Undocumented
Response body
Download
{
  "code": 200,
  "type": "unknown",
  "message": "7483956774362108546"
}
         */
        // 1 -Request url ve body'sini hazirlamak
        JSONObject updateBody=new JSONObject();
        updateBody.put("id",0);
        updateBody.put("username", "Asya");
        updateBody.put("firstName", "Asyay1");
        updateBody.put("lastName", "Demirel");
        updateBody.put("email", "string.@gmail.com");
        updateBody.put("password", "string6754");
        updateBody.put("phone", "8547575757775676");
        updateBody.put("userStatus", 0);
        specPet.pathParams("pp1","user","pp2","Arya");
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .body(updateBody.toString())
                .when()
                .put("/{pp1}/{pp2}");
        response.prettyPrint();
        /*
        {
  "id": 0,
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "userStatus": 0"userStatus": 0
}'
         */


        // 2- Expected Data'yi hazirla
        JSONObject expData=new JSONObject();
        // 3- Response'u kaydet
        JsonPath act=response.jsonPath();
        // 4- Assertion'lari yap
        response.then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals("unknown",act.get("type"));
        Assert.assertEquals("7483956774362108821",act.get("message"));
    }

}
