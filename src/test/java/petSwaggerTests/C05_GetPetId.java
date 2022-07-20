package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C05_GetPetId extends BaseUrlPet {
    @Test
    public void name() {
        /*
        curl -X 'GET' \
  'https://petstore.swagger.io/v2/pet/9' \
  -H 'accept: application/json'
         */
        specPet.pathParams("pp1","pet","pp2",9);
        Response response=given()
                .spec(specPet)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();

    }
}
