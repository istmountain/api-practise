package httpBin.dynamicData;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C03_Delay extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/delay/2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/delay/2").setAccept("application/json").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
        JSONObject exp=new JSONObject();
        JSONObject args=new JSONObject();
        JSONObject files=new JSONObject();
        JSONObject form=new JSONObject();
        JSONObject headers=new JSONObject();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Host", "httpbin.org");
        headers.put("Origin", "http://httpbin.org");
        headers.put("Referer", "http://httpbin.org/");
        headers.put("User-Agent","Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        exp.put("args",args);
        exp.put("data", "");
        exp.put("files",files);
        exp.put("form", form);
        exp.put("headers",headers);
        exp.put("origin", "88.236.86.164");
        exp.put("url","http://httpbin.org/delay/11");
        //save Response



        /*
        {
  "args": {},
  "data": "",
  "files": {},
  "form": {},
   "headers": {
        "Accept": "application/json",
        "Accept-Encoding": "gz1p,deflate",
        "Host": "httpbin.org",
        "User-Agent": "Apache-HttpClient/4.5.3 (Java/18.0.1.1)",
        "X-Amzn-Trace-Id": "Root=1-62e2d421-594b678410fbac2a76a4e717"
    },
    "origin": "88.236.86.164",
    "url": "http://httpbin.org/delay/11"
}
         */

    }
    @Test
    public void res() {
    }
    /*
    Curl
curl -X DELETE "http://httpbin.org/delay/3" -H "accept: application/json"
Request URL
http://httpbin.org/delay/3
Server response
Code	Details
200
Response body
Download
,
  "origin": "88.236.86.164",
  "url": "http://httpbin.org/delay/3"
}
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: http://httpbin.org
 connection: keep-alive
 content-length: 718
 content-type: application/json
 date: Thu, 28 Jul 2022 11:58:35 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
A delayed response.

     */
}
