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

public class C05_GetSingleResource extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/unknown/2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/unknown/2").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", Matchers.equalTo(2)
                        ,"data.name", Matchers.equalTo("fuchsia rose")
                        ,"data.year", Matchers.equalTo(2001)
                        ,"data.color", Matchers.equalTo("#C74375")
                        ,"data.pantone_value", Matchers.equalTo("17-2031")
                        ,"support.url", Matchers.equalTo("https://reqres.in/#support-heading")
                        ,"support.text", Matchers.equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
    @Test
    public void res() { //https://reqres.in/api/unknown/2
        specReqres.pathParams("pp1","api","pp2","unknown","pp3",2);
        Response response=given()
                .spec(specReqres)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", Matchers.equalTo(2)
                ,"data.name", Matchers.equalTo("fuchsia rose")
                ,"data.year", Matchers.equalTo(2001)
                ,"data.color", Matchers.equalTo("#C74375")
                ,"data.pantone_value", Matchers.equalTo("17-2031")
                ,"support.url", Matchers.equalTo("https://reqres.in/#support-heading")
                ,"support.text", Matchers.equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }


}
