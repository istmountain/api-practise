package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
        specRestful.pathParams("pp1","booking","pp2",556);
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
        Response response=
                given()
                        .spec(specRestful)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(outer.toString())
                        .put("/{pp1}/{pp2}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        /*
        {
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
         */
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
    }
}
