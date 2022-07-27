package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Deflate extends BaseHttpBin {
    /*
            Curl
        curl -X GET "http://httpbin.org/deflate" -H "accept: application/json"
        Request URL
        http://httpbin.org/deflate
        Server response
        Code	Details
        200
        Response body
        Download
        {
          "deflated": true,
          "headers": {
            "Accept": "application/json",
            "Accept-Encoding": "gzip, deflate",
            "Accept-Language": "tr,en;q=0.9,en-GB;q=0.8,en-US;q=0.7",
            "Cookie": "stale_after=never; fake=fake_value; last_nonce=1027ef932062b9ecae44b3557b950c76",
            "Host": "httpbin.org",
            "Referer": "http://httpbin.org/",
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62",
            "X-Amzn-Trace-Id": "Root=1-62e0f07b-6af2aa8d5375d72358b665e5"
          },
          "method": "GET",
          "origin": "88.236.86.164"
        }
        Response headers
         access-control-allow-credentials: true
         access-control-allow-origin: *
         connection: keep-alive
         content-encoding: deflate
         content-length: 418
         content-type: application/json
         date: Wed, 27 Jul 2022 07:59:55 GMT
         server: gunicorn/19.9.0
        Responses
        Code
     */
    @Test
    public void req() {
        //request body olustur
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/deflate").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        //exp data
        JSONObject exp=new JSONObject();
        /*
           },
          "method": "GET",
          "origin": "88.236.86.164"
        }
         */
        exp.put("method", "GET");
        exp.put("origin", "88.236.86.164");
        //Response save
        JsonPath act=response.jsonPath();
        //Assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("method"),act.get("method"));
        Assert.assertEquals(exp.get("origin"),act.get("origin"));

    }
    @Test
    public void http() {
    }
    @Test
    public void res() {
    }
}
