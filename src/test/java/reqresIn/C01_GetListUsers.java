package reqresIn;

import baseUrls.BaseReqresIn;
import com.google.gson.JsonArray;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01_GetListUsers extends BaseReqresIn {
    /*

     */

    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/users?page=2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users?page=2").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        //expData
        JSONObject exp=new JSONObject();
        JSONArray data=new JSONArray();
        JSONObject inner =new JSONObject();
        inner.put("id", 1);
        inner.put("email","george.bluth@reqres.in");
        inner.put("first_name", "George");
        inner.put("last_name", "Bluth");
        inner.put("avatar", "https://reqres.in/img/faces/1-image.jpg");
        data.put(0,inner);
        exp.put("page", 1);
        exp.put("per_page", 6);
        exp.put("total", 12);
        exp.put("total_pages", 2);
        exp.put("data",data);
        System.out.println(exp);
        /*
        {
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
            "avatar": "https://reqres.in/img/faces/1-image.jpg"
        },
         */
        //save response
        JsonPath act=response.jsonPath();
        //assert
        assertEquals(exp.get("page"),act.get("page"));
        assertEquals(exp.get("per_page"),act.get("per_page"));
        assertEquals(exp.get("total"),act.get("total"));
        assertEquals(exp.get("total_pages"),act.get("total_pages"));
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("id"),( ( (Map)act.getList("data").get(0)).get("id")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("email"),( ( (Map)act.getList("data").get(0)).get("email")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("first_name"),( ( (Map)act.getList("data").get(0)).get("first_name")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("last_name"),( ( (Map)act.getList("data").get(0)).get("last_name")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("avatar"),( ( (Map)act.getList("data").get(0)).get("avatar")) );

    }
    @Test
    public void res() { //https://reqres.in/api/users?page=2
        specReqres.pathParams("pp1","api","pp2","users").queryParam("page",2);
        Response response=given()
                .spec(specReqres)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();
        //expData
        JSONObject exp=new JSONObject();
        JSONArray data=new JSONArray();
        JSONObject inner =new JSONObject();
        inner.put("id", 7);
        inner.put("email","michael.lawson@reqres.in");
        inner.put("first_name", "Michael");
        inner.put("last_name", "Lawson");
        inner.put("avatar", "https://reqres.in/img/faces/7-image.jpg");
        data.put(0,inner);
        exp.put("page", 2);
        exp.put("per_page", 6);
        exp.put("total", 12);
        exp.put("total_pages", 2);
        exp.put("data",data);
        System.out.println(exp);
        /*
        {
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
            "avatar": "https://reqres.in/img/faces/1-image.jpg"
        },
         */
        //save response
        JsonPath act=response.jsonPath();
        //assert
        assertEquals(exp.get("page"),act.get("page"));
        assertEquals(exp.get("per_page"),act.get("per_page"));
        assertEquals(exp.get("total"),act.get("total"));
        assertEquals(exp.get("total_pages"),act.get("total_pages"));
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("id"),( ( (Map)act.getList("data").get(0)).get("id")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("email"),( ( (Map)act.getList("data").get(0)).get("email")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("first_name"),( ( (Map)act.getList("data").get(0)).get("first_name")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("last_name"),( ( (Map)act.getList("data").get(0)).get("last_name")) );
        assertEquals(  exp.getJSONArray("data").getJSONObject(0).get("avatar"),( ( (Map)act.getList("data").get(0)).get("avatar")) );

    }
}
