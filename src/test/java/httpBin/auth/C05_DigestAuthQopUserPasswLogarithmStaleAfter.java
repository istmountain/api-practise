package httpBin.auth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.digest;
import static io.restassured.RestAssured.given;

public class C05_DigestAuthQopUserPasswLogarithmStaleAfter {
    /*
    Curl
curl -X GET "http://httpbin.org/digest-auth/undefined/ece/1234567/MD5/never" -H "accept: application/json"
Request URL
http://httpbin.org/digest-auth/undefined/ece/1234567/MD5/never
Server response
Code	Details
200
Response body
Download
{
  "authenticated": true,
  "user": "ece"
}
     */

    @Test
    public void DigestAuthWithLogStaleREQ() {
        RequestSpecification req=new RequestSpecBuilder().setAuth(digest("ece", "1234567"))
                .setBaseUri("http://httpbin.org/digest-auth/undefined/ece/1234567/MD5/never").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        //expData
        JSONObject exp=new JSONObject();
        /*
        {
  "authenticated": true,
  "user": "ece"
}*/
        exp.put("authenticated",true);
        exp.put("user", "ece");
        //response
        JsonPath act=response.jsonPath();
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),act.get("authenticated"));
        Assert.assertEquals(exp.get("user"),act.get("user"));
    }
    @Test
    public void DigestAuthWithLogStaleRES() {
        //Response http://httpbin.org/digest-auth/undefined/ece/1234567/MD5/never
        Response response=given()
                .auth()
                .digest("ece", "1234567")
                .accept(ContentType.JSON)
                .when()
                .get("http://httpbin.org/digest-auth/undefined/ece/1234567/MD5/never");

        //expData
        JSONObject exp=new JSONObject();
        /*
        {
  "authenticated": true,
  "user": "ece"
}*/
        exp.put("authenticated",true);
        exp.put("user", "ece");
        //response
        JsonPath act=response.jsonPath();
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),act.get("authenticated"));
        Assert.assertEquals(exp.get("user"),act.get("user"));
    }
}
