package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import utilities.ConfigReader;

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

}
