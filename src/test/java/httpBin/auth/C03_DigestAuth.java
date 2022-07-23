package httpBin.auth;

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

import static io.restassured.RestAssured.*;

public class C03_DigestAuth extends BaseHttpBin {
    @Test
    public void digestAuthREq() {
        RequestSpecification req=new RequestSpecBuilder().setAuth(digest("ece", "1234567"))
                .setBaseUri("http://httpbin.org/digest-auth/auth/ece/1234567").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        //expected Body
        /*
        Response body
{
  "authenticated": true,
  "user": "ece"
}
         */
        JSONObject exp=new JSONObject();
        exp.put("authenticated",true);
        exp.put("user", "ece");
        //Response'u kaydet
        JsonPath act=response.jsonPath();

        //Assert
       response
               .then()
               .assertThat()
               .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),act.get("authenticated"));
        Assert.assertEquals(exp.get("user"),act.get("user"));

    }

    @Test
    public void digestAUTHREQ() {

       Response response= given().auth()
                .digest("ece", "1234567")
               .accept(ContentType.JSON)
                .when()
               .get("http://httpbin.org/digest-auth/auth/ece/1234567");
        System.out.println(response.getStatusCode());

    }

    @Test
    public void httpDigest() throws IOException {
        URL url = new URL("http://httpbin.org/digest-auth/auth/ece/1234567");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
