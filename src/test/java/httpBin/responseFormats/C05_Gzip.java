package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_Gzip extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/gzip" -H "accept: application/json"
Request URL
http://httpbin.org/gzip
Server response
Code	Details
200
Response body
Download

     */

    @Test
    public void http() throws IOException {
            URL url = new URL("http://httpbin.org/gzip");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            http.disconnect();

    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/gzip").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        //exp response body
        /*
     {
    "gzipped": true,
    "headers": {
        "Accept": "application/json, application/javascript, text/javascript, text/json",
        "Accept-Encoding": "gz1p,deflate",
        "Host": "httpbin.org",
        "User-Agent": "Apache-HttpClient/4.5.3 (Java/18.0.1.1)",
        "X-Amzn-Trace-Id": "Root=1-62e1947f-4dba55864164bfa5280da270"
    },
    "method": "GET",
    "origin": "178.245.94.65"
}

         */
        JSONObject exp=new JSONObject();
        JSONObject inner=new JSONObject();
        inner.put("Accept", "application/json, application/javascript, text/javascript, text/json");
        inner.put( "Accept-Encoding", "gz1p,deflate");
        inner.put("Host","httpbin.org");
        inner.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        exp.put("method", "GET");
        exp.put("origin", "178.245.94.65");
        exp.put("gzipped", true);
        exp.put("headers",inner);
        //save actual response
        JsonPath actual=response.jsonPath();
        //
        assertEquals(exp.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(exp.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(exp.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(exp.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(exp.get("method"),actual.get("method"));
        assertEquals(exp.get("origin"),actual.get("origin"));
        assertEquals(exp.get("gzipped"),actual.get("gzipped"));



        response
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","gzip");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}");
        System.out.println(response.getHeaders());
        // expected Response header
        JSONObject expHeader=new JSONObject();
        /*
        Response headers
Date=Wed, 27 Jul 2022 19:45:49 GMT
Content-Type=application/json
Content-Length=272
Connection=keep-alive
Server=gunicorn/19.9.0
Content-Encoding=gzip
Access-Control-Allow-Origin=*
Access-Control-Allow-Credentials=true
         */
        expHeader.put("Access-Control-Allow-Credentials","true");
        expHeader.put( "Access-Control-Allow-Origin", "*");
        expHeader.put("Connection", "keep-alive");
        expHeader.put("Content-Encoding","gzip");
        expHeader.put("Content-Type", "application/json");
        expHeader.put("Server", "gunicorn/19.9.0");

        response.then()
                .assertThat()
                .statusCode(200);
        assertEquals(expHeader.get("Content-Type"),response.getHeader("Content-Type"));
        assertEquals(expHeader.get("Connection"),response.getHeader("Connection"));
        assertEquals(expHeader.get("Server"),response.getHeader("Server"));
        assertEquals(expHeader.get("Content-Encoding"),response.getHeader("Content-Encoding"));
        assertEquals(expHeader.get("Access-Control-Allow-Origin"),response.getHeader("Access-Control-Allow-Origin"));
        assertEquals(expHeader.get("Access-Control-Allow-Credentials"),response.getHeader("Access-Control-Allow-Credentials"));


    }
}
