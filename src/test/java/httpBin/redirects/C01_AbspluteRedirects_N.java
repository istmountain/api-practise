package httpBin.redirects;

import baseUrls.BaseHttpBin;

public class C01_AbspluteRedirects_N extends BaseHttpBin {

    /*
    Curl
curl -X GET "http://httpbin.org/absolute-redirect/3" -H "accept: text/html"
Request URL
http://httpbin.org/absolute-redirect/3
Server response
Code	Details
200
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
    "X-Amzn-Trace-Id": "Root=1-62e67332-0a20a2bb6a8897a31ca210f7"
  },
  "origin": "88.236.86.164",
  "url": "http://httpbin.org/get"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 646
 content-type: application/json
 date: Sun, 31 Jul 2022 12:18:58 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
302
A redirection.
     */

}
