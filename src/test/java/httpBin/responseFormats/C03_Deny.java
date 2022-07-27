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

public class C03_Deny extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/deny" -H "accept: text/plain"
Request URL
http://httpbin.org/deny
Server response
Code	Details
200
Response body
Download

          .-''''''-.
        .' _      _ '.
       /   O      O   \
      :                :
      |                |
      :       __       :
       \  .-"`  `"-.  /
        '.          .'
          '-......-'
     YOU SHOULDN'T BE HERE
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 239
 content-type: text/plain
 date: Wed, 27 Jul 2022 08:09:45 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Denied message

     */

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/deny").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);

    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","deny");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/deny");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
