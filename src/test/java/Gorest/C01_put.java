package Gorest;

import baseUrls.BaseUrlGorest;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_put extends BaseUrlGorest {
    @Test
    public void name() {
        specGorest.pathParam("pp1","users");
        // 1 -Request url ve body'sini hazirlamak
        Response response=given()
                .spec(specGorest)
                .when()
                .get("/{pp1}");
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
    }
}
