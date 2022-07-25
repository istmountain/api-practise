package httpBin.headers;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_Ip extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/ip" -H "accept: application/json"
Request URL
http://httpbin.org/ip
Server response
Code	Details
200
Response body
Download
{
  "origin": "88.236.86.164"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 32
 content-type: application/json
 date: Mon, 25 Jul 2022 18:20:45 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void response() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/ip").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when().get();
        response.prettyPrint();
        //expobject
        JSONObject exp=new JSONObject();
        exp.put("origin", "88.236.86.164");
        //Assert
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
        Assert.assertEquals(exp.get("origin"),response.jsonPath().get("origin"));
    }
    @Test
    public void request() {
        specHttpbin.pathParam("pp1","ip");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when().get("/{pp1}");
        response.prettyPrint();
        //expobject
        JSONObject exp=new JSONObject();
        exp.put("origin", "88.236.86.164");
        //Assert
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
        Assert.assertEquals(exp.get("origin"),response.jsonPath().get("origin"));
    }

}
