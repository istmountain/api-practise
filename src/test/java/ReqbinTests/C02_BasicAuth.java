package ReqbinTests;

import baseUrls.BaseUrlReqbin;
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

public class C02_BasicAuth extends BaseUrlReqbin {
    @Test
    public void name() {
        /*
        POST /echo/post/json HTTP/1.1
Authorization: Basic bGEuY3J5bW9zYTMyMUBnbWFpbC5jb206MTIzNDU2Nzg=
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
        JSONObject js=new JSONObject();
        js.put("Id", 12345);
        js.put("Customer", "John Smith");
        js.put("Quantity", 1);
        js.put("Price", 10.00);
        RequestSpecification req=new RequestSpecBuilder().addHeader("Authorization","Basic bGEuY3J5bW9zYTMyMUBnbWFpbC5jb206MTIzNDU2Nzg=")
                .setBaseUri("https://reqbin.com/echo/post/json").build();
        Response response=given()
                .spec(req)
                .contentType(ContentType.JSON)
                .body(js.toString())
                .when()
                .post();
        System.out.println(response.getStatusCode());
    }

    @Test
    public void test2() throws IOException {
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
