package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C06_PostPetId extends BaseUrlPet {
    @Test
    public void name() {
        /*
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
}
curl -X 'POST' \
  'https://petstore.swagger.io/v2/pet/9' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'name=cats&status=avaliable'
         */
        specPet.pathParams("pp1","pet","pp2",9);
        //create request body
        JSONObject body=new JSONObject();
        body.put("name","cats");
        body.put("status","avaliable");
        Response response=given()
                .spec(specPet)
                //.contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .contentType("application/json")
                .body(body.toString())
                .when()

                .post("/{pp1}/{pp2}");
        response.prettyPrint();
        System.out.println(response.getContentType());
        response
                .then()
                .assertThat()
                .statusCode(200);


    }
}
