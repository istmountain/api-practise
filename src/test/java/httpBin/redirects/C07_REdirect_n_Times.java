package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
    public void http() throws IOException {
        /*
            Curl
curl -X GET "http://httpbin.org/redirect/4" -H "accept: text/html"
Request URL
http://httpbin.org/redirect/4
Server response
Code	Details
200
         */
        URL url = new URL("http://httpbin.org/redirect-to");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "text/html");
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "http://www.amazon.com&status_code=10%22";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

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
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/redirect/4").setAccept("text/html").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        System.out.println(response.statusCode());
        response.prettyPrint();
        /*
        {
    "args": {

    },
    "headers": {
        "Accept": "text/html",
        "Accept-Encoding": "gz1p,deflate",
        "Host": "httpbin.org",
        "User-Agent": "Apache-HttpClient/4.5.3 (Java/18.0.1.1)",
        "X-Amzn-Trace-Id": "Root=1-62f2bc24-29b1b0567f0910025f3102cd"
    },
    "origin": "176.42.164.88",
    "url": "http://httpbin.org/get"
}
         */
        //expected body
        JSONObject headers=new JSONObject();
        JSONObject expected=new JSONObject();
        headers.put("Accept", "text/html");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        expected.put("origin","176.42.164.88");
        expected.put("url", "http://httpbin.org/get");
        expected.put("headers",headers);
        // save response
        JsonPath actual=response.jsonPath();
        //assertions
        assertEquals(expected.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(expected.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(expected.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(expected.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(expected.get("origin"),actual.get("origin"));
        assertEquals(expected.get("url"),actual.get("url"));


        response
                .then()
                .assertThat()
                .statusCode(200);
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
