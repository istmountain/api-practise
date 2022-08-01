package reqresIn;

import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C03_GetNotFoundUsers404 extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/users/23");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users/23").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
       response
               .then()
               .assertThat()
               .statusCode(404);
    }
    @Test
    public void res() { //https://reqres.in/api/users/23
        specReqres.pathParams("pp1","api","pp2","users","pp3",23);
        Response response=given()
                .spec(specReqres)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();
        //response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(404);
    }

}
