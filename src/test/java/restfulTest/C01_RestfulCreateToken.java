package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_RestfulCreateToken extends BaseUrlRestful {
    @Test
    public void name() {
        /*
        post request
https://restful-booker.herokuapp.com/auth
        Content-Type	string
Sets the format of payload you are sending

Varsayılan değer: application/json
        username	String
Username for authentication

Varsayılan değer: admin

password	String
Password for authentication

Varsayılan değer: password123
token	String
Token to use in future requests
         */
        specRestful.pathParam("pp1","auth");
        // 1 -Request url ve body'sini hazirlamak
        JSONObject js=new JSONObject();
        js.put("username","admin");
        js.put("password","password123");
        Response response=
                given()
                        .spec(specRestful)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(js.toString())
                        .post("/{pp1}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        JsonPath jsonPath=response.jsonPath();
        String token= jsonPath.get("token");
        System.out.println(token);
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
