package httpBin.dynamicData;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C04_DelayGet extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/delay/3" -H "accept: application/json"
Request URL
http://httpbin.org/delay/3
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/delay/2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + "http://httpbin.org/delay/2" + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/delay/2").setContentType("application/json").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        System.out.println(response.getHeaders());
        //exp data and headers
        /*
            "origin": "88.236.86.164",
    "url": "http://httpbin.org/delay/2"
}
Date=Fri, 29 Jul 2022 12:17:38 GMT
Content-Type=application/json
Content-Length=428
Connection=keep-alive
Server=gunicorn/19.9.0
Access-Control-Allow-Origin=*
Access-Control-Allow-Credentials=true
         */
        JSONObject exp=new JSONObject();
        exp.put("origin", "88.236.86.164");
        exp.put("url", "http://httpbin.org/delay/2");
        JSONObject headers=new JSONObject();
        headers.put("Content-Type","application/json");
        headers.put("Content-Length","428");
        headers.put("Connection","keep-alive");
        headers.put("Server","gunicorn/19.9.0");
        headers.put("Access-Control-Allow-Origin","*");
        headers.put("Access-Control-Allow-Credentials","true");
        //save response
        JsonPath act=response.jsonPath();
        //Assert
        assertEquals(exp.get("origin"),act.get("origin"));
        assertEquals(exp.get("url"),act.get("url"));
        assertEquals(headers.get("Content-Type"),response.getHeader("Content-Type"));
        assertEquals(headers.get("Content-Length"),response.getHeader("Content-Length"));
        assertEquals(headers.get("Connection"),response.getHeader("Connection"));
        assertEquals(headers.get("Server"),response.getHeader("Server"));
        assertEquals(headers.get("Access-Control-Allow-Origin"),response.getHeader("Access-Control-Allow-Origin"));
        assertEquals(headers.get("Access-Control-Allow-Credentials"),response.getHeader("Access-Control-Allow-Credentials"));

    }
    @Test
    public void res() {
        specHttpbin.pathParams("pp1","delay","pp2",2);
        Response response=given()
                .spec(specHttpbin)
                .accept("application/json")
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        System.out.println(response.getHeaders());
        //exp data and headers
        /*
            "origin": "88.236.86.164",
    "url": "http://httpbin.org/delay/2"
}
Date=Fri, 29 Jul 2022 12:17:38 GMT
Content-Type=application/json
Content-Length=428
Connection=keep-alive
Server=gunicorn/19.9.0
Access-Control-Allow-Origin=*
Access-Control-Allow-Credentials=true
         */
        JSONObject exp=new JSONObject();
        exp.put("origin", "88.236.86.164");
        exp.put("url", "http://httpbin.org/delay/2");
        JSONObject headers=new JSONObject();
        headers.put("Content-Type","application/json");
        headers.put("Content-Length","385");
        headers.put("Connection","keep-alive");
        headers.put("Server","gunicorn/19.9.0");
        headers.put("Access-Control-Allow-Origin","*");
        headers.put("Access-Control-Allow-Credentials","true");
        //save response
        JsonPath act=response.jsonPath();
        //Assert
        assertEquals(exp.get("origin"),act.get("origin"));
        assertEquals(exp.get("url"),act.get("url"));
        assertEquals(headers.get("Content-Type"),response.getHeader("Content-Type"));
        assertEquals(headers.get("Content-Length"),response.getHeader("Content-Length"));
        assertEquals(headers.get("Connection"),response.getHeader("Connection"));
        assertEquals(headers.get("Server"),response.getHeader("Server"));
        assertEquals(headers.get("Access-Control-Allow-Origin"),response.getHeader("Access-Control-Allow-Origin"));
        assertEquals(headers.get("Access-Control-Allow-Credentials"),response.getHeader("Access-Control-Allow-Credentials"));

    }
}
