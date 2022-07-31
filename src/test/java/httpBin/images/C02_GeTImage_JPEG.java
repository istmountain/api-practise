package httpBin.images;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class C02_GeTImage_JPEG extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/image/jpeg");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        /*
            Curl
curl -X GET "http://httpbin.org/image/jpeg" -H "accept: image/jpeg"
Request URL
http://httpbin.org/image/jpeg
Server response
Code	Details
200
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/image/jpeg").build();
        Response
        /*
            Curl
curl -X GET "http://httpbin.org/image/jpeg" -H "accept: image/jpeg"
Request URL
http://httpbin.org/image/jpeg
Server response
Code	Details
200
         */
    }
    @Test
    public void res() {
        /*
            Curl
curl -X GET "http://httpbin.org/image/jpeg" -H "accept: image/jpeg"
Request URL
http://httpbin.org/image/jpeg
Server response
Code	Details
200
         */
    }
    /*
    Curl
curl -X GET "http://httpbin.org/image/jpeg" -H "accept: image/jpeg"
Request URL
http://httpbin.org/image/jpeg
Server response
Code	Details
200
Response body

Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 35588
 content-type: image/jpeg
 date: Sun, 31 Jul 2022 11:58:33 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
     */
}
