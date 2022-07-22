package petSwaggerTests;

import baseUrls.BaseUrlPet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class C07_PostFile extends BaseUrlPet {
    @Test
    public void name() {
        /*
        File file = new File("File Path");
String endpoint = "/api/v3/abc";

   RestAssured.baseURI = "http://dummy.com/";
        Response res = given()
                .formParam("token", "eacca99696ac5")
                .multiPart("media_url", file,"application/json")
                .when().post(endpoint);
                curl -X 'POST' \
  'https://petstore.swagger.io/v2/pet/9/uploadImage' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'file=@akışŞeması.jpg;type=image/jpeg'
         */
        String filePath="C:\\Users\\himer\\OneDrive\\Masaüstü\\akışŞeması.jpg";
        File file=new File(filePath);
        specPet.pathParams("pp1","pet","pp2",9,"pp3","uploadImage");
        Response response=given()
                .spec(specPet)
                .accept(ContentType.JSON)
                .multiPart("form-data",file,"application/json")
                .formParam("file","@akışŞeması.jpg","type","image/jpeg")
                .when().post("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200);

    }
}
