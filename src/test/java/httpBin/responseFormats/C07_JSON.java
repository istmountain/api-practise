package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C07_JSON extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/json" -H "accept: application/json"
Request URL
http://httpbin.org/json
Server response
Code	Details
200
Response body
Download
{
  "slideshow": {
    "author": "Yours Truly",
    "date": "date of publication",
    "slides": [
      {
        "title": "Wake up to WonderWidgets!",
        "type": "all"
      },
      {
        "items": [
          "Why <em>WonderWidgets</em> are great",
          "Who <em>buys</em> WonderWidgets"
        ],
        "title": "Overview",
        "type": "all"
      }
    ],
    "title": "Sample Slide Show"
  }
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 429
 content-type: application/json
 date: Wed, 27 Jul 2022 21:27:16 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/json");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/json").setAccept(ContentType.JSON).build();
        Response response=given()
                .spec(req)
                .when()
                .get();
    }
    @Test
    public void res() {
    }
}
