package httpBin.responseFormats;

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
import static org.junit.Assert.assertEquals;

public class C05_Gzip extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/gzip" -H "accept: application/json"
Request URL
http://httpbin.org/gzip
Server response
Code	Details
200
Response body
Download

     */

    @Test
    public void http() throws IOException {
            URL url = new URL("http://httpbin.org/gzip");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            http.disconnect();

    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/gzip").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        //exp response body
        /*
        {
  "gzipped": true,
  "headers": {
    "Accept": "application/json",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76",
    "Host": "httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62",
    "X-Amzn-Trace-Id": "Root=1-62e18b7a-62ad638465451615624660e5"
  },
  "method": "GET",
  "origin": "178.245.94.65"
}
         */
        JSONObject exp=new JSONObject();
        JSONObject inner=new JSONObject();
        inner.put("Accept", "application/json");
        inner.put("Accept-Encoding", "gzip, deflate");
        inner.put("Accept-Language","tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7");
        inner.put("Cookie", "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76");
        inner.put("Host", "httpbin.org");
        inner.put("Referer", "http://httpbin.org/");
        inner.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62");
        exp.put("method", "GET");
        exp.put("origin", "178.245.94.65");
        exp.put("gzipped", true);
        exp.put("headers",inner);
        //save actual response
        JsonPath actual=new JsonPath();
        //
        assertEquals(exp.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(exp.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(exp.getJSONObject("headers").get("Accept-Language"),actual.get("headers.Accept-Language"));
        assertEquals(exp.getJSONObject("headers").get("Cookie"),actual.get("headers.Cookie"));
        assertEquals(exp.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(exp.getJSONObject("headers").get("Referer"),actual.get("headers.Referer"));
        assertEquals(exp.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(exp.get("method"),actual.get("method"));
        assertEquals(exp.get("origin"),actual.get("origin"));
        assertEquals(exp.get("gzipped"),actual.get("gzipped"));



        response
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","gzip");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}");
        // expected Response header
        JSONObject expHeader=new JSONObject();
        /*
        Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-encoding: gzip
 content-length: 430
 content-type: application/json
 date: Wed, 27 Jul 2022 19:01:14 GMT
 server: gunicorn/19.9.0
         */
        expHeader.put("access-control-allow-credentials",true);
        expHeader.put( "access-control-allow-origin", "*");
        expHeader.put("connection", "keep-alive");
        expHeader.put("content-encoding","gzip");
        expHeader.put("content-length",430);
        expHeader.put("content-type", "application/json");
        expHeader.put( "date", "Wed, 27 Jul 2022 19:01:14 GMT");
        expHeader.put("server", "gunicorn/19.9.0");

        response.then()
                .assertThat()
                .statusCode(200);

    }
}
