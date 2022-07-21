package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C08_Delete extends BaseUrlPet {
    @Test
    public void name() {
        /*
        curl -X 'DELETE' \
  'https://petstore.swagger.io/v2/pet/0' \
  -H 'accept: application/json'
         */
        specPet.pathParams("pp1","pet","pp2",9001);
        Response response=given()
                .spec(specPet)
                .contentType(ContentType.JSON)
                .when()
                .delete("/{pp1}/{pp2}");
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
