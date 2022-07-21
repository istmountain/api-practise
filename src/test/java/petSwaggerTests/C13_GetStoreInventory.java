package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C13_GetStoreInventory extends BaseUrlPet {

    @Test
    public void name() {
         /*
    Curl

curl -X 'GET' \
  'https://petstore.swagger.io/v2/store/inventory' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/store/inventory
Server response
Code	Details
200
     */
        // 1 -Request url ve body'sini hazirlamak
        specPet.pathParams("pp1","store","pp2","inventory");
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        /*
        Response body

{
  "sold": 4,
  "new": 1,
  "string": 130,
  "alive": 2,
  "unavailable": 6,
  "avaliable": 1,
  "pending": 9,
  "not available": 1,
  "connector_up": 1,
  "available": 10,
  "OK": 704
}
        */
       JSONObject expdata=new JSONObject();
       expdata.put("sold",2);
       expdata.put("new", 1);
       expdata.put("string", 129);
        expdata.put("alive", 2);
        expdata.put("unavailable", 6);
        expdata.put("avaliable", 1);
        expdata.put("pending", 4);
        expdata.put("not available", 1);
        expdata.put("connector_up", 1);
        expdata.put("available", 22);
        expdata.put("OK", 684);

        // 3- Response'u kaydet
       JsonPath actual=response.jsonPath();
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expdata.get("sold"),actual.get("sold"));
        Assert.assertEquals(expdata.get("new"),actual.get("new"));
        Assert.assertEquals(expdata.get("string"),actual.get("string"));
        Assert.assertEquals(expdata.get("alive"),actual.get("alive"));
        Assert.assertEquals(expdata.get("unavailable"),actual.get("unavailable"));
        Assert.assertEquals(expdata.get("avaliable"),actual.get("avaliable"));
        Assert.assertEquals(expdata.get("pending"),actual.get("pending"));
        Assert.assertEquals(expdata.get("not available"),actual.get("not available"));
        Assert.assertEquals(expdata.get("connector_up"),actual.get("connector_up"));
        Assert.assertEquals(expdata.get("available"),actual.get("available"));
        Assert.assertEquals(expdata.get("OK"),actual.get("OK"));

    }
}
