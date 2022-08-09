package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C08_RedirectsGet extends BaseHttpBin {
    /*
Curl
curl -X GET "http://httpbin.org/relative-redirect/4" -H "accept: text/html"
Request URL
http://httpbin.org/relative-redirect/4
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
    "Host": "httpbin.org",
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.47",
    "X-Amzn-Trace-Id": "Root=1-62f2c351-7b00d31c777cd0a444d1a031"
  },
  "origin": "176.42.164.88",
  "url": "http://httpbin.org/get"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 525
 content-type: application/json
 date: Tue, 09 Aug 2022 20:28:01 GMT
 server: gunicorn/19.9.0     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/relative-redirect/4");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();


    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/relative-redirect/4")
                .setAccept("text/html").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
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
        //http://httpbin.org/relative-redirect/4
        specHttpbin.pathParams("pp1","relative-redirect","pp2",4);
        Response response=given()
                .spec(specHttpbin)
                .accept("text/html")
                .when()
                .get("/{pp1}/{pp2}");
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
}
