package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C06_HTML extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/html" -H "accept: text/html"
Request URL
http://httpbin.org/html
Server response
Code	Details
200
Response body
Download
<!DOCTYPE html>
<html>
  <head>
  </head>
  <body>
      <h1>Herman Melville - Moby-Dick</h1>

      <div>
        <p>
          Availing himself of the mild, summer-cool weather that now reigned in these latitudes, and in preparation for the peculiarly active pursuits
          shortly to be anticipated, Perth, the begrimed, blistered old blacksmith, had not removed his portable forge to the hold again, after concluding his
          contributory work for Ahab's leg, but still retained it on deck, fast lashed to ringbolts by the foremast; being now almost incessantly invoked by the headsmen,
        </p>
      </div>
  </body>
</html>
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 3741
 content-type: text/html; charset=utf-8
 date: Wed, 27 Jul 2022 20:28:58 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/html");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/html").setAccept("text/html").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html; charset=utf-8");
    }
    @Test
    public void res() {
        Response response=given()
                .accept("text/html")
                .when()
                .get("http://httpbin.org/html");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html; charset=utf-8");
    }
}
