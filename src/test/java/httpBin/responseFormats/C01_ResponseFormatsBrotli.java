package httpBin.responseFormats;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_ResponseFormatsBrotli extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/brotli" -H "accept: application/json"
Request URL
http://httpbin.org/brotli
     */

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/brotli")
                .build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();

        System.out.println(response.getStatusCode());
    }
    @Test
    public void res() {
        specHttpbin.pathParam("pp1","brotli");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}");

        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
