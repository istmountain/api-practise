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

public class C04_Image_SVG extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/image/png");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        /*
Curl
curl -X GET "http://httpbin.org/image/png" -H "accept: image/png"
Request URL
http://httpbin.org/image/png
Server response
Code	Details
200
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/image/png").build();
        Response response=given()
                .spec(req)
                .accept("image/png")
                .when()
                .get();
        response.prettyPrint();
        System.out.println(response.getContentType());
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("image/png");
        /*
Curl
curl -X GET "http://httpbin.org/image/png" -H "accept: image/png"
Request URL
http://httpbin.org/image/png
Server response
Code	Details
200
         */
    }
    @Test
    public void res() {
        specHttpbin.pathParams("pp1","image","pp2","png");
        Response response=given()
                .spec(specHttpbin)
                .accept("image/png")
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        System.out.println(response.getContentType());
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("image/png");
        /*
Curl
curl -X GET "http://httpbin.org/image/png" -H "accept: image/png"
Request URL
http://httpbin.org/image/png
Server response
Code	Details
200
Response body

Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 8090
 content-type: image/png
 date: Sun, 31 Jul 2022 12:05:58 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
         */
    }
}
