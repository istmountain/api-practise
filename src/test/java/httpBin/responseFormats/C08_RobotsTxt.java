package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C08_RobotsTxt extends BaseHttpBin {
    /*
Curl
curl -X GET "http://httpbin.org/robots.txt" -H "accept: text/plain"
Request URL
http://httpbin.org/robots.txt
Server response
Code	Details
200
Response body
Download
User-agent: *
Disallow: /deny
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 30
 content-type: text/plain
 date: Thu, 28 Jul 2022 10:34:33 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Robots file
}*/

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/robots.txt").build();
        Response response=given()
                .spec(req)
                .accept("text/plain")
                .when()
                .get();
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("text/plain");
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","robots.txt");
        Response response=given()
                .spec(specHttpbin)
                .accept("text/plain")
                .when()
                .get("/{pp1}");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("text/plain");
    }
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/robots.txt");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}