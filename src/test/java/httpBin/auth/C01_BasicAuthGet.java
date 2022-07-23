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

public class C01_BasicAuthGet extends BaseHttpBin {
    @Test
    public void name() {
        /*
        Curl

Request URL
http://httpbin.org/basic-auth/ece/1234567
Server response
Code	Details
200

Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 46
 content-type: application/json
 date: Sat, 23 Jul 2022 09:24:01 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Sucessful authentication.
         */
        // 1 -Request url ve body'sini hazirlamak
        //curl -X GET "http://httpbin.org/basic-auth/ece/1234567" -H "accept: application/json"
        //  curl -H 'Authorization: Basic c2NvdHQ6dGlnZXI=' httpbun.org/basic-auth/scott/tiger
        specHttpbin.pathParams("pp1","basic-auth","pp2","ece","pp3","1234567");
        Response response=given()
                .header("Authorization", "Basic ZWNlOjEyMzQ1Njc=")
               // .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .get("http://www.httpbin.org/basic-auth/ece/1234567");
                //.get("/{pp1}/{pp2}/{pp3}");
        // 2- Expected Data'yi hazirla
        /*
        Response body
{
  "authenticated": true,
  "user": "ece"
}
         */
        JSONObject exp=new JSONObject();
        exp.put("authenticated",true);
        exp.put("user", "ece");
        // 3- Response'u kaydet
        JsonPath actual=response.jsonPath();
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),actual.get("authenticated"));
        Assert.assertEquals(exp.get("user"),actual.get("user"));
    }

    @Test
    public void dd() {
        RequestSpecification req=new RequestSpecBuilder().addHeader("Authorization","Basic ZWNlOjEyMzQ1Njc=")
                .setBaseUri("http://www.httpbin.org/basic-auth/ece/1234567").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        System.out.println(response.getStatusCode());
    }

    @Test
    public void ww() throws IOException {
        URL url = new URL("http://www.httpbin.org/basic-auth/ece/1234567");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
