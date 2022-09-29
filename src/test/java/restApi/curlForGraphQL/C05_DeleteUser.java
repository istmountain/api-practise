package restApi.curlForGraphQL;

import baseUrls.BaseUrlGorest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C05_DeleteUser extends BaseUrlGorest {
    /*
    curl -i -H "Accept:application/json"
    -H "Content-Type:application/json"
    -H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql" ,
    -d '{"query":"mutation{deleteUser(input: {id: 9}){user {id name email gender status}}}"}'
     */
    /*
    Response
    {
    "data": {
        "deleteUser": null
    },
    "errors": [{
        "message": "Resource not found!",
        "locations": [{
            "line": 1,
            "column": 10
        }],
        "path": ["deleteUser"],
        "extensions": {
            "code": "NOT_FOUND",
            "result": [{
                "fieldName": "id",
                "messages": ["Couldn't find record with id=9"]
            }]
        }
    }]
}
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("https://gorest.co.in/public/v2/graphql");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");

        String data = "{\"query\":\"mutation{deleteUser(input: {id: 9}){user {id name email gender status}}}\"}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void req() {
        String data = "{\"query\":\"mutation{deleteUser(input: {id: 9}){user {id name email gender status}}}\"}";
        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/graphql")
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .build();
        Response response=given()
                .spec(req)
                .body(data)
                .when()
                .post();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void res() {
        String data = "{\"query\":\"mutation{deleteUser(input: {id: 9}){user {id name email gender status}}}\"}";
        specGorest.pathParam("pp1","graphql");
        Response response=given()
                .spec(specGorest)
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .body(data)
                .when()
                .post("/{pp1}");
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200);

    }
}
