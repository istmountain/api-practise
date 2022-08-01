package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C03_Redirect_toGEt extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/redirect-to?url=http%3A%2F%2Fhttpbin.org%2Fabsolute-redirect%2F3&status_code=200");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/redirect-to?url=http%3A%2F%2Fhttpbin.org%2Fheaders&status_code=200")
                .setContentType("text/html").build();
        Response response=given()
                .spec(req)
                .accept("text/html")
                .when()
                .get();
        response.prettyPrint();
    }
    @Test
    public void res() {
        Response response=given()
                .accept("text/html")
                .when().get("http://httpbin.org/redirect-to?url=http%3A%2F%2Fhttpbin.org%2Fheaders&status_code=200");
    }
    /*
    Curl
curl -X GET "http://httpbin.org/redirect-to?url=http%3A%2F%2Fhttpbin.org%2Fabsolute-redirect%2F3&status_code=200" -H "accept: text/html"
Request URL
http://httpbin.org/redirect-to?url=http%3A%2F%2Fhttpbin.org%2Fabsolute-redirect%2F3&status_code=200
Server response
Code	Details
200
Undocumented
Response body
Download
{
  "args": {},
  "headers": {
    "Accept": "text/html",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76; freeform=; ece=alper",
    "Host": "httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62",
    "X-Amzn-Trace-Id": "Root=1-62e7cd5a-3ea9fa9822d95d2d4c51c175"
  },
  "origin": "88.236.86.164",
  "url": "http://httpbin.org/get"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 646
 content-type: application/json
 date: Mon, 01 Aug 2022 12:55:54 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
302
A redirection.
     */
}
