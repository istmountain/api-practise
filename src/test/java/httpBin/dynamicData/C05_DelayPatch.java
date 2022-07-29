package httpBin.dynamicData;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C05_DelayPatch extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        /*
        Curl
curl -X PATCH "http://httpbin.org/delay/3" -H "accept: application/json"
Request URL
http://httpbin.org/delay/3
         */
        URL url = new URL("http://httpbin.org/delay/3");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + "http://httpbin.org/delay/2" + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        /*
        Curl
curl -X PATCH "http://httpbin.org/delay/3" -H "accept: application/json"
Request URL
http://httpbin.org/delay/3
         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/delay/3").setContentType("application/json").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);
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
        response.then()
                .assertThat()
                .statusCode(200);

        /*
        Curl
curl -X PATCH "http://httpbin.org/delay/3" -H "accept: application/json"
Request URL
http://httpbin.org/delay/3
         */
    }
}
