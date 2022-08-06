package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C06_RedirectsPutData extends BaseHttpBin {
    /*
    Curl
curl -X PUT "http://httpbin.org/redirect-to" -H "accept: text/html" -H "Content-Type: application/x-www-form-urlencoded" -d "url=http%3A%2F%2Fwww.amazon.com&status_code=10"
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
 date: Sat, 06 Aug 2022 18:24:57 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
302
A redirection.
     */

    @Test
    public void http() throws IOException {
        /*
            Curl
curl -X PUT "http://httpbin.org/redirect-to" -H "accept: text/html" -H "Content-Type: application/x-www-form-urlencoded" -d "url=http%3A%2F%2Fwww.amazon.com&status_code=10"
Request URL
http://httpbin.org/redirect-to
Server response
         */
        URL url = new URL("http://httpbin.org/redirect-to");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "text/html");
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "%22url%22=%22https%3A%2F%2Fwww.amazon.com&status_code=10%22";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
            /*
            Curl
curl -X PUT "http://httpbin.org/redirect-to" -H "accept: text/html" -H "Content-Type: application/x-www-form-urlencoded" -d "url=http%3A%2F%2Fwww.amazon.com&status_code=10"
Request URL
http://httpbin.org/redirect-to
Server response
         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/redirect-to")
                .addFormParam("url","http://www.amazon.com&status_code=10")
                .setContentType("application/x-www-form-urlencoded").setAccept("text/html")
                .build();
        Response response=given()
                .spec(req)
                .when()
                .put();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(500);
    }
    @Test
    public void res() {
            /*
            Curl
curl -X PUT "http://httpbin.org/redirect-to" -H "accept: text/html" -H "Content-Type: application/x-www-form-urlencoded" -d "url=http%3A%2F%2Fwww.amazon.com&status_code=10"
Request URL
http://httpbin.org/redirect-to
Server response
         */
    }
}
