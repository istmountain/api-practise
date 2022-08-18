package restApi;

import baseUrls.BaseUrlGorest;
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
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C04_PostCreateUsers extends BaseUrlGorest {
    /*
    Create user
curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:
 Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/users" -d '
 {"name":"Tenali Ramakrishna", "gender":"male",
 "email":"tenali.ramakrishna@15ce.com", "status":"active"}
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("https://gorest.co.in/public/v2/users");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"name\":\"Tenali Ramakrishna\",\n \"gender\":\"male\",\n \"email\":\"tenali898.ramakrishna@15ce.com\", \n \"status\":\"active\"}\n";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
    /*
    Create user
curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:
 Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/users" -d '
 {"name":"Tenali Ramakrishna", "gender":"male",
 "email":"tenali.ramakrishna@15ce.com", "status":"active"}
     */
        JSONObject body=new JSONObject();
        body.put("name","Tenali Ramakrishna");
        body.put("gender","male");
        body.put("email","tenali.ramakr6768ishna@15ce.com");
        body.put("status","active");
        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/users")
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .build();
        Response response=given()
                .spec(req)
                .body(body.toString())
                .when()
                .post();
        response.prettyPrint();
        //exp body
        JSONObject exp=new JSONObject();
        exp.put("name","Tenali Ramakrishna");
        exp.put("gender","male");
        exp.put("email","tenali.ramakr6768ishna@15ce.com");
        exp.put("status","active");
        //save response
        JsonPath act=response.jsonPath();
        response.then()
                .assertThat()
                .statusCode(200);
        assertEquals(exp.get("name"),act.get("name"));
        assertEquals(exp.get("gender"),act.get("gender"));
        assertEquals(exp.get("email"),act.get("email"));
        assertEquals(exp.get("status"),act.get("status"));

    }
    @Test
    public void res() {
        specGorest.pathParam("pp1","users");
            /*
    Create user
curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:
 Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/users" -d '
 {"name":"Tenali Ramakrishna", "gender":"male",
 "email":"tenali.ramakrishna@15ce.com", "status":"active"}
     */
        JSONObject body=new JSONObject();
        body.put("name","Tenali Ramakrishna");
        body.put("gender","male");
        body.put("email","tenali.ramakr6hgfhf768ishna@15ce.com");
        body.put("status","active");
        Response response=given()
                .spec(specGorest)
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .body(body.toString())
                .when()
                .post("/{pp1}");
        response.prettyPrint();
        //exp body
        JSONObject exp=new JSONObject();
        exp.put("name","Tenali Ramakrishna");
        exp.put("gender","male");
        exp.put("email","tenali.ramakr6768ishna@15ce.com");
        exp.put("status","active");
        //save response
        JsonPath act=response.jsonPath();
        response.then()
                .assertThat()
                .statusCode(200);
        assertEquals(exp.get("name"),act.get("name"));
        assertEquals(exp.get("gender"),act.get("gender"));
        assertEquals(exp.get("email"),act.get("email"));
        assertEquals(exp.get("status"),act.get("status"));

    }
}
