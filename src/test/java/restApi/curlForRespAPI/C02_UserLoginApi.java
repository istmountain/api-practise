package restApi.curlForRespAPI;

import baseUrls.BaseRestApi;
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
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02_UserLoginApi extends BaseRestApi {
    /*
    http://restapi.adequateshop.com/api/authaccount/login
    st/api/authaccount/login
API Request
{
	"email":"Developer5@gmail.com",
	"password":123456
}
API Response

{
    "$id": "1",
    "code": 0,
    "message": "success",
    "data": {
        "$id": "2",
        "Id": 7075,
        "Name": "Developer",
        "Email": "Developer5@gmail.com",
        "Token": "02b869e4-ea45-4b5c-b764-642a39e95bb7"
    }
}
If email or password is wrong then API Response

{
    "$id": "1",
    "code": 1,
    "message": "invalid username or password",
    "data": null
}
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://restapi.adequateshop.com/api/authaccount/login");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n     \"email\":\"Developer5@gmail.com\",\n            \"password\":123456\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        /*
         /*
    http://restapi.adequateshop.com/api/authaccount/login
    st/api/authaccount/login
API Request
{
	"email":"Developer5@gmail.com",
	"password":123456
}
         */
        JSONObject body=new JSONObject();
        body.put("email","Developer5@gmail.com");
        body.put("password",123456);

        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://restapi.adequateshop.com/api/authaccount/login")
                .build(); //.pathParams("pp1","api","pp2","authaccount","pp3","registration");
        Response response=given()
                .spec(req)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .accept(ContentType.JSON)
                .when()
                .post();
        response.prettyPrint();

       /*

{
    "$id": "1",
    "code": 0,
    "message": "success",
    "data": {
        "$id": "2",
        "Id": 7075,
        "Name": "Developer",
        "Email": "Developer5@gmail.com",
        "Token": "02b869e4-ea45-4b5c-b764-642a39e95bb7"
    }
}
If email or password is wrong then API Response

{
    "$id": "1",
    "code": 1,
    "message": "invalid username or password",
    "data": null
}
     */

        JSONObject expData=new JSONObject();
        expData.put("code", 1);
        expData.put("message", "The email address you have entered is already registered");
       // expData.put("data", "null");
        //Response
        JsonPath actual=response.jsonPath();
        response
                .then()
                .assertThat()
                .statusCode(200);
        assertEquals(expData.get("code"),actual.get("code"));
        assertEquals(expData.get("message"),actual.get("message"));
        // assertEquals(expData.get("data"),actual.get("data"));
    }
    @Test
    public void res() {
         /*
    http://restapi.adequateshop.com/api/authaccount/login
    st/api/authaccount/login
API Request
{
	"email":"Developer5@gmail.com",
	"password":123456
}
         */
        JSONObject body=new JSONObject();
        body.put("email","Developer5@gmail.com");
        body.put("password",123456);
        specRest.pathParams("pp1","api","pp2","authaccount","pp3","login");
        Response response=given()
                .spec(specRest)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/{pp1]/{pp2}/{pp3}");
        response.prettyPrint();
      /*

{
    "$id": "1",
    "code": 0,
    "message": "success",
    "data": {
        "$id": "2",
        "Id": 7075,
        "Name": "Developer",
        "Email": "Developer5@gmail.com",
        "Token": "02b869e4-ea45-4b5c-b764-642a39e95bb7"
    }
}
If email or password is wrong then API Response

{
    "$id": "1",
    "code": 1,
    "message": "invalid username or password",
    "data": null
}
     */
        JSONObject expData=new JSONObject();
        expData.put("code", 1);
        expData.put("message", "The email address you have entered is already registered");
        //Response
        JsonPath actual=response.jsonPath();
        response
                .then()
                .assertThat()
                .statusCode(200);
        assertEquals(expData.get("code"),actual.get("code"));
        assertEquals(expData.get("message"),actual.get("message"));

    }
}
