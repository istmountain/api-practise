package gorest;

import baseUrls.BaseUrlGorest;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_put extends BaseUrlGorest {
    @Test
    public void name() {
        specGorest.pathParams("pp1","users","pp2",2527);
        // 1 -Request url ve body'sini hazirlamak
        // token acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79
        Response response=given()
                .spec(specGorest)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        System.out.println("All cookies" +response.getCookies());
        System.out.println(response.getStatusLine());
        response
                .then()
                .assertThat()
                .statusCode(200);

    }
}
