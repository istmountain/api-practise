package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_GetSold extends BaseUrlPet {
    @Test
    public void name() {
        /*
        curl -X 'GET' \
  'https://petstore.swagger.io/v2/pet/findByStatus?status=sold' \
  -H 'accept: application/json'
         */
        specPet.pathParams("pp1","pet", "pp2","findByStatus")
                .queryParam("status","sold");
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}");

        response.prettyPrint();
    }
}
