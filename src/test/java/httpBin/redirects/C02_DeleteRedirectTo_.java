package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

public class C02_DeleteRedirectTo_ extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/redirect-to");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/redirect-to").setContentType("accept: text/html").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
     response.prettyPrint();
     response.then()
             .assertThat()
             .statusCode(500);

    System.out.println(response.getContentType() + response.getStatusCode());
    }
    @Test
    public void res() {
    }
    /*
Curl
curl -X DELETE "http://httpbin.org/redirect-to" -H "accept: text/html"
Request URL
http://httpbin.org/redirect-to
Server response
Code	Details
500
Undocumented
Error: INTERNAL SERVER ERROR
Response body
Download
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<title>500 Internal Server Error</title>
<h1>Internal Server Error</h1>
<p>The server encountered an internal error and was unable to complete your request.  Either the server is overloaded or there is an error in the application.</p>
Response headers
 connection: keep-alive
 content-length: 291
 content-type: text/html
 date: Mon, 01 Aug 2022 11:35:28 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
302     */
}
