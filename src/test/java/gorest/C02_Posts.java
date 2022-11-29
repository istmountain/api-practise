package gorest;

import baseUrls.BaseUrlGorest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C02_Posts extends BaseUrlGorest {
    @Test
    public void posts() {
        /* POST /public/v2/users
        create new user
        {
    "id": 2527,
    "name": "Prof. Akshayakeerti Devar",
    "email": "akshayakeerti_prof_devar@cole.co",
    "gender": "female",
    "status": "active"
}
         */
        // 1 -Request url ve body'sini hazirlamak
        specGorest.pathParam("pp1","users");
        RequestSpecification req=given()
                .spec(specGorest)
                .contentType(ContentType.JSON);
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
    }

    @Test
    public void name() throws IOException {
        URL url = new URL("https://reqbin.com/echo/get/json");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }

    @Test
    public void httpPost() throws IOException {
        URL url = new URL("https://gorest.co.in/public/v2/users");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");
        http.setRequestProperty("Content-Type", "application/json");

        String data = " {\n                        \"id\": 2536,\n                        \"name\": \"Mr. Shankar Acharya\",\n                        \"email\": \"acharya_sh4522234ygsar_mr@marquardt.com\",\n                        \"gender\": \"male\",\n                        \"status\": \"active\"\n                    }";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }
}
