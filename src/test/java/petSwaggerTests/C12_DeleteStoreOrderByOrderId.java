package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C12_DeleteStoreOrderByOrderId extends BaseUrlPet {
    @Test
    public void name() {
        /*
        curl -X 'DELETE' \
  'https://petstore.swagger.io/v2/store/order/3' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/store/order/3
         */
        // Response ve body olustur
        specPet.pathParams("pp1","store","pp2","order","pp3",21);
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .when()
                .delete("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();
        // expected data'yı olusutr
        /*
        {
  "code": 200,
  "type": "unknown",
  "message": "3"
}
         */
        JSONObject expdata=new JSONObject();
        expdata.put("code", 200);
        expdata.put("type", "unknown");
        expdata.put("message", "21");
        //Response'u kaydet
        JsonPath actualData=response.jsonPath();
         // assertions
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expdata.get("code"),actualData.get("code"));
        Assert.assertEquals(expdata.get("type"),actualData.get("type"));
       Assert.assertEquals(expdata.get("message"),actualData.get("message"));
    }
}
