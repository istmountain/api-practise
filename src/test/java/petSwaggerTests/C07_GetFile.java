package petSwaggerTests;

import baseUrls.BaseUrlPet;
import org.junit.Test;

public class C07_GetFile extends BaseUrlPet {
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
    }
}
