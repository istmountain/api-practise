package httpBin.responseInspection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
Response headers//exp heeaders
 access-control-allow-credentials: true 
 access-control-allow-origin: * 
 cache-control: public, max-age=3 
 connection: keep-alive 
 content-length: 635 
 content-type: application/json 
 date: Tue, 26 Jul 2022 11:10:10 GMT 
 server: gunicorn/19.9.0 
         */
        /*
        "headers": {
        "Accept": "application/json, application/javascript, text/javascript, text/json",
        "Accept-Encoding": "gz1p,deflate",
        "Host": "httpbin.org",
        "User-Agent": "Apache-HttpClient/4.5.3 (Java/18.0.1.1)",
        "X-Amzn-Trace-Id": "Root=1-62dffddc-26e6026c7e1d75930d834c09"
    }
         */
        Response response=given()
                .accept(ContentType.JSON)
                .when()
                .get("http://httpbin.org/cache/3");
        response.prettyPrint();
        //exp
        JSONObject exp=new JSONObject();
        exp.put( "Accept", "application/json, application/javascript, text/javascript, text/json");
        exp.put("Accept-Encoding", "gz1p,deflate");
        exp.put("Host", "httpbin.org");
        exp.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        exp.put("X-Amzn-Trace-Id", "Root=1-62dffddc-26e6026c7e1d75930d834c09");
        //Response header Assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        assertEquals(exp.get("Accept"),response.jsonPath().get("headers.Accept"));
        assertEquals(exp.get("Accept-Encoding"),response.jsonPath().get("headers.Accept-Encoding"));
        assertEquals(exp.get("Host"),response.jsonPath().get("headers.Host"));
        assertEquals(exp.get("User-Agent"),response.jsonPath().get("headers.User-Agent"));
      //  assertEquals(exp.get("X-Amzn-Trace-Id"),response.jsonPath().get("headers.X-Amzn-Trace-Id"));
    }

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/cache/3").build();
        Response response=given()
                .spec(req)
                .contentType(ContentType.JSON)
                .when()
                .get();
        //exp
        JSONObject exp=new JSONObject();
        exp.put( "Accept", "application/json, application/javascript, text/javascript, text/json");
        exp.put("Accept-Encoding", "gz1p,deflate");
        exp.put("Host", "httpbin.org");
        exp.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        exp.put("X-Amzn-Trace-Id", "Root=1-62dffddc-26e6026c7e1d75930d834c09");
        //Response header Assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        assertEquals(exp.get("Accept"),response.jsonPath().get("headers.Accept"));
        assertEquals(exp.get("Accept-Encoding"),response.jsonPath().get("headers.Accept-Encoding"));
        assertEquals(exp.get("Host"),response.jsonPath().get("headers.Host"));
        assertEquals(exp.get("User-Agent"),response.jsonPath().get("headers.User-Agent"));
    }
}
