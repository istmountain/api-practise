package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C09_Post extends BaseUrlPet {
    @Test
    public void name() {
        /*
        Curl

curl -X 'POST' \
  'https://petstore.swagger.io/v2/store/order' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "petId": 0,
  "quantity": 0,
  "shipDate": "2022-07-21T08:50:27.625Z",
  "status": "placed",
  "complete": true
}'

         */
        // 1 -Request url ve body'sini hazirlamak
        specPet.pathParams("pp1","store","pp2","order");
        JSONObject requestBody=new JSONObject();
        requestBody.put("id", 0);
        requestBody.put("petId", 0);
        requestBody.put("quantity", 0);
        requestBody.put("shipDate", "2022-07-21T08:50:27.625Z");
        requestBody.put("status", "placed");
        requestBody.put("complete", true);
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/{pp1}/{pp2}");

        // 2- Expected Data'yi hazirla
        /*

Response body
Download
{
  "id": 3124324353454342700,
  "petId": 0,
  "quantity": 0,
  "shipDate": "2022-07-21T08:50:27.625+0000",
  "status": "placed",
  "complete": true
}
         */
        JSONObject expData=new JSONObject();
        expData.put("id", 0);
        expData.put("petId", 0);
        expData.put("quantity", 0);
        expData.put("shipDate", "2022-07-21T08:50:27.625+0000");
        expData.put("status", "placed");
        expData.put("complete", true);

        // 3- Response'u kaydet
        JsonPath actual=response.jsonPath();
        // 4- Assertion'lari yap
        response.then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expData.get("petId"),actual.get("petId"));
        Assert.assertEquals(expData.get("quantity"),actual.get("quantity"));
        Assert.assertEquals(expData.get("shipDate"),actual.get("shipDate"));
        Assert.assertEquals(expData.get("complete"),actual.get("complete"));
        Assert.assertEquals(expData.get("status"),actual.get("status"));
    }
}
