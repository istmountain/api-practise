package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest extends BaseUrlRestful {
    @Test
    public void pathParam() {
       specRestful.pathParam("pp1","booking");
        // 1 -Request url ve body'sini hazirlamak
        Response response=
                given()
                        .spec(specRestful)
                        .when()
                        .get("/{pp1}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        /*
            "firstname": "amod261",
    "lastname": "mahajan261",
    "totalprice": 757,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-07-16",
        "checkout": "2022-07-27"
         */
        int totalSize=response.jsonPath().getList("bookingid").size();
        System.out.println(totalSize);
        response.then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasSize(totalSize));
    }

    @Test
    public void pathParams() {
        // https://restful-booker.herokuapp.com/booking?firstname=sally&lastname=brown
        // 1 -Request url ve body'sini hazirlamak
        specRestful.pathParam("pp1","booking").queryParams("firstname","amod261","lastname","mahajan261");
        Response response=
                given()
                        .spec(specRestful)
                        .when()
                        .get("/{pp1}"); // query parameter not add in here
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        response
                .then()
                .statusCode(200);
               // .body("bookingid")
    }

    @Test
    public void queryParams() {
        //https://restful-booker.herokuapp.com/booking?checkin=2014-03-13&checkout=2014-05-21
        // 1 -Request url ve body'sini hazirlamak
        specRestful.pathParam("pp1","booking").queryParams("checkin","2022-07-16","checkout","2022-07-27");
        Response response=
                given()
                        .spec(specRestful)
                        .when()
                        .get("/{pp1}");
        response.prettyPrint();
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
