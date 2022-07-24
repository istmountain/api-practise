package httpBin.status;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C03_PatchStatus {
    @Test
    public void statusPatch300() {
        /*

Code	Description
100
Informational responses
200
Success
300
Redirection
400
Client Errors
500
Server Errors
         */
        //300 (MULTIPLE CHOICES)
        Response response=given()
                .accept(ContentType.JSON)
                .when()
                .patch("http://httpbin.org/status/300");
        /*
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 0
 content-type: text/html; charset=utf-8
 date: Sun, 24 Jul 2022 14:06:50 GMT
 server: gunicorn/19.9.0
         */
        response
                .then()
                .assertThat()
                .statusCode(300)
                .contentType("text/html; charset=utf-8")
                .header("Content-Length","0")
                .header("Connection", "keep-alive")
                .header("Server", "gunicorn/19.9.0")
                .header("Access-Control-Allow-Origin" ,"*")
                .header("Access-Control-Allow-Credentials", "true");
    }
    @Test
    public void statusPatch300HTTP() throws IOException {
        URL url = new URL("http://httpbin.org/status/300");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PATCH");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");
        http.setRequestProperty("Content-Type", "");
        http.setRequestProperty("Content-Length", "0");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }

    @Test
    public void statusPatch200() {
        Response
                response=given()
                .contentType(ContentType.JSON)
                .when()
                .patch("http://httpbin.org/status/200");
        /*
200 (OK)
        Date: Sun, 24 Jul 2022 13:27:11 GMT
Content-Type: text/html; charset=utf-8
Content-Length: 0
Connection: keep-alive
Server: gunicorn/19.9.0
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true
         */
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html; charset=utf-8")
                .header("Content-Length","0")
                .header("Connection", "keep-alive")
                .header("Server", "gunicorn/19.9.0")
                .header("Access-Control-Allow-Origin" ,"*")
                .header("Access-Control-Allow-Credentials", "true");
    }
    @Test
    public void statusPatch200HTTP() throws IOException {
        URL url = new URL("http://httpbin.org/status/200");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PATCH");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");
        http.setRequestProperty("Content-Type", "");
        http.setRequestProperty("Content-Length", "0");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }


    @Test
    public void statusPatch400() {
        Response
                response=given()
                .header("Authorization", "Basic ZWNlOjEyMzQ1Njc=")
                .accept(ContentType.JSON)
                .when()
                .patch("http://httpbin.org/status/400");
        /* 400 (BAD REQUEST)
        Date: Sun, 24 Jul 2022 13:29:36 GMT
Content-Type: text/html; charset=utf-8
Content-Length: 0
Connection: keep-alive
Server: gunicorn/19.9.0
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true
         */
        response
                .then()
                .assertThat()
                .statusCode(400)
                .contentType("text/html; charset=utf-8")
                .header("Content-Length","0")
                .header("Connection", "keep-alive")
                .header("Server", "gunicorn/19.9.0")
                .header("Access-Control-Allow-Origin" ,"*")
                .header("Access-Control-Allow-Credentials", "true");
    }
    @Test
    public void statusPatch400HTTP() throws IOException {
        URL url = new URL("http://httpbin.org/status/400");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PATCH");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");
        http.setRequestProperty("Content-Type", "");
        http.setRequestProperty("Content-Length", "0");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void statusPatch500() {
        Response
                response=given()
                .accept(ContentType.JSON)
                .when()
                .patch("http://httpbin.org/status/500");
              /* HTTP/1.1 500 INTERNAL SERVER ERROR
Date: Sun, 24 Jul 2022 14:23:17 GMT
Content-Type: text/html; charset=utf-8
Content-Length: 0
Connection: keep-alive
Server: gunicorn/19.9.0
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true
         */
        response
                .then()
                .assertThat()
                .statusCode(500)
                .contentType("text/html; charset=utf-8")
                .header("Content-Length","0")
                .header("Connection", "keep-alive")
                .header("Server", "gunicorn/19.9.0")
                .header("Access-Control-Allow-Origin" ,"*")
                .header("Access-Control-Allow-Credentials", "true");
    }
    @Test
    public void statusPatch500HTTP() throws IOException {
        /*  500 (INTERNAL SERVER ERROR)
        Date: Sun, 24 Jul 2022 13:30:43 GMT
Content-Type: text/html; charset=utf-8
Content-Length: 0
Connection: keep-alive
Server: gunicorn/19.9.0
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true
         */
        URL url = new URL("http://httpbin.org/status/500");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PATCH");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");
        http.setRequestProperty("Content-Type", "");
        http.setRequestProperty("Content-Length", "0");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
