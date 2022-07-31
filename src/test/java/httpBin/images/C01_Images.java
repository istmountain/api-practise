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

public class C01_Images extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/image" -H "accept: image/webp"
Request URL
http://httpbin.org/image
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
 date: Sat, 30 Jul 2022 14:21:37 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/image");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/image").build();
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
curl -X GET "http://httpbin.org/image" -H "accept: image/webp"
Request URL
http://httpbin.org/image
Server response
Code	Details
200
         */
    }

    @Test
    public void res() {
                /*
          Curl
curl -X GET "http://httpbin.org/image" -H "accept: image/webp"
Request URL
http://httpbin.org/image
Server response
Code	Details
200
         */

    }

}
