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

public class C02_GetUserGraphQL extends BaseUrlGorest {
    /*
    curl -i -H "Accept:application/json"
    -H "Content-Type:application/json"
    -H "Authorization: Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79"
    -XPOST "https://gorest.co.in/public/v2/graphql"
    -d '{"query":"query{user(id: 32) { id name email gender status }}"}'
    {
    "data": {
        "user": {
            "id": 32,
            "name": "Chandrabhan Nair Sr.",
            "email": "sr_nair_chandrabhan@kuhlman.net",
            "gender": "male",
            "status": "active"
        }
    }
}
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("https://gorest.co.in/public/v2/graphql");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");

        String data = "{\"query\":\"query{user(id: 32) { id name email gender status }}\"}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void req() {
            /*
    curl -i -H "Accept:application/json"
    -H "Content-Type:application/json"
    -H "Authorization: Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79"
    -XPOST "https://gorest.co.in/public/v2/graphql"
    -d '{"query":"query{user(id: 32) { id name email gender status }}"}'
    {
    "data": {
        "user": {
            "id": 32,
            "name": "Chandrabhan Nair Sr.",
            "email": "sr_nair_chandrabhan@kuhlman.net",
            "gender": "male",
            "status": "active"
        }
    }
}
     */
        String data = "{\"query\":\"query{user(id: 32) { id name email gender status }}\"}";
        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/graphql")
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .build();
        Response response=
                given()
                        .spec(req)
                        .body(data)
                        .when()
                        .post();
        response.prettyPrint();
        //expBody
        /*
        {
    "data": {
        "user": {
            "id": 32,
            "name": "Dhruv Panicker",
            "email": "panicker_dhruv@feil.org",
            "gender": "male",
            "status": "inactive"
        }
    }
}

         */

    }

    @Test
    public void res() {
    }
}
