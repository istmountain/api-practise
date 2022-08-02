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

public class C10_Delete extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/users/2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("DELETE");
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users/2").build();
        Response response=given()
                .spec(req)
                .when()
                .delete();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(204);
    }
    @Test
    public void res() { //https://reqres.in/api/unknown
        specReqres.pathParams("pp1","api","pp2","users","pp3",2);
        Response response=given()
                .spec(specReqres)
                .when()
                .delete("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(204);
    }
}
