package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01_AbspluteRedirects_N extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/image/jpeg");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/absolute-redirect/3").setContentType("accept: text/html").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        /*
        {
    "args": {

    },
    "headers": {
        "Accept": "",
        "Accept-Encoding": "gz1p,deflate",
                "Content-Type": "accept: text/html; charset=ISO-8859-1",
                "Host": "httpbin.org",
                "User-Agent": "Apache-HttpClient/4.5.3 (Java/18.0.1.1)",
                "X-Amzn-Trace-Id": "Root=1-62e7b5e3-1d0a987a7ba2514a03841ae2"
    },
            "origin": "88.236.86.164",
            "url": "http://httpbin.org/get"
}

         */
        //exp body
        JSONObject exp=new JSONObject();
        JSONObject args=new JSONObject();
        JSONObject headers=new JSONObject();
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Content-Type", "accept: text/html; charset=ISO-8859-1");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        exp.put("headers",headers);
        exp.put("origin", "88.236.86.164");
        exp.put("url", "http://httpbin.org/get");
        exp.put("args",args);
        //save response
        JsonPath act=response.jsonPath();
        //Asssertions
       response.then()
               .assertThat()
               .statusCode(200);
        assertEquals(exp.getJSONObject("args"),act.get("args"));
    }
    @Test
    public void res() {
    }
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
