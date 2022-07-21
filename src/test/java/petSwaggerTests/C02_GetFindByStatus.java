package petSwaggerTests;

import baseUrls.BaseUrlGorest;
import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetFindByStatus extends BaseUrlPet {
    /*
    curl -X 'GET' \
  'https://petstore.swagger.io/v2/pet/findByStatus?status=available' \
  -H 'accept: application/json'
     */

    @Test
    public void name() {
        specPet.pathParams("pp1","pet","pp2","findByStatus")
                .queryParam("status","available");
        Response response=given()
                .spec(specPet)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        System.out.println(response.getHeader("api_key"));
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
