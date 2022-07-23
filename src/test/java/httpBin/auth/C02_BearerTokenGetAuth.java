package httpBin.auth;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C02_BearerTokenGetAuth extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/bearer" -H "accept: application/json" -H "Authorization: Bearer ZWNlOjEyMzQ1Njc="
Request URL
http://httpbin.org/bearer
Server response
Code	Details
200
Response body
Download
{
  "authenticated": true,
  "token": "ZWNlOjEyMzQ1Njc="
}
     */
    @Test
    public void restAssuredRequest() {
        specHttpbin.pathParam("pp1","bearer");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer ZWNlOjEyMzQ1Njc=")
                .when()
                .get("/{pp1}");
        //expData
       JSONObject exp=new JSONObject();
       exp.put("authenticated", true);
       exp.put("token", "ZWNlOjEyMzQ1Njc=");
       //Response'u kaydet
        JsonPath act=response.jsonPath();
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),act.get("authenticated"));
        Assert.assertEquals(exp.get("token"),act.get("token"));
    }

    @Test
    public void restAssuredHeader() {
        RequestSpecification req= new RequestSpecBuilder().addHeader("Authorization", "Bearer ZWNlOjEyMzQ1Njc=")
                .setBaseUri("http://httpbin.org/bearer").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void httpFormat() throws IOException {
        URL url = new URL("http://httpbin.org/bearer");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("HEAD");
        http.setRequestProperty("Authorization", "Bearer ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }
}
