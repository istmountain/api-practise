package httpBin.responseInspection;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class C05_PostFreeFormQuery {
    /*Curl
        curl -X POST "http://httpbin.org/response-headers?freeform=ece" -H "accept: application/json"
        Request URL
        http://httpbin.org/response-headers?freeform=ece
        Server response
        Code	Details
        200
        Response body
        Download
        {
          "Content-Length": "90",
          "Content-Type": "application/json",
          "freeform": "ece"
        }
        Response headers
         access-control-allow-credentials: true
         access-control-allow-origin: http://httpbin.org
         connection: keep-alive
         content-length: 90
         content-type: application/json
         date: Tue, 26 Jul 2022 15:45:47 GMT
         freeform: ece
         server: gunicorn/19.9.0
        Responses
        Code	Description
        200
        Response headers
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/response-headers?freeform=ece");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }
    @Test
    public void req() {
    }
    @Test
    public void res() {
    }
}
