package restApi;

import baseUrls.BaseRestApi;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C01_PostApiAuthAccount extends BaseRestApi {
    //http://restapi.adequateshop.com/api/authaccount/registration
    /*
    Post/api/authaccount/registration
API Request
{

            "name":"Developer",
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
            "Token": "3030401c-c5a5-43c8-8b73-2ab9e6f2ca22"
    }
}
If email already in use then API Response

{
            "$id": "1",
            "code": 1,
            "message": "The email address you have entered is already registered",
            "data": null
}
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://restapi.adequateshop.com/api/authaccount/registration");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n\n            \"name\":\"Developer\",\n            \"email\":\"Developer5@gmail.com\",\n            \"password\":123456\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("")
                .build().pathParams("pp1","api","pp2","authaccount","pp3","registration");
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();

    }

    @Test
    public void res() {
    }
}
