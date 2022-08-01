package reqresIn;

import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C04_ApiUnknown extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/unknown");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/unknown").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void res() { //https://reqres.in/api/unknown
        specReqres.pathParams("pp1","api","pp2","unknown");
        Response response=given()
                .spec(specReqres)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        response.prettyPrint();
        int size= response.jsonPath().getList("data").size();
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("data", Matchers.hasSize(size));
    }

}
