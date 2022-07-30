package httpBin.cookies;

import baseUrls.BaseHttpBin;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class C04_Cookie_Set_Name_Value extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
200
Response body
Download
{
  "cookies": {
    "ece": "alper",
    "fake": "fake_value",
    "freeform": "",
    "last_nonce": "1027ef932062b9ecae44b3557b950c76",
    "stale_after": "never"
  }
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 174
 content-type: application/json
 date: Sat, 30 Jul 2022 14:07:09 GMT
 server: gunicorn/19.9.0
Responses
Code
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/cookies/set/ece/alper");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        /*
            Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
         */
    }
    @Test
    public void req() {

        /*
            Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
         */
    }
    @Test
    public void res() {

        /*
            Curl
curl -X GET "http://httpbin.org/cookies/set/ece/alper" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set/ece/alper
Server response
Code	Details
         */
    }
}
