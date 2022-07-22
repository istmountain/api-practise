package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C15_CreateWithList extends BaseUrlPet {
    @Test
    public void name() {
        /*
        Curl

curl -X 'POST' \
  'https://petstore.swagger.io/v2/user/createWithList' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '[
Request URL
https://petstore.swagger.io/v2/user/createWithList
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
        // 1 -Request url ve body'sini hazirlamak
        specPet.pathParams("pp1","user","pp2","createWithList");

        JSONArray arrayBody=new JSONArray();
        JSONObject obj=new JSONObject();
        obj.put("id", 0);
        obj.put("username", "Arya");
        obj.put("firstName", "Sarkisyan");
        obj.put("email", "string@gmail.com");
        obj.put("password", "string1234");
        obj.put("phone", "50775758475");
        obj.put("userStatus", 0);
        arrayBody.put(obj);

        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .body(arrayBody.toString())
                .when()
                .post("/{pp1}/{pp2}");
        /*
         {
    "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string",
    "phone": "string",
    "userStatus": 0
  }
]'
         */
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
