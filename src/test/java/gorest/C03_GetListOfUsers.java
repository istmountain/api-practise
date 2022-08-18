package gorest;

import baseUrls.BaseUrlGorest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C03_GetListOfUsers extends BaseUrlGorest {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://gorest.co.in/public/v2/users");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        /*
        List users
curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization: Bearer ACCESS-TOKEN" -XGET "https://gorest.co.in/public/v2/users"
         */
        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/users")
                .setAccept("application/json").
                setContentType("application/json")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        int size=response.jsonPath().getList("id").size();
        response.then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.hasSize(size));
    }
    @Test
    public void res() {
        specGorest.pathParam("pp1",  "users");
        Response response = given()
                .spec(specGorest)
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .when()
                .get("/{pp1}");
        response.prettyPrint();
        int size = response.jsonPath().getList("id").size();
        response.then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.hasSize(size));
    }
}
