package dummyTests;

import baseUrls.BaseDummyRestApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C05_DeleteRequest extends BaseDummyRestApi {
    @Test
    public void name() {
        /*
        https://dummy.restapiexample.com/api/v1/delete/2
         */
        // 1 -Request url ve body'sini hazirlamak
       specDummy.pathParams("pp1","delete","pp2",3);
        Response response=given()
                .spec(specDummy)
                .when()
                .delete("/{pp1}/{pp2}");
        // 2- Expected Data'yi hazirla
         /*
        Expected
        {
    "status": "success",
    "message": "successfully! deleted Records"
}
         */
        JSONObject expData=new JSONObject();
        expData.put("status", "success");
        expData.put("message", "Successfully! Record has been deleted");

        // 3- Response'u kaydet
        JsonPath actual=response.jsonPath();
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expData.get("status"),actual.get("status"));
        Assert.assertEquals(expData.get("message"),actual.get("message"));

    }
}
