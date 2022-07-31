package httpBin.images;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C05_Image_WEBP extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/image/webp");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        /*    Curl
curl -X GET "http://httpbin.org/image/webp" -H "accept: image/webp"
Request URL
http://httpbin.org/image/webp
Server response
Code	Details
200
Response body
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/image/webp").build();
        Response response=given()
                .spec(req)
                .accept("image/webp")
                .when()
                .get();
        response.prettyPrint();
        System.out.println(response.getContentType());
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("image/webp");
        /*
    Curl
curl -X GET "http://httpbin.org/image/webp" -H "accept: image/webp"
Request URL
http://httpbin.org/image/webp
Server response
Code	Details
200
Response body
         */
    }
    @Test
    public void res() {
        specHttpbin.pathParams("pp1","image","pp2","webp");
        Response response=given()
                .spec(specHttpbin)
                .accept("image/webp")
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        System.out.println(response.getContentType());
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("image/webp");
        /*
    Curl
curl -X GET "http://httpbin.org/image/webp" -H "accept: image/webp"
Request URL
http://httpbin.org/image/webp
Server response
Code	Details
200
Response body
         */
    }
    /*
    Curl
curl -X GET "http://httpbin.org/image/webp" -H "accept: image/webp"
Request URL
http://httpbin.org/image/webp
Server response
Code	Details
200
Response body

Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 10568
 content-type: image/webp
 date: Sun, 31 Jul 2022 12:13:04 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
     */
}
