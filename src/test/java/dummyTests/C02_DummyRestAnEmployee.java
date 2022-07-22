package dummyTests;

import baseUrls.BaseDummyRestApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_DummyRestAnEmployee extends BaseDummyRestApi {
    @Test
    public void name() {
        //https://dummy.restapiexample.com/api/v1/employee/1
        specDummy.pathParams("pp1","employee","pp2","21");
        Response response=given()
                .spec(specDummy)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        //expData
        JSONObject expData=new JSONObject();
        JSONObject inner=new JSONObject();
        /*
        {
    "status": "success",
    "data": {
        "id": 21,
        "employee_name": "Jenette Caldwell",
        "employee_salary": 345000,
        "employee_age": 30,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}
         */
        inner.put("id",21);
        inner.put("employee_name","Jenette Caldwell");
        inner.put("employee_salary", 345000);
        inner.put("employee_age", 30);
        inner.put("profile_image","");
        expData.put("status", "success");
        expData.put("data",inner);
        expData.put("message", "Successfully! Record has been fetched.");
        //save response
        JsonPath actual=response.jsonPath();
        //Assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(expData.get("status"),actual.get("status"));
        Assert.assertEquals(expData.getJSONObject("data").get("id"),actual.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"),actual.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"),actual.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_age"),actual.get("data.employee_age"));
        Assert.assertEquals(expData.getJSONObject("data").get("profile_image"),actual.get("data.profile_image"));
        Assert.assertEquals(expData.get("message"),actual.get("message"));

    }
}
