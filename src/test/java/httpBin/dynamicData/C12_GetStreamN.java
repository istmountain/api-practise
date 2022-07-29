package httpBin.dynamicData;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C12_GetStreamN extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/stream/3" -H "accept: application/json"
Request URL
http://httpbin.org/stream/3
Server response
Code	Details
200
Response body
Download
can't parse JSON.  Raw result:

{"url": "http://httpbin.org/stream/3", "args": {}, "headers": {"Host": "httpbin.org", "X-Amzn-Trace-Id": "Root=1-62e3e67f-16d7b8953e0319855becfac9", "Accept": "application/json", "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62", "Referer": "http://httpbin.org/", "Accept-Encoding": "gzip, deflate", "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7", "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76"}, "origin": "88.236.86.164", "id": 0}
{"url": "http://httpbin.org/stream/3", "args": {}, "headers": {"Host": "httpbin.org", "X-Amzn-Trace-Id": "Root=1-62e3e67f-16d7b8953e0319855becfac9", "Accept": "application/json", "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62", "Referer": "http://httpbin.org/", "Accept-Encoding": "gzip, deflate", "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7", "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76"}, "origin": "88.236.86.164", "id": 1}
{"url": "http://httpbin.org/stream/3", "args": {}, "headers": {"Host": "httpbin.org", "X-Amzn-Trace-Id": "Root=1-62e3e67f-16d7b8953e0319855becfac9", "Accept": "application/json", "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62", "Referer": "http://httpbin.org/", "Accept-Encoding": "gzip, deflate", "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7", "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76"}, "origin": "88.236.86.164", "id": 2}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-type: application/json
 date: Fri, 29 Jul 2022 13:54:07 GMT
 server: gunicorn/19.9.0
 transfer-encoding: chunked
Responses
Code	Description
200
Streamed JSON responses.
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/stream/3");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        /*
        Curl
curl -X GET "http://httpbin.org/stream/3" -H "accept: application/json"
Request URL
http://httpbin.org/stream/3
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/stream/3")
                .setAccept("application/json").build();
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
curl -X GET "http://httpbin.org/stream/3" -H "accept: application/json"
Request URL
http://httpbin.org/stream/3
         */
    }
    @Test
    public void res() {
        /*
        Curl
curl -X GET "http://httpbin.org/stream/3" -H "accept: application/json"
Request URL
http://httpbin.org/stream/3
         */
        specHttpbin.pathParams("pp1","stream","pp2",3);
        Response response=given()
                .spec(specHttpbin)
                .accept("application/json")
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
    }
}
