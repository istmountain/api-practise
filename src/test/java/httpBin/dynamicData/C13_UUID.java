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

public class C13_UUID extends BaseHttpBin {
    /*
    Curl Return a UUID4.
curl -X GET "http://httpbin.org/uuid" -H "accept: application/json"
Request URL
http://httpbin.org/uuid
Server response
Code	Details
200
Response body
Download
{
  "uuid": "c18e09d7-1472-4929-ad8b-1d5ebfbe90f1"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 53
 content-type: application/json
 date: Fri, 29 Jul 2022 14:00:30 GMT
 server: gunicorn/19.9.0
     */
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/uuid");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        /*
          Curl Return a UUID4.
curl -X GET "http://httpbin.org/uuid" -H "accept: application/json"
Request URL
http://httpbin.org/uuid
Server response
Code	Details
200
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/uuid")
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
          Curl Return a UUID4.
curl -X GET "http://httpbin.org/uuid" -H "accept: application/json"
Request URL
http://httpbin.org/uuid
Server response
Code	Details
200
         */
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","uuid");
        Response response=given()
                .spec(specHttpbin)
                .accept("application/json")
                .when()
                .get("/{pp1}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
        /*
          Curl Return a UUID4.
curl -X GET "http://httpbin.org/uuid" -H "accept: application/json"
Request URL
http://httpbin.org/uuid
Server response
Code	Details
200
         */
    }
}
