package httpBin.responseInspection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C05_PostFreeFormQuery {
    /*Curl
        curl -X POST "http://httpbin.org/response-headers?freeform=ece" -H "accept: application/json"
        Request URL
        http://httpbin.org/response-headers?freeform=ece
        Server response
        Code	Details
        200
        Response body
        Download
        {
          "Content-Length": "90",
          "Content-Type": "application/json",
          "freeform": "ece"
        }
        Response headers
         access-control-allow-credentials: true
         access-control-allow-origin: http://httpbin.org
         connection: keep-alive
         content-length: 90
         content-type: application/json
         date: Tue, 26 Jul 2022 15:45:47 GMT
         freeform: ece
         server: gunicorn/19.9.0
        Responses
        Code	Description
        200
        Response headers
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/response-headers?freeform=ece");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/response-headers?freeform=ece")
                .build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
       /*
        {
            "Content-Length": "68",
                "Content-Type": "application/json"
        }
        */
        //exp
        JSONObject exp=new JSONObject();
        exp.put("Content-Length", "68");
        exp.put("Content-Type", "application/json");
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("Content-Length"),response.jsonPath().get("Content-Length"));
        Assert.assertEquals(exp.get("Content-Type"),response.jsonPath().get("Content-Type"));
    }
    @Test
    public void res() {
        Response response=given()
                .accept(ContentType.JSON)
                .when()
                .get("http://httpbin.org/response-headers?freeform=ece");
        response.prettyPrint();
       /*
        {
            "Content-Length": "68",
                "Content-Type": "application/json"
        }
        */
        //exp
        JSONObject exp=new JSONObject();
        exp.put("Content-Length", "90");
        exp.put("Content-Type", "application/json");
        exp.put("freeform", "ece");
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("Content-Length"),response.jsonPath().get("Content-Length"));
        Assert.assertEquals(exp.get("Content-Type"),response.jsonPath().get("Content-Type"));
        Assert.assertEquals(exp.get("freeform"),response.jsonPath().get("freeform"));

    }
}
