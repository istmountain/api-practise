package httpBin.redirects;

import baseUrls.BaseHttpBin;
import org.junit.Test;

public class C07_REdirect_n_Times extends BaseHttpBin {
    /*
GET
/redirect/{n}
302 Redirects n times.

Undocumented
Response body
Download
{
  "args": {},
  "headers": {
    "Accept": "text/html",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76; freeform=; ece=alper",
    "Host": "httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62",
    "X-Amzn-Trace-Id": "Root=1-62eeb611-30734c0029b9614f36055a8b"
  },
  "origin": "176.42.163.196",
  "url": "http://httpbin.org/get"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 647
 content-type: application/json
 date: Sat, 06 Aug 2022 18:42:25 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void http() {
        /*
            Curl
curl -X GET "http://httpbin.org/redirect/4" -H "accept: text/html"
Request URL
http://httpbin.org/redirect/4
Server response
Code	Details
200
         */

    }
    @Test
    public void req() {
               /*
            Curl
curl -X GET "http://httpbin.org/redirect/4" -H "accept: text/html"
Request URL
http://httpbin.org/redirect/4
Server response
Code	Details
200
         */
    }
    @Test
    public void res() {
               /*
            Curl
curl -X GET "http://httpbin.org/redirect/4" -H "accept: text/html"
Request URL
http://httpbin.org/redirect/4
Server response
Code	Details
200
         */
    }

}
