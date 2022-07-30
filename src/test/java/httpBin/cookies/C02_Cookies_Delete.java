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

public class C02_Cookies_Delete extends BaseHttpBin {
    /*
      Curl
curl -X GET "http://httpbin.org/cookies/delete?freeform=" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/delete?freeform=
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
 date: Sat, 30 Jul 2022 13:29:27 GMT
 server: gunicorn/19.9.0

     */
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/cookies/delete?freeform=");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        /*
              Curl
curl -X GET "http://httpbin.org/cookies/delete?freeform=" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/delete?freeform=
Server response
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/cookies/delete?freeform=").setContentType("text/plain").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
    }
    @Test
    public void res() {
    }

}
