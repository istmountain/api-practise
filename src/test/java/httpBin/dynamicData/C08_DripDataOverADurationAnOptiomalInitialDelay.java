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

public class C08_DripDataOverADurationAnOptiomalInitialDelay extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2" -H "accept: application/octet-stream"
Request URL
http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2
Server response
Code	Details
200
Response body
Download file
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 10
 content-type: application/octet-stream
 date: Fri, 29 Jul 2022 12:48:14 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2")
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
        /*
        curl -X GET "http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2" -H "accept: application/octet-stream"
Request URL
http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2
Server response
Code	Details
200
        Name	Description
duration
number
(query)
The amount of time (in seconds) over which to drip each byte

2
numbytes
integer
(query)
The number of bytes to respond with
10
code
integer
(query)
The response code that will be returned
200
delay
number
(query)
The amount of time (in seconds) to delay before responding

2

         */
    }
    @Test
    public void res() {
        /*
            Curl
curl -X GET "http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2" -H "accept: application/octet-stream"
Request URL
http://httpbin.org/drip?duration=2&numbytes=10&code=200&delay=2
Server response
Code	Details
200
         */
        specHttpbin.pathParam("pp1","drip").queryParams("duration",2,"numbytes",2,"code",200,"delay",2);
        Response response=given()
                .spec(specHttpbin)
                .accept("application/octet-stream")
                .when()
                .get("/{pp1}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/octet-stream");
    }
}
