package petSwaggerTests;

import baseUrls.BaseUrlPet;
import com.google.gson.JsonArray;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class C14_CreateUserWithArray extends BaseUrlPet {
    @Test
    public void name() {
        /*
        Curl

curl -X 'POST' \
  'https://petstore.swagger.io/v2/user/createWithArray' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \

Request URL
https://petstore.swagger.io/v2/user/createWithArray
         */
        // 1 -Request url ve body'sini hazirlamak
        /*
         -d '[
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
        JSONArray arrayBody=new JSONArray();
        JSONObject body=new JSONObject();
        body.put("id", 0);
        body.put("username", "Asya");
        body.put("firstName", "Demirel");
        body.put("lastName", "Demire");
        body.put("email", "serkan_asya@gmail.com");
        body.put("password", "726763gfhghd");
        body.put("phone", "3646573828838");
        body.put("userStatus", 0);
        arrayBody.put(body);

        specPet.pathParams("pp1","user","pp2","createWithArray");
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .body(arrayBody.toString())
                .when()
                .post("/{pp1}/{pp2}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        response.jsonPath();
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
