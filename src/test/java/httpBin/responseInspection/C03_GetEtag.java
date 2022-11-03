package httpBin.responseInspection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C03_GetEtag {
    /*
    Curl
curl -X GET "http://httpbin.org/etag/{etag}" -H "accept: application/json" -H "If-None-Match: " -H "If-Match: 1027ef932062b9ecae44b3557b950c76"
Request URL
http://httpbin.org/etag/{etag}
Server response
Code	Details
412	Error: PRECONDITION FAILED
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 0
 date: Tue, 26 Jul 2022 15:09:03 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Normal response
412
match
     */

    @Test
    public void req() {
        Map<String,String>  headers=new HashMap<>();
        headers.put("If-None-Match","");
        headers.put("If-Match", "1027ef932062b9ecae44b3557b950c76");

        RequestSpecification req=new RequestSpecBuilder()
                .addHeaders(headers)
                .setBaseUri("http://httpbin.org/etag/1027ef932062b9ecae44b3557b950c76")
                .build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void resNonMatch() {
        Map<String,String>  headers=new HashMap<>();
        headers.put("If-None-Match","1027ef932062b9ecae44b3557b950c76");
        headers.put("If-Match", "");
        Response response=given()
                .accept(ContentType.JSON)
                .headers(headers)
                .when()
                .get("http://httpbin.org/etag/1027ef932062b9ecae44b3557b950c76");
        System.out.println(response.getStatusCode());
        response.then()
                .assertThat()
                .statusCode(304);

    }
    @Test
    public void resMatch() {
        Map<String,String>  headers=new HashMap<>();
        headers.put("If-None-Match","");
        headers.put("If-Match", "1027ef932062b9ecae44b3557b950c76");
        Response response=given()
                .accept(ContentType.JSON)
                .headers(headers)
                .when()
                .get("http://httpbin.org/etag/1027ef932062b9ecae44b3557b950c76");
        System.out.println(response.getStatusCode());
        response.then()
                .assertThat()
                .statusCode(200);

    }
}
