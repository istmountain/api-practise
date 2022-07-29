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

public class C10_RangeNumbytes extends BaseHttpBin {
    /*

Response body
Download file
Response headers
 accept-ranges: bytes
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 5
 content-range: bytes 0-4/5
 content-type: application/octet-stream
 date: Fri, 29 Jul 2022 13:11:57 GMT
 etag: range5
 server: gunicorn/19.9.0
Responses
Code	Description
200
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/range/5");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        /*
                Curl
    curl -X GET "http://httpbin.org/range/5" -H "accept: application/octet-stream"
    Request URL
    http://httpbin.org/range/5
    Server response
    Code	Details
    200
         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/range/5")
                .setAccept("application/octet-stream").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/octet-stream");
    }
    @Test
    public void res() {
        /*
            Curl
    curl -X GET "http://httpbin.org/range/5" -H "accept: application/octet-stream"
    Request URL
    http://httpbin.org/range/5
    Server response
    Code	Details
    200
         */
        specHttpbin.pathParams("pp1","range","pp2",5);
        Response response=given()
                .spec(specHttpbin)
                .accept("application/octet-stream")
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/octet-stream");

    }
}
