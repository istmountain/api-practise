package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.AnnotatedArrayType;

import static io.restassured.RestAssured.given;

public class C18_UserDElete extends BaseUrlPet {
    @Test
    public void name() {
        /*
        Curl

curl -X 'DELETE' \
  'https://petstore.swagger.io/v2/user/Asya' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/user/Asya
Server response
Code	Details
200
Undocumented
Response body
Download
{
  "code": 200,
  "type": "unknown",
  "message": "Asya"
}
         */
        // 1 -Request url ve body'sini hazirlamak
        specPet.pathParams("pp1","user","pp2","Asya");
        Response response=given()
                .spec(specPet)
                .when()
                .delete("/{pp1}/{pp2}");
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        JsonPath act=response.jsonPath();
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals("unknown",act.get("type"));
        Assert.assertEquals("Asya",act.get("message"));

    }
}
