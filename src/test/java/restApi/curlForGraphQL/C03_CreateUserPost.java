package restApi.curlForGraphQL;

import baseUrls.BaseUrlGorest;
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

public class C03_CreateUserPost extends BaseUrlGorest {
    /*
    Create User
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"mutation{createUser(input: {name: \"Tenali Ramakrishna\" gender: \"male\" email: \"tenali.ramakrishna@15ce.com\" status: \"active\"}) {user{id name gender email status}}}"}'
{
    "data": {
        "createUser": null
    },
    "errors": [{
        "message": "Validation failed!",
        "locations": [{
            "line": 1,
            "column": 10
        }],
        "path": ["createUser"],
        "extensions": {
            "code": "UNPROCESSABLE_ENTITY",
            "result": [{
                "fieldName": "email",
                "messages": ["has already been taken"]
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


        String data = "{\"query\":\"mutation{createUser(input: {name: \"Tenali Ramakrishna\" gender: \"male\" email: \"tenali.ramakrishna@15ce.com\" status: \"active\"}) {user{id name gender email status}}}\"}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();



    }
    @Test
    public void req() {
        String data = "{\"query\":\"mutation{createUser(input: {name: \"Tenali Ramakrishna\" gender: \"male\" email: \"tenali.ramakrishna@15ce.com\" status: \"active\"}) {user{id name gender email status}}}\"}";

        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/graphql")
                .setAccept( "application/json")
                .setContentType("application/json")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .build();
        Response response=given()
                .spec(req)
                .body(data)
                .when()
                .post();
        /*
        {
    "data": {
        "createUser": null
    },
    "errors": [{
        "message": "Validation failed!",
        "locations": [{
            "line": 1,
            "column": 10
        }],
        "path": ["createUser"],
        "extensions": {
            "code": "UNPROCESSABLE_ENTITY",
            "result": [{
                "fieldName": "email",
                "messages": ["has already been taken"]
            }]
        }
    }]
}
         */
        JSONObject exp=new JSONObject();
    }
    @Test
    public void res() {

        String data = "{\"query\":\"mutation{createUser(input: {name: \"Tenali Ramakrishna\" gender: \"male\" email: \"tenali.ramakrishna@15ce.com\" status: \"active\"}) {user{id name gender email status}}}\"}";
        specGorest.pathParam("pp1","graphql");
        Response response=given()
                .spec(specGorest)
                .accept("application/json")
                .contentType("application/json")
                .body(data)
                .when()
                .post("/{pp1}");
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(201);
    }

}
