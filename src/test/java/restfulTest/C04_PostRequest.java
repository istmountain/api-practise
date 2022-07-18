package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C04_PostRequest extends BaseUrlRestful {
    @Test
    public void postRequest() {
        /*
        https://restful-booker.herokuapp.com/booking    post request
        {
        postRequest
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
        ResponseBody
        ExpectedData


         */
        // 1 -Request url ve body'sini hazirlamak
        specRestful.pathParam("pp1","booking");
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
        //Response create
        Response response=
                given()
                        .spec(specRestful)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(outer.toString())
                        .post("/{pp1}");
        response.prettyPrint();
      response.getHeaders().asList().stream().forEach(t-> System.out.println(t.getName()));
        // 2- Expected Data'yi hazirla
         /*
        {
    "bookingid": 1,
    "booking": {
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
}
         */
        JSONObject expData=new JSONObject();
        JSONObject booking=new JSONObject();
        JSONObject bookingdate=new JSONObject();
        bookingdate.put("checkin", "2018-01-01");
        bookingdate.put("checkout", "2019-01-01");
        booking.put("firstname", "Jim");
        booking.put("lastname", "Brown");
        booking.put("totalprice", 111);
        booking.put("depositpaid", true);
        booking.put("bookingdates",bookingdate);
        booking.put("additionalneeds", "Breakfast");
        expData.put("booking",booking);
        //JsonPath create
        JsonPath jsonPath=response.jsonPath();

        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        assertEquals(200,response.statusCode());
        assertEquals(expData.getJSONObject("booking").get("firstname"),jsonPath.get("booking.firstname"));
        assertEquals(expData.getJSONObject("booking").get("lastname"),jsonPath.get("booking.lastname"));
        assertEquals(expData.getJSONObject("booking").get("totalprice"),jsonPath.get("booking.totalprice"));
        assertEquals(expData.getJSONObject("booking").get("depositpaid"),jsonPath.get("booking.depositpaid"));
        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),jsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),jsonPath.get("booking.bookingdates.checkout"));
        assertEquals(expData.getJSONObject("booking").get("additionalneeds"),jsonPath.get("booking.additionalneeds"));

    }
}
