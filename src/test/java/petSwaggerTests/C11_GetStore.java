package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C11_GetStore extends BaseUrlPet {
    @Test
    public void name() {
        specPet.pathParams("pp1","store","pp2","order");
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();

    }
}
