package dummyTests;

import baseUrls.BaseDummyRestApi;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static io.restassured.RestAssured.given;

public class C04_Put extends BaseDummyRestApi {
    @Test
    public void name() {
        // 1 -Request url ve body'sini hazirlamak //https://dummy.restapiexample.com/api/v1/update/id
        specDummy.pathParams("pp1","update","pp2",15);
        /*
          {
            "id": 15,
            "employee_name": "Tatyana Fitzpatrick",
            "employee_salary": 385750,
            "employee_age": 19,
            "profile_image": ""
        }
         */
        JSONObject updateBody=new JSONObject();
        updateBody.put("id", 15);
        updateBody.put("employee_name", "Tatyana Fdkfjfjggrick");
        updateBody.put("employee_salary", 388855880);
        updateBody.put("employee_age", 32);
        updateBody.put("profile_image", "");

        Response response=given()
                .spec(specDummy)
                .contentType(ContentType.JSON)
                .body(updateBody.toString())
                .when()
                .put("/{pp1}/{pp2}");
       response.prettyPrint();
        // 2- Expected Data'yi hazirla
        /*
        {
    "status": "success",
    "data": {
        "profile_image": null,
        "employee_name": "Tatyana Fdkfjfjggrick",
        "employee_salary": 388855880,
        "id": 15,
        "employee_age": 32
    },
    "message": "Successfully! Record has been updated."
}

         */
        JSONObject expData=new JSONObject();
        JSONObject inner=new JSONObject();
        inner.put("employee_name","Tatyana Fdkfjfjggrick");
        inner.put("employee_salary", 388855880);
        inner.put("id", 15);
        inner.put("employee_age",32);
        expData.put("data",inner);
        expData.put("status","success");
        expData.put("message", "Successfully! Record has been updated.");
        // 3- Response'u kaydet
        JsonPath actual=response.jsonPath();
        // 4- Assertion'lari yap
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"),actual.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("id"),actual.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"),actual.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_age"),actual.get("data.employee_age"));
        Assert.assertEquals(expData.get("status"),actual.get("status"));
        Assert.assertEquals(expData.get("message"),actual.get("message"));
    }
}
