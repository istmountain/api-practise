package httpBin.redirects;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C05_PostRedirects extends BaseHttpBin {
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
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/redirect-to")
                .addFormParam("url","https://www.automationexercise.com&status_code=200")
                .setAccept("text/html").setContentType("application/x-www-form-urlencoded").build();
        Response response=given()
                .spec(req)
                .when()
                .post();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(504);

    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","redirect-to");
        Response response=given()
                .spec(specHttpbin)
                .formParam("url","https://www.automationexercise.com&status_code=200")
                .contentType("application/x-www-form-urlencoded")
                .accept("text/html")
                .when()
                .post("/{pp1}");
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(504);
    }
}
