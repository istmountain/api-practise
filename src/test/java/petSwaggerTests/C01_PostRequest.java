package petSwaggerTests;

import baseUrls.BaseUrlPet;
import com.google.gson.JsonArray;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_PostRequest extends BaseUrlPet {


    @Test
    public void name() {
        /* post request
         //https://petstore.swagger.io/v2/pet
 'https://petstore.swagger.io/v2/pet' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \

         {
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}'
         */
        // 1 -Request url ve body'sini hazirlamak
        JSONObject requestBody=new JSONObject();
        JSONObject category=new JSONObject();
        JSONObject tagsBody=new JSONObject();
        JSONArray photo=new JSONArray();
        JSONArray tags=new JSONArray();
        tagsBody.put("id", 0);
        tagsBody.put("name", "string");
        tags.put(tagsBody);
        category.put("id", 0);
        category.put("name","string");
        photo.put("Strings");
        requestBody.put("id", 0);
        requestBody.put("category",category);
        requestBody.put("name", "doggie");
        requestBody.put("photoUrls",photo);
        requestBody.put("tags",tags);
        requestBody.put("status", "available");
        specPet.pathParam("pp1","pet");
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/{pp1}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        JsonPath actual=response.jsonPath();
        System.out.println(actual.prettyPrint());
        System.out.println(response.getHeader("api_key"));
        // 4- Assertion'lari yap
        response.then()
                .assertThat()
                .statusCode(200);
    }
}
