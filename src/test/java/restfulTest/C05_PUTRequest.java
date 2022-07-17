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

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_PUTRequest extends BaseUrlRestful {
    @Test
    public void put() {
        /*
        curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Cookie: token=abc123' \
  -d '{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
         */
        // 1 -Request url ve body'sini hazirlamak
        specRestful.pathParams("pp1","booking","pp2",152);
        //body create
        JSONObject outer=new JSONObject();
        JSONObject inner=new JSONObject();
        inner.put("checkin" , "2018-01-01");
        inner.put("checkout" , "2019-01-01");
        outer.put("firstname" , "Jim");
        outer.put("lastname" , "Brown");
        outer.put("totalprice" , 111);
        outer.put("depositpaid" , true);
        outer.put("bookingdates",inner);
        outer.put("additionalneeds" , "Breakfast");
        //create response

        RequestSpecification request= RestAssured.given()
                .contentType(ContentType.JSON)
                .spec(specRestful)
                .cookie("token", ConfigReader.getProperty("token"))
                .when()
                .body(outer.toString());
        Response response=request.put("/{pp1}/{pp2}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        /*
     {
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
         */
        JSONObject first=new JSONObject();
        JSONObject expData=new JSONObject();
        first.put("checkin" , "2018-01-01");
        first.put("checkout" , "2019-01-01");
        expData.put("firstname" , "Jim");
        expData.put("lastname" , "Brown");
        expData.put("totalprice" , 111);
        expData.put("depositpaid" , true);
        expData.put("bookingdates",first);
        expData.put("additionalneeds" , "Breakfast");
        // 3- Response'u kaydet
        /*
        {
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
         */
        // 4- Assertion'lari yap
        JsonPath jsonPath=response.jsonPath();

        response.then()
                .assertThat()
                .statusCode(200);
        assertEquals(expData.get("firstname"),jsonPath.get("firstname"));
        assertEquals(expData.get("lastname"),jsonPath.get("lastname"));
        assertEquals(expData.get("totalprice"),jsonPath.get("totalprice"));
        assertEquals(expData.get("depositpaid"),jsonPath.get("depositpaid"));
        assertEquals(expData.getJSONObject("bookingdates").get("checkin"),jsonPath.get("bookingdates.checkin"));
        assertEquals(expData.getJSONObject("bookingdates").get("checkout"),jsonPath.get("bookingdates.checkout"));
        assertEquals(expData.get("additionalneeds"),jsonPath.get("additionalneeds"));

    }
}
