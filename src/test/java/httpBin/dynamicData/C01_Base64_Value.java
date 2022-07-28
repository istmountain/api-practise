package httpBin.dynamicData;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C01_Base64_Value extends BaseHttpBin {
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/base64/SFRUUEJJTiBpcyBhd2Vzb21l").setContentType("text/html").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        //expected
       HTML exp=response.as(HTML.class);
        System.out.println(exp);
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html")
                .body("HTTPBIN is awesome",Matchers.equalTo(exp));
    }
    @Test
    public void res() {
        specHttpbin.pathParams("pp1","base64","pp2","SFRUUEJJTiBpcyBhd2Vzb21l");
        Response response=given()
                .spec(specHttpbin)
                .accept("text/html")
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        //expected

        //assert
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html");

    }
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/base64/SFRUUEJJTiBpcyBhd2Vzb21l");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    /*
    Curl
curl -X GET "http://httpbin.org/base64/SFRUUEJJTiBpcyBhd2Vzb21l" -H "accept: text/html"
Request URL
http://httpbin.org/base64/SFRUUEJJTiBpcyBhd2Vzb21l
Server response
Code	Details
200
Response body
Download
HTTPBIN is awesome
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 18
 content-type: text/html; charset=utf-8
 date: Thu, 28 Jul 2022 11:14:06 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
Decoded base64 content.
     */
}
