package httpBin.cookies;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_CookiesSet extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/cookies/set?freeform=" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set?freeform=
Server response
Code	Details
200
Response body
Download
{
  "cookies": {
    "fake": "fake_value",
    "freeform": "",
    "last_nonce": "1027ef932062b9ecae44b3557b950c76",
    "stale_after": "never"
  }
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 153
 content-type: application/json
 date: Sat, 30 Jul 2022 13:46:54 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void http() {


        /*
            Curl
curl -X GET "http://httpbin.org/cookies/set?freeform=" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set?freeform=
         */
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/cookies/set?freeform=").setContentType("text/plain").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);
                /*
            Curl
curl -X GET "http://httpbin.org/cookies/set?freeform=" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set?freeform=
         */
    }
    @Test
    public void res() {

                /*
            Curl
curl -X GET "http://httpbin.org/cookies/set?freeform=" -H "accept: text/plain"
Request URL
http://httpbin.org/cookies/set?freeform=
         */
    }
}
