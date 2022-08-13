package httpBin.anything;

import baseUrls.BaseHttpBin;

public class C08_PatchAnything extends BaseHttpBin {
    /*
    Curl
curl -X PATCH "http://httpbin.org/anything/{anything}" -H "accept: application/json"
Request URL
http://httpbin.org/anything/{anything}
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
    "Host": "httpbin.org",
    "Origin": "http://httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.47",
    "X-Amzn-Trace-Id": "Root=1-62f812b2-141446054bd8f7210780d867"
  },
  "json": null,
  "method": "PATCH",
  "origin": "176.90.132.203",
  "url": "http://httpbin.org/anything/{anything}"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: http://httpbin.org
 connection: keep-alive
 content-length: 671
 content-type: application/json
 date: Sat, 13 Aug 2022 21:08:02 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Anything passed in request

     */
}
