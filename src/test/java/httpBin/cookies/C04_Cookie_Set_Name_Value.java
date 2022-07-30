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

public class C04_Cookie_Set_Name_Value extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
200
Response body
Download
{
  "cookies": {
    "ece": "alper",
    "fake": "fake_value",
    "freeform": "",
    "last_nonce": "1027ef932062b9ecae44b3557b950c76",
    "stale_after": "never"
  }
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 174
 content-type: application/json
 date: Sat, 30 Jul 2022 14:07:09 GMT
 server: gunicorn/19.9.0
Responses
Code
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/cookies/set/ece/alper");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        /*
            Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/cookies/set/ece/alper").setContentType("text/plain").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);
        /*
            Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
         */
    }
    @Test
    public void res() {
        specHttpbin.pathParams("pp1","cookies","pp2","set","pp3","ece","pp4","alper");
        Response response=given()
                .spec(specHttpbin)
                .accept("text/plain")
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);


        /*
            Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
         */
    }
}
