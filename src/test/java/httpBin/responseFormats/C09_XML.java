package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C09_XML extends BaseHttpBin {

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/xml");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/xml").setContentType("application/xml").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/xml");
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","xml");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.XML)
                .when()
                .get("/{pp1}");
        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/xml");
    }
    /*
    Curl
curl -X GET "http://httpbin.org/xml" -H "accept: application/xml"
Request URL
http://httpbin.org/xml
Server response
Code	Details
200
Response body
Download
<?xml version='1.0' encoding='us-ascii'?>
  <!--  A SAMPLE set of slides  -->
  <slideshow
    title="Sample Slide Show"
    date="Date of publication"
    author="Yours Truly"
    >
    <!-- TITLE SLIDE -->
    <slide type="all">
      <title>Wake up to WonderWidgets!</title>
    </slide>
    <!-- OVERVIEW -->
    <slide type="all">
      <title>Overview</title>
      <item>
        Why
        <em>WonderWidgets</em>
         are great
      </item>
      <item/>
      <item>
        Who
        <em>buys</em>
         WonderWidgets
      </item>
    </slide>
  </slideshow>
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 522
 content-type: application/xml
 date: Thu, 28 Jul 2022 10:49:31 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
An XML document.
     */

}
