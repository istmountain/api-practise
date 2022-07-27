package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import org.junit.Test;

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
{
  "gzipped": true,
  "headers": {
    "Accept": "application/json",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76",
    "Host": "httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62",
    "X-Amzn-Trace-Id": "Root=1-62e18b7a-62ad638465451615624660e5"
  },
  "method": "GET",
  "origin": "178.245.94.65"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-encoding: gzip
 content-length: 430
 content-type: application/json
 date: Wed, 27 Jul 2022 19:01:14 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void http() {
    }
    @Test
    public void req() {
    }
    @Test
    public void res() {
    }
}
