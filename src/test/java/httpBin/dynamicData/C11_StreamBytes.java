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

public class C11_StreamBytes extends BaseHttpBin {
    /*

Response body
Download file
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-type: application/octet-stream
 date: Fri, 29 Jul 2022 13:48:24 GMT
 server: gunicorn/19.9.0
 transfer-encoding: chunked
Responses
Code	Description
200
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/stream-bytes/4");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }

    @Test
    public void req() {
        /*
            Curl
curl -X GET "http://httpbin.org/stream-bytes/4" -H "accept: application/octet-stream"
Request URL
http://httpbin.org/stream-bytes/4
Server response
Code	Details
200
         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/stream-bytes/4")
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
curl -X GET "http://httpbin.org/stream-bytes/4" -H "accept: application/octet-stream"
Request URL
http://httpbin.org/stream-bytes/4
Server response
Code	Details
200
         */
        specHttpbin.pathParams("pp1","stream-bytes","pp2",4);
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

