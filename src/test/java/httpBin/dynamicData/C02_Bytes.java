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

public class C02_Bytes extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/bytes/5");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/bytes/5").setContentType("application/octet-stream").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/octet-stream");
    }
    @Test
    public void res() {
        specHttpbin.pathParams("pp1","bytes","pp2",5);
        Response response=given()
                .spec(specHttpbin)
                .accept("application/octet-stream")
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/octet-stream");

    }

    /*
    Curl
curl -X GET "http://httpbin.org/bytes/5" -H "accept: application/octet-stream"
Request URL
http://httpbin.org/bytes/5
Server response
Code	Details
200
Response body
Download file
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 5
 content-type: application/octet-stream
 date: Thu, 28 Jul 2022 11:50:25 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
     */
}
