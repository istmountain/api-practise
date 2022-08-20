package restApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_UpdateUserDetails {
    /*
    Update user
  curl -i -H "Accept:application/json" -H "Content-Type:application/json"
 -H "Authorization: Bearer ACCESS-TOKEN" -XPATCH "https://gorest.co.in/public/v2/users/24"
 -d '{"name":"Allasani Peddana", "email":"allasani.peddana@15ce.com", "status":"active"}'
 "Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79"
     */

    @Test
    public void http() throws IOException {
        /*
        {
    "email": "allaasani.peddana@15ce.com",
    "name": "Allasani Peddana",
    "status": "active",
    "id": 24,
    "gender": "female"
}
         */
        URL url = new URL("https://gorest.co.in/public/v2/users/24");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PATCH");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"name\":\"Allasani Peddana\", \n \"email\":\"allaasani.peddana@15ce.com\", \n \"status\":\"active\"}\n";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void req() {
         /*
    Update user
  curl -i -H "Accept:application/json" -H "Content-Type:application/json"
 -H "Authorization: Bearer ACCESS-TOKEN" -XPATCH "https://gorest.co.in/public/v2/users/24"
 -d '{"name":"Allasani Peddana", "email":"allasani.peddana@15ce.com", "status":"active"}'
 "Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79"
        {
    "email": "allaasani.peddana@15ce.com",
    "name": "Allasani Peddana",
    "status": "active",
    "id": 24,
    "gender": "female"
}
         */
        JSONObject body=new JSONObject();
        body.put("email", "allaasani.peddsdana@15ce.com");
        body.put("name", "Allasani Peddana");
        body.put("status", "active");
        body.put("gender", "female");
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v2/users/24")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
        Response response=given()
                .spec(req)
                .body(body.toString())
                .when()
                .patch();
        response.prettyPrint();
        JSONObject exp=new JSONObject();
        exp.put("email", "allaasani.peddsdana@15ce.com");
        exp.put("name", "Allasani Peddana");
        exp.put("status", "active");
        exp.put("gender", "female");
        exp.put("id", 24);
        JsonPath act=response.jsonPath();
        response
                .then()
                .assertThat()
                .statusCode(200);
        assertEquals(exp.get("email"),act.get("email"));
        assertEquals(exp.get("name"),act.get("name"));
        assertEquals(exp.get("status"),act.get("status"));
        assertEquals(exp.get("gender"),act.get("gender"));
        assertEquals(exp.get("id"),act.get("id"));
    }

    @Test
    public void res() {
                 /*
    Update user
  curl -i -H "Accept:application/json" -H "Content-Type:application/json"
 -H "Authorization: Bearer ACCESS-TOKEN" -XPATCH "https://gorest.co.in/public/v2/users/24"
 -d '{"name":"Allasani Peddana", "email":"allasani.peddana@15ce.com", "status":"active"}'
 "Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79"
        {
    "email": "allaasani.peddana@15ce.com",
    "name": "Allasani Peddana",
    "status": "active",
    "id": 24,
    "gender": "female"
}
         */

    }
}
