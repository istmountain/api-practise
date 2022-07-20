package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_GetPending extends BaseUrlPet {
    @Test
    public void name() {
        /*
        curl -X 'GET' \
  'https://petstore.swagger.io/v2/pet/findByStatus?status=pending' \
  -H 'accept: application/json'
         */
        specPet.pathParams("pp1","pet","pp2","findByStatus")
                .queryParam("status","pending");
        Response response=given()
                .spec(specPet)
                .when()
                .accept(ContentType.JSON)
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
    }
}
