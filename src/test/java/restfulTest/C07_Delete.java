package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import utilities.ConfigReader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C07_Delete extends BaseUrlRestful {
    @Test
    public void cookie() {
        /*
curl -X DELETE \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Cookie: token=abc123'
         */
        // 1 -Request url ve body'sini hazirlamak
        specRestful.pathParams("pp1","booking","pp2",3956);
        RequestSpecification req= given()
                .spec(specRestful)
                .contentType(ContentType.JSON)
                .cookie("token", ConfigReader.getProperty("token"))
                .when();
        Response response=req.delete("/{pp1}/{pp2}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        response.then()
                .assertThat()
                .statusCode(201);


    }

    @Test
    public void authorisation() {
        /*
        curl -X DELETE \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Authorisation: Basic YWRtaW46cGFzc3dvcmQxMjM=
         */
        // 1 -Request url ve body'sini hazirlamak
        specRestful.pathParams("pp1","booking","pp2",70);
        RequestSpecification req=given()
                .spec(specRestful)
                .contentType(ContentType.JSON)
                .auth().basic("Basic","YWRtaW46cGFzc3dvcmQxMjM=")
                .when();
        Response response=req.delete("/{pp1/{pp2}");
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void name() throws IOException {
        URL url = new URL("https://restful-booker.herokuapp.com/booking/152");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("DELETE");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Basic b39c3dc61ea8d8f");
        http.setRequestProperty("Content-Type", "application/json");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }
}
