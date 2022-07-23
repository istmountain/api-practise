package httpBin.auth;

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

import static io.restassured.RestAssured.digest;
import static io.restassured.RestAssured.given;

public class C06_HiddenBasicAuth {
    /*
Curl
curl -X GET "http://httpbin.org/hidden-basic-auth/ece/1234567" -H "accept: application/json"
Request URL
http://httpbin.org/hidden-basic-auth/ece/1234567
     */

    @Test
    public void hidden_basic_auth_REQ() {
        RequestSpecification req=new RequestSpecBuilder().addHeader("Authorization", "Basic ZWNlOjEyMzQ1Njc=")
                .setAuth(digest("ece", "1234567"))
                .setBaseUri("http://httpbin.org/hidden-basic-auth/ece/1234567").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when().get();
        //expData
        JSONObject exp=new JSONObject();
        /*
        {
  "authenticated": true,
  "user": "ece"
}*/
        exp.put("authenticated",true);
        exp.put("user", "ece");
        //response
        JsonPath act=response.jsonPath();
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),act.get("authenticated"));
        Assert.assertEquals(exp.get("user"),act.get("user"));
    }

    @Test
    public void hidden_basic_auth_RES() {
    }

    @Test
    public void hidden_basic_auth_HTTP() throws IOException {
        URL url = new URL("http://httpbin.org/hidden-basic-auth/ece/1234567");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
