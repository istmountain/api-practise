package httpBin.auth;

import baseUrls.BaseHttpBin;
import org.junit.Test;

public class C01_AuthGet extends BaseHttpBin {
    @Test
    public void name() {
        /*
        Curl
curl -X GET "http://httpbin.org/basic-auth/ece/1234567" -H "accept: application/json"
Request URL
http://httpbin.org/basic-auth/ece/1234567
Server response
Code	Details
200
Response body
Download
{
  "authenticated": true,
  "user": "ece"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 46
 content-type: application/json
 date: Sat, 23 Jul 2022 09:24:01 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Sucessful authentication.
         */
    }
}
