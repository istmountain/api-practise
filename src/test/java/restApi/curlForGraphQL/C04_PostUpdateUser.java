package restApi.curlForGraphQL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C04_PostUpdateUser {
    @Test
    public void HTTP() throws IOException {
        /*
        Update user
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"mutation{updateUser(input: {id: 9 name: \"Allasani Peddana\" email: \"allasani.peddana@15ce.com\" status: \"active\"}) {user{id name gender email status}}}"}'
         */
              /*
        {
    "email": "allasani.peddana@15ce.com",
    "name": "Allasani Peddana",
    "status": "active",
    "id": 9,
    "gender": "male"
} Response
         */
        URL url = new URL("https://gorest.co.in/public/v2/users/9");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PATCH");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");

        String data = "{\"query\":\"mutation{updateUser(input: {id: 9 name: \"Allasani Peddana\" email: \"allasani.peddana@15ce.com\" status: \"active\"}) {user{id name gender email status}}}\"}";

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
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"mutation{updateUser(input: {id: 9 name: \"Allasani Peddana\" email: \"allasani.peddana@15ce.com\" status: \"active\"}) {user{id name gender email status}}}"}'
         */
        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/graphql")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .setAccept("application/json")
                .setContentType("application/json")
                .build();
        String data = "{\"query\":\"mutation{updateUser(input: {id: 9 name: \"Allasani Peddana\" email: \"allasani.peddana@15ce.com\" status: \"active\"}) {user{id name gender email status}}}\"}";
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
}
