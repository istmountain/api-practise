package httpBin.responseInspection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C04_FreeformQuery {
    /*
        Curl
        curl -X GET "http://httpbin.org/response-headers?freeform=" -H "accept: application/json"
        Request URL
        http://httpbin.org/response-headers?freeform=
        Server response
        Code	Details
        200
        Response body
        Download
        {
          "Content-Length": "87",
          "Content-Type": "application/json",
          "freeform": ""
        }
        Response headers
         access-control-allow-credentials: true
         access-control-allow-origin: *
         connection: keep-alive
         content-length: 87
         content-type: application/json
         date: Tue, 26 Jul 2022 15:24:55 GMT
         freeform:
         server: gunicorn/19.9.0
        Responses
        Code	Description
        200
        Response headers
     */

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org")
                .addPathParam("pp1","response-headers")
               // .addFormParam("freeform","")
                .addQueryParam("freeform","")
                .build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}");
        response.prettyPrint();
    }
    @Test
    public void res() {
        Response response=given()
                .accept(ContentType.JSON)
                .when()
                .get("http://httpbin.org/response-headers?freeform=");
        response.prettyPrint();

    }
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/response-headers?freeform=");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
