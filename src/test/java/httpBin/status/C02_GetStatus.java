package httpBin.status;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class C02_GetStatus {
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

    @Test
    public void status300() {
        //300 (MULTIPLE CHOICES)
        /*
        Date: Sun, 24 Jul 2022 13:23:47 GMT
Content-Type: text/html; charset=utf-8
Content-Length: 0
Connection: keep-alive
Server: gunicorn/19.9.0
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true
         */
    }
    @Test
    public void status300HTTP() throws IOException {
        //300 (MULTIPLE CHOICES)
        URL url = new URL("http://httpbin.org/status/300");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void status200() {
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
    }
    @Test
    public void status200HTTP() throws IOException {
        URL url = new URL("http://httpbin.org/status/200");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }


    @Test
    public void status400() {
        /* 400 (BAD REQUEST)
        Date: Sun, 24 Jul 2022 13:29:36 GMT
Content-Type: text/html; charset=utf-8
Content-Length: 0
Connection: keep-alive
Server: gunicorn/19.9.0
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true
         */
    }
    @Test
    public void status400HTTP() throws IOException {
        URL url = new URL("http://httpbin.org/status/400");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void status500() {
    }
    @Test
    public void status500HTTP() throws IOException {
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
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
