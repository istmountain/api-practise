package httpBin.responseInspection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01_GetCache {
    //http://httpbin.org/cache
    /*
    Bu IE9 tarayıcısından geldi. Aynı şeyi en son Firefox ve Chrome tarayıcılarından da alıyorum. Buradan sunucu "If-Modified-Since"
    üstbilgisini arayabilir ve kaynağın değiştirilmediğini belirlerse 304 Not Modified yanıtı döndürür, aksi takdirde kaynak gösterimini 200 OK yanıtıyla döndürür.

    böylece HTTP spesifikasyonuna göre, "Expires" ve/veya "Cache-Control" başlıklarını "Last-Modified"
    başlığıyla birlikte kullanarak önbelleğe almayı kontrol edebilirsiniz. Bu, tarayıcı önbelleğinin "If-Modified-Since" üstbilgisini içerdiği için "koşullu GET"
    isteği adı verilen bir işlemi gerçekleştirmesine neden olur.
    If-Modified-Since bir HTTP koşullu istek üstbilgisidir ve burada son değişiklik tarihi koşuldur.
         */
    /*
   Curl
curl -X GET "http://httpbin.org/cache" -H "accept: application/json"
Request URL
http://httpbin.org/cache
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
    "X-Amzn-Trace-Id": "Root=1-62deea7f-2c0b4a0d5737ff3628c2340b"
  },
  "origin": "88.236.86.164",
  "url": "http://httpbin.org/cache"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 content-length: 633
 content-type: application/json
 date: Tue, 26 Jul 2022 09:32:38 GMT
 etag: 8c29f4b6349c4e44a34f75d812300ed7
 last-modified: Mon, 25 Jul 2022 19:09:51 GMT
 server: gunicorn/19.9.0
     */

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().addHeader("last-modified"," Mon, 25 Jul 2022 19:09:51 GMT")
                .setBaseUri("http://httpbin.org/cache").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        //exp
        JSONObject exp=new JSONObject();
        exp.put( "origin", "88.236.86.164");
        exp.put(    "url", "http://httpbin.org/cache");
        //act
        JsonPath act=response.jsonPath();
        //Assert
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
       assertEquals(exp.get("origin"),act.get("origin"));
        assertEquals(exp.get("url"),act.get("url"));


    }
    @Test
    public void res() {
        Response response=given()
                .header("Last-Modified","Mon, 25 Jul 2022 19:09:51 GMT")
                .accept(ContentType.JSON)
                .when()
                .get("http://httpbin.org/cache");
        response.prettyPrint();
        //exp
        JSONObject exp=new JSONObject();
        exp.put( "origin", "88.236.86.164");
        exp.put(    "url", "http://httpbin.org/cache");
        //act
        JsonPath act=response.jsonPath();
        //Assert
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
        assertEquals(exp.get("origin"),act.get("origin"));
        assertEquals(exp.get("url"),act.get("url"));

    }
}
