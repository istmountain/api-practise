package httpBin.headers;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class C01_ReturningIncomingHTTP {
    /*
HttpResponseRecBin
headers
{6}
Accept
:

    Accept-Encoding
:
    deflate, gzip
            Authorization
:
    Basic ZWNlOjEyMzQ1Njc=
            Host
:
    httpbin.org
    User-Agent
:
    Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62
    X-Amzn-Trace-Id
:
    Root=1-62dec28e-251176db06d0b04f53caf710
     */
    @Test
    public void headerHttp() throws IOException {
        URL url = new URL("http://httpbin.org/headers");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }

    @Test
    public void name() {
              /*
        {
  "headers": {
    "Accept": "application/json",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
    "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76",
    "Host": "httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62",
    "X-Amzn-Trace-Id": "Root=1-62dec27a-5189a4172963a80357c0a70f"
  }
}
         */
    }
}
