package httpBin.anything;

import baseUrls.BaseHttpBin;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class C04_Postanything extends BaseHttpBin {
    /*
Curl
curl -X POST "http://httpbin.org/anything" -H "accept: application/json"
Request URL
http://httpbin.org/anything
Server response
Code	Details
200
Response body
Download
{
  "args": {},
  "data": "",
  "files": {},
  "form": {},
  "headers": {
    "Accept": "application/json",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Content-Length": "0",
    "Host": "httpbin.org",
    "Origin": "http://httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.47",
    "X-Amzn-Trace-Id": "Root=1-62f2d047-37b10cee19bb3ab572ee87aa"
  },
  "json": null,
  "method": "POST",
  "origin": "176.42.164.88",
  "url": "http://httpbin.org/anything"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: http://httpbin.org
 connection: keep-alive
 content-length: 686
 content-type: application/json
 date: Tue, 09 Aug 2022 21:23:19 GMT
 server: gunicorn/19.9.0
     */
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/anything");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + "http://httpbin.org/anything" + http.getResponseMessage());
        http.disconnect();
    }
}
