package dummyTests;

import baseUrls.BaseDummyRestApi;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_GetAllEmployee extends BaseDummyRestApi {
    @Test
    public void name() {

        /*   https://dummy.restapiexample.com/api/v1/employees
{
"status": "success",
"data": [
	{
	"id": "1",
	"employee_name": "Tiger Nixon",
	"employee_salary": "320800",
	"employee_age": "61",
	"profile_image": ""
	},
	....
	]
}
         */
        // 1 -Request url ve body'sini hazirlamak
        specDummy.pathParam("pp1","employees");
        Response response=given()
                .spec(specDummy)
                .when()
                .get("/{pp1}");
        response.prettyPrint();
       response.jsonPath().getList("data.id").stream().forEach(t-> System.out.println(t));
        System.out.println( response.jsonPath().getList("data.id").get(0));
        // 2- Expected Data'yi hazirla

        // 3- Response'u kaydet
        // 4- Assertion'lari yapr
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", Matchers.hasSize(24));
    }
}
