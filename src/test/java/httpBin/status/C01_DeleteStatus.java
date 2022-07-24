package httpBin.status;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C01_DeleteStatus {
    /*
    curl -X DELETE "http://httpbin.org/status/200" -H "accept: text/plain"
Request URL
http://httpbin.org/status/200
Server response
Code	Details
200
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: http://httpbin.org
 connection: keep-alive
 content-length: 0
 content-type: text/html; charset=utf-8
 date: Sun, 24 Jul 2022 12:55:14 GMT
 server: gunicorn/19.9.0
     */
    @Test
    public void reqDelete() {
        // we dont need to add header
        RequestSpecification req=new RequestSpecBuilder().addHeader("Authorization", "Basic ZWNlOjEyMzQ1Njc=")
                .setBaseUri("http://httpbin.org/status/200").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.TEXT)// although json format it will return as txt
                .when()
                .get();
        //Assert
        response
                .then()
                .assertThat()
                .statusCode(200)
                .header("access-control-allow-credentials","true")
                .header("access-control-allow-origin","*")
                .header("connection", "keep-alive")
                .header("content-length","0")
                .header("Server","gunicorn/19.9.0")
                .contentType("text/html; charset=utf-8");

        /*
        Response headers
Date=Sun, 24 Jul 2022 13:08:56 GMT
Content-Type=text/html; charset=utf-8
Content-Length=0
Connection=keep-alive
Server=gunicorn/19.9.0
Access-Control-Allow-Origin=*
Access-Control-Allow-Credentials=true
         */

    }
    @Test
    public void resDelete() {
        Response response=given()
                .header("Authorization", "Basic ZWNlOjEyMzQ1Njc=")
                .accept(ContentType.JSON)
                .when()
                .delete("http://httpbin.org/status/200");
        //Assert
        response
                .then()
                .assertThat()
                .statusCode(200)
                .header("access-control-allow-credentials","true")
                .header("access-control-allow-origin","*")
                .header("connection", "keep-alive")
                .header("content-length","0")
                .header("Server","gunicorn/19.9.0")
                .contentType("text/html; charset=utf-8");


    }
    @Test
    public void httpDelete() throws IOException {
        URL url = new URL("http://httpbin.org/status/200");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
