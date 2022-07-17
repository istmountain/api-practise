package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utilities.ConfigReader;

import static org.junit.Assert.assertEquals;

public class C06_Patch extends BaseUrlRestful {
    @Test
    public void name() {
        /* CH
https://restful-booker.herokuapp.com/booking/:id
           curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Cookie: token=abc123' \
  -d '{
    "firstname" : "James",
    "lastname" : "Brown"
}'
         */
        // 1 -Request url ve body'sini hazirlamak
        specRestful.pathParams("pp1","booking","pp2",1);
        JSONObject js=new JSONObject();
        js.put("firstname" , "Asya");
        js.put("lastname" ,"Ararat");
        RequestSpecification request= RestAssured.given()
                .spec(specRestful)
                .contentType(ContentType.JSON)
                .cookie("token", ConfigReader.getProperty("token"))
                .when()
                .body(js.toString());
        Response response=request.patch("/{pp1}/{pp2}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        JSONObject expData=new JSONObject();
        expData.put("firstname" , "Asya");
        expData.put("lastname" ,"Ararat");
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        JsonPath jsonPath=response.jsonPath();
        assertEquals(expData.get("firstname"),jsonPath.get("firstname"));
        assertEquals(expData.get("firstname"),jsonPath.get("firstname"));

    }
}
