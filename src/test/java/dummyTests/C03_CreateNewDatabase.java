package dummyTests;

import baseUrls.BaseDummyRestApi;
import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_CreateNewDatabase extends BaseDummyRestApi {
    @Test
    public void name() {
        //	https://dummy.restapiexample.com/api/v1/create
        /*
        request
        	{
        	"name":"test",
        	"salary":"123",
        	"age":"23"}
{
*/
                // 1 -Request url ve body'sini hazirlamak
        specDummy.pathParam("pp1","create");
        JSONObject body=new JSONObject();
        body.put("name","Arya");
        body.put("salary","12345646");
        body.put("age","23");

        Response response=given()
                .spec(specDummy)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/{pp1}");
        response.prettyPrint();
     /* response//expected
 {
    "status": "success",
    "data": {
        "map": {
            "name": "Arya",
            "salary": "12345646",
            "age": "23"
        },
        "id": 4888
    },
    "message": "Successfully! Record has been added."
}
         */

        // 2- Expected Data'yi hazirla
        JSONObject expData=new JSONObject();
        JSONObject inner=new JSONObject();
        JSONObject innerin=new JSONObject();
        innerin.put( "name", "Arya");
        innerin.put("salary", "12345646");
        innerin.put("age", "23");
        inner.put( "map",innerin);
        inner.put("id", "488");
        expData.put("data",inner);
        expData.put("status", "success");
        expData.put("message", "Successfully! Record has been added.");

        // 3- Response'u kaydet
        JsonPath actual=response.jsonPath();
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expData.getJSONObject("data").getJSONObject("map").get("name"),actual.get("data.map.name"));
        Assert.assertEquals(expData.getJSONObject("data").getJSONObject("map").get("salary"),actual.get("data.map.salary"));
        Assert.assertEquals(expData.getJSONObject("data").getJSONObject("map").get("age"),actual.get("data.map.age"));
        Assert.assertEquals(expData.get("message"),actual.get("message"));
        Assert.assertEquals(expData.get("status"),actual.get("status"));
    }
}
