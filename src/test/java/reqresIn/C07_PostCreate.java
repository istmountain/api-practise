package reqresIn;

import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users").setAccept(ContentType.JSON).setBody(body.toString()).build();
        Response response=given()
                .spec(req)
                .contentType(ContentType.JSON)
                .when()
                .post();
        response.prettyPrint();

        /*
         {
    "name": "morpheus",
    "job": "leader"
} */
/*
Response
201
{
    "id": "176",
    "createdAt": "2022-08-02T13:06:46.929Z"
}
{
    "name": "morpheus",
    "job": "leader",
    "id": "686",
    "createdAt": "2022-08-02T12:56:38.736Z"
}
         */
        JSONObject exp=new JSONObject();
        exp.put("name", "morpheus");
        exp.put("job", "leader");
        //assert
        response
                .then()
                .assertThat()
                .statusCode(201);
        //save response
        JsonPath act=response.jsonPath();
        assertEquals(exp.get("name"),act.get("name"));
        assertEquals(exp.get("job"),act.get("job"));

    }
    @Test
    public void res() { //https://reqres.in/api/users
        specReqres.pathParams("pp1","api","pp2","users");
        JSONObject body=new JSONObject();
        body.put("name", "morpheus");
        body.put("job", "leader");
        Response response=given()
                .spec(specReqres)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/{pp1}/{pp2}");
        response.prettyPrint();
        //response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(201)
                .body("id", Matchers.notNullValue());
        //save response
        JSONObject exp=new JSONObject();
        exp.put("name", "morpheus");
        exp.put("job", "leader");
        JsonPath act=response.jsonPath();
        assertEquals(exp.get("name"),act.get("name"));
        assertEquals(exp.get("job"),act.get("job"));
        /*
        {
    "name": "morpheus",
    "job": "leader",
    "id": "863",
    "createdAt": "2022-08-02T13:18:17.847Z"
}
         */
    }

}
