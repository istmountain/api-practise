package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C04_Redirects extends BaseHttpBin {
    /*
    PATCH
/redirect-to
302/3XX Redirects to the given URL.
     */
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/redirect-to");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/redirect-to").build();
        Response response=given()
                .spec(req)
                .when()
                .patch();
        response.then()
                .assertThat()
                .statusCode(500);
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","redirect-to");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .patch("/{pp1}");
        response.then()
                .assertThat()
                .statusCode(500);
    }
}
