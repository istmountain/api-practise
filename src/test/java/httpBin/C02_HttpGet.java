package httpBin;

import baseUrls.BaseHttpBin;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_HttpGet extends BaseHttpBin {
    @Test
    public void name() {
        //curl -X GET "http://httpbin.org/get" -H "accept: application/json"
        //Request URL
        //http://httpbin.org/get
        // 1 -Request url ve body'sini hazirlamak
        specHttpbin.pathParam("pp1","get");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}");
        // 2- Expected Data'yi hazirla
        JSONObject exp=new JSONObject();
        //  "origin": "88.236.86.164",
        //  "url": "http://httpbin.org/get"
        exp.put("origin", "88.236.86.164");
        exp.put( "url", "http://httpbin.org/get");
        // 3- Response'u kaydet
        JsonPath act=response.jsonPath();
        // 4- Assertion'lari yap
        response.then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("origin"),act.get("origin"));
        Assert.assertEquals(exp.get("url"),act.get("url"));
    }
}
