package httpBin.responseInspection;

import org.junit.Test;

public class C02_CacheControlIntSeconds {
    @Test
    public void res() {
        /*
        Curl
curl -X GET "http://httpbin.org/cache/3" -H "accept: application/json"
Request URL
http://httpbin.org/cache/3
Server response
Code	Details
200	
Response body
Download
{
  "args": {},
  "headers": {
    "Accept": "application/json",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76",
    "Host": "httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62",
    "X-Amzn-Trace-Id": "Root=1-62dfcb92-4386ac8816a4d75e2c5d07cd"
  },
  "origin": "88.236.86.164",
  "url": "http://httpbin.org/cache/3"
}
Response headers/7exp heeaders
 access-control-allow-credentials: true 
 access-control-allow-origin: * 
 cache-control: public, max-age=3 
 connection: keep-alive 
 content-length: 635 
 content-type: application/json 
 date: Tue, 26 Jul 2022 11:10:10 GMT 
 server: gunicorn/19.9.0 
         */
    }

    @Test
    public void req() {
    }
}
