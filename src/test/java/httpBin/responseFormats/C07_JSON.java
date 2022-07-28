package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C07_JSON extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/json" -H "accept: application/json"
Request URL
http://httpbin.org/json
Server response
Code	Details
200
Response body
Download

Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 429
 content-type: application/json
 date: Wed, 27 Jul 2022 21:27:16 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/json");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/json").setAccept(ContentType.JSON).build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        //exp data
        JSONObject exp=new JSONObject();
        JSONObject slideShow=new JSONObject();
        JSONObject inner=new JSONObject();
        JSONObject ininner=new JSONObject();
        JSONArray slides=new JSONArray();
        JSONArray items=new JSONArray();
        HashMap<String,String> map=new HashMap<>();
        map.put(     "Why <em>WonderWidgets</em> are great",
                "Who <em>buys</em> WonderWidgets");
        items.put(0,map);
        ininner.put("items",items);
        ininner.put("title", "Overview");
        ininner.put("type", "all");
        inner.put("title", "Wake up to WonderWidgets!");
        inner.put("type", "all");
        slides.put(0,inner);
        slides.put(1,ininner);
        slideShow.put("author", "Yours Truly");
        slideShow.put("date","date of publication");
        slideShow.put("slides",slides);
        slideShow.put("title", "Sample Slide Show");
        exp.put("slideshow",slideShow);
        /*
        {
    "slideshow": {
        "author": "Yours Truly",
        "date": "date of publication",
        "slides": [
            {
                "title": "Wake up to WonderWidgets!",
                "type": "all"
            },
            {
                "items": [
                    "Why <em>WonderWidgets</em> are great",
                    "Who <em>buys</em> WonderWidgets"
                ],
                "title": "Overview",
                "type": "all"
            }
        ],
        "title": "Sample Slide Show"
    }
}
         */
        //
        JsonPath act=response.jsonPath();

        response
                .then()
                .assertThat()
                .statusCode(200);
       assertEquals(exp.getJSONObject("slideshow").get("author"),act.get("slideshow.author"));
        assertEquals(exp.getJSONObject("slideshow").get("date"),act.get("slideshow.date"));
        assertEquals(exp.getJSONObject("slideshow").get("title"),act.get("slideshow.title"));
    }
    @Test
    public void res() {
    }
}
