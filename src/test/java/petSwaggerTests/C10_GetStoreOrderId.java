package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C10_GetStoreOrderId extends BaseUrlPet {

    @Test
    public void name() {
        /*
        Curl

curl -X 'GET' \
  'https://petstore.swagger.io/v2/store/order/2' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/store/order/2

Response body
Download
{
  "id": 2,
  "petId": 2,
  "quantity": 200,
  "shipDate": "2023-07-20T19:55:02.322+0000",
  "status": "placed",
  "complete": true
}
         */
               specPet.pathParams("pp1","store","pp2","order","pp3",2);
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();
        //expected body
        JSONObject expedata=new JSONObject();
        expedata.put("id", 2);
        expedata.put("petId", 2);
        expedata.put("quantity", 200);
        expedata.put("shipDate", "2023-07-20T19:55:02.322+0000");
        expedata.put("status", "placed");
        expedata.put("complete", true);
        //response'u kaydet
        JsonPath actual=response.jsonPath();
        //assertions
        response.then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expedata.get("id"),actual.get("id"));
        Assert.assertEquals(expedata.get("petId"),actual.get("petId"));
        Assert.assertEquals(expedata.get("quantity"),actual.get("quantity"));
        Assert.assertEquals(expedata.get("shipDate"),actual.get("shipDate"));
        Assert.assertEquals(expedata.get("status"),actual.get("status"));
        Assert.assertEquals(expedata.get("complete"),actual.get("complete"));

    }
}
