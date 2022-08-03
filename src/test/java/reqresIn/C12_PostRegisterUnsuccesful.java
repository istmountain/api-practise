package reqresIn;

import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
import static org.junit.Assert.assertEquals;

public class C12_PostRegisterUnsuccesful extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/register");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n    \"email\": \"eve.holt@reqres.in\",\n    \"password\": \"pistol\"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        //Body
        /*
        {
    "email": "eve.holt@reqres.in",

}
         */
        JSONObject body=new JSONObject();
        body.put("email", "sydney@fife");
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/register").build();
        Response response=given()
                .spec(req)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post();
        response.prettyPrint();
        //asssert
        response
                .then()
                .assertThat()
                .statusCode(400);
        //save response
        JSONObject exp=new JSONObject();
        exp.put("error", "Missing password");
        JsonPath act=response.jsonPath();
        assertEquals(exp.get("error"),act.get("error"));

        /*
    Request
/api/register
{
    "error": "Missing email or username"
}

Response
200

{
    "id": 4,
    "token": "QpwL5tke4Pnpja7X4"
}
     */
    }
    @Test
    public void res() {
            /*
    Request
/api/register

{
    "email": "eve.holt@reqres.in",
    "password": "pistol"
}

Response
200

{
    "id": 4,
    "token": "QpwL5tke4Pnpja7X4"
}https://reqres.in/api/register
     */
        specReqres.pathParams("pp1","api","pp2","register");
        JSONObject body=new JSONObject();
        body.put("email", "sydney@fife");
        Response response=given()
                .spec(specReqres)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/{pp1}/{pp2}");
        response.prettyPrint();
        //asssert
        response
                .then()
                .assertThat()
                .statusCode(400);
        //save response
        JSONObject exp=new JSONObject();
        exp.put("error", "Missing password");
        JsonPath act=response.jsonPath();
        assertEquals(exp.get("error"),act.get("error"));
    }
}
