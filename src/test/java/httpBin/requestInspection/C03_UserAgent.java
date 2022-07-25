package httpBin.requestInspection;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
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

public class C03_UserAgent extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/user-agent");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        /*
        Curl
curl -X GET "http://httpbin.org/user-agent" -H "accept: application/json"
Request URL
http://httpbin.org/user-agent
Server response
Code	Details
200
Response body
Download
{
  "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62"
}
         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/user-agent").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        //exp
        JSONObject exp=new JSONObject();
        exp.put("user-agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        //act
        JsonPath act=response.jsonPath();
        //Assert
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
        Assert.assertEquals(exp.get("user-agent"),act.get("user-agent"));
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","user-agent");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}");
        response.prettyPrint();
        //exp
        JSONObject exp=new JSONObject();
        exp.put("user-agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        //act
        JsonPath act=response.jsonPath();
        //Assert
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
        Assert.assertEquals(exp.get("user-agent"),act.get("user-agent"));
    }
}
