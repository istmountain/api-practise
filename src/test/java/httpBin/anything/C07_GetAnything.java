package httpBin.anything;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C07_GetAnything extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/anything/{anything}" -H "accept: application/json"
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
    "Referer": "http://httpbin.org/",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.47",
    "X-Amzn-Trace-Id": "Root=1-62f811ac-790234bf4f0f966777c54279"
  },
  "json": null,
  "method": "GET",
  "origin": "176.90.132.203",
  "url": "http://httpbin.org/anything/{anything}"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 632
 content-type: application/json
 date: Sat, 13 Aug 2022 21:03:40 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Anything passed in request
     */
    @Test
    public void http() throws IOException {
        /*
    Curl
curl -X GET "http://httpbin.org/anything/{anything}" -H "accept: application/json"
Request URL
http://httpbin.org/anything/{anything}
Server response
Code	Details
200
         */
        URL url = new URL("http://httpbin.org/anything/image");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + "http://httpbin.org/anything/image" + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
           /*
            Curl
curl -X DELETE "http://httpbin.org/anything/{anything}" -H "accept: application/json"
Request URL
http://httpbin.org/anything/{anything}
Server response
Code	Details
200
         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/anything/image").setAccept("application/json").build();
        Response response=given()
                .spec(req)
                .when()
                .delete();
        response.prettyPrint();
        //expected body
        JSONObject headers=new JSONObject();
        JSONObject expected=new JSONObject();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        expected.put("origin","176.90.132.203");
        expected.put("url", "http://httpbin.org/anything/image");
        expected.put("headers",headers);
        expected.put("json", "null");
        expected.put("method", "DELETE");
        // save response
        JsonPath actual=response.jsonPath();
        //assertions
        assertEquals(expected.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(expected.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(expected.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(expected.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(expected.get("origin"),actual.get("origin"));
        assertEquals(expected.get("url"),actual.get("url"));
        assertEquals(expected.get("method"),actual.get("method"));



    }
    @Test
    public void res() {
           /*
            Curl
curl -X DELETE "http://httpbin.org/anything/{anything}" -H "accept: application/json"
Request URL
http://httpbin.org/anything/{anything}
Server response
Code	Details
200
         */
        //http://httpbin.org/anything
        specHttpbin.pathParams("pp1","anything","pp2","image");
        Response response=given()
                .spec(specHttpbin)
                .accept("application/json")
                .when()
                .delete("/{pp1}/{pp2}");
        response.prettyPrint();
        //expected body
        JSONObject headers=new JSONObject();
        JSONObject expected=new JSONObject();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        expected.put("origin","176.90.132.203");
        expected.put("url", "http://httpbin.org/anything/image");
        expected.put("headers",headers);
        expected.put("json", "null");
        expected.put("method", "DELETE");
        // save response
        JsonPath actual=response.jsonPath();
        //assertions
        assertEquals(expected.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(expected.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(expected.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(expected.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(expected.get("origin"),actual.get("origin"));
        assertEquals(expected.get("url"),actual.get("url"));
        assertEquals(expected.get("method"),actual.get("method"));

    }
}
