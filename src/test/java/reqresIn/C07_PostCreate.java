package reqresIn;

import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C07_PostCreate extends BaseReqresIn {
    /*

     */
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/users");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n    \"name\": \"morpheus\",\n    \"job\": \"leader\"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        JSONObject body=new JSONObject();
        body.put("name", "morpheus");
        body.put("job", "leader");
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users").setBody(body.toString()).build();
        Response response=given()
                .spec(req)
                .when()
                .post();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(201);
        //asseert

        /*
         {
    "name": "morpheus",
    "job": "leader"
} */
/*
Response
201

{
    "name": "morpheus",
    "job": "leader",
    "id": "686",
    "createdAt": "2022-08-02T12:56:38.736Z"
}
         */
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
