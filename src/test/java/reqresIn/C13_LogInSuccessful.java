package reqresIn;

import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.junit.Assert.assertEquals;

public class C13_LogInSuccessful extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/login");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Basic ZXZlLmhvbHRAcmVxcmVzLmluOmNpdHlzbGlja2E=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        /*
        {
    "email": "eve.holt@reqres.in",
    "password": "cityslicka"
}
         */
        JSONObject body=new JSONObject();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "cityslicka");

       RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/login")
              // .addHeader("Authorization", "Basic ZXZlLmhvbHRAcmVxcmVzLmluOmNpdHlzbGlja2E=")
        .build();
       Response response=given()
               .spec(req)
               .contentType(ContentType.JSON)
               .body(body.toString())
               .when()
               .post();
       response.prettyPrint();
       //expbody
        /*
        {
    "token": "QpwL5tke4Pnpja7X4"
}
         */
        JSONObject exp=new JSONObject();
        exp.put("token", "QpwL5tke4Pnpja7X4");
        //save response
        JsonPath act=response.jsonPath();
        response.then()
                .assertThat()
                .statusCode(200);
        assertEquals(exp.get("token"),act.get("token"));

    }
    @Test
    public void res() {
 //"https://reqres.in/api/login"
        specReqres.pathParams("pp1","api","pp2","login");
        JSONObject body=new JSONObject();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "cityslicka");
        Response response=given()
                .spec(specReqres)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/{pp1}/{pp2}");
        response.prettyPrint();
        //asssert
        JSONObject exp=new JSONObject();
        exp.put("token", "QpwL5tke4Pnpja7X4");
        //save response
        JsonPath act=response.jsonPath();
        response.then()
                .assertThat()
                .statusCode(200);
        assertEquals(exp.get("token"),act.get("token"));
    }
}
