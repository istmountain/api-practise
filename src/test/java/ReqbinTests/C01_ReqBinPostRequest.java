package ReqbinTests;

import baseUrls.BaseUrlPet;
import baseUrls.BaseUrlReqbin;
import com.sun.net.httpserver.Request;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C01_ReqBinPostRequest extends BaseUrlReqbin {
    @Test
    public void name() {
        /*
        https://reqbin.com/echo/post/json
        POST /echo/post/json HTTP/1.1
Authorization: Bearer {token}
Host: reqbin.com
Content-Type: application/json
Content-Length: 80

{
  "Id": 12345,
  "Customer": "John Smith",
  "Quantity": 1,
  "Price": 10.00
}
         */
        JSONObject data=new JSONObject();
        data.put("Id", 12345);
        data.put("Customer", "John Smith");
        data.put("Quantity", 1);
        data.put("Price", 10.00);
       // specReqbin.pathParams("pp1","echo","pp2","post","pp3","json")
         //       .header("Authorization", "Bearer mt0dgHmLJMVQhvjpNXDyA83vA_PxH23Y");
        // 1 -Request url ve body'sini hazirlamak

      RequestSpecification req=new RequestSpecBuilder()
               .setBaseUri("https://reqbin.com/echo/get/json")
               .addHeader("Authorization", "Bearer mt0dgHmLJMVQhvjpNXDyA83vA_PxH23Y")
               .build();
                //This adds the token to the header.

        Response response=given()
                .spec(req)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(data.toString())
                .when()
                .post();

              //  .post("/{pp1}/{pp2}/{pp3}");

       // RequestSpecification req=given().request().pr
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat();
             //   .statusCode(200);
    }

    @Test
    public void n2() throws IOException {
        URL url = new URL("https://reqbin.com/echo/get/json");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer mt0dgHmLJMVQhvjpNXDyA83vA_PxH23Y");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void m2() throws IOException {
        URL url = new URL("https://reqbin.com/echo/post/json");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Basic bGEuY3J5bW9zYTMyMUBnbWFpbC5jb206MTIzNDU2Nzg=");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n  \"Id\": 12345,\n  \"Customer\": \"John Smith\",\n  \"Quantity\": 1,\n  \"Price\": 10.00\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
