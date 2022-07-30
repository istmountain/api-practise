package httpBin.cookies;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C01_Cookies extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/cookies");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        /*
            Curl
curl -X GET "http://httpbin.org/cookies" -H "accept: application/json"
Request URL
http://httpbin.org/cookies
Server response
Code	Details
200
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/cookies").setContentType("application/json").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
                /*
            Curl
curl -X GET c -H "accept: application/json"
Request URL
http://httpbin.org/cookies
Server response
Code	Details
200
         */
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","cookies");
        Response response=given()
                .spec(specHttpbin)
                .accept("application/json")
                .when()
                .get("/{pp1}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
                /*
            Curl
curl -X GET "http://httpbin.org/cookies" -H "accept: application/json"
Request URL
http://httpbin.org/cookies
Server response
Code	Details
200
         */
    }
    /*
    Curl
curl -X GET "http://httpbin.org/cookies" -H "accept: application/json"
Request URL
http://httpbin.org/cookies
Server response
Code	Details
200
Response body
Download
{
  "cookies": {
    "fake": "fake_value",
    "last_nonce": "1027ef932062b9ecae44b3557b950c76",
    "stale_after": "never"
  }
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 132
 content-type: application/json
 date: Fri, 29 Jul 2022 14:08:21 GMT
 server: gunicorn/19.9.0
Responses
Code
     */
}
