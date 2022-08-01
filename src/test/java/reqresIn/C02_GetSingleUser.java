package reqresIn;

import baseUrls.BaseHttpBin;
import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02_GetSingleUser extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/users/2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users/2").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        //epx data
        /*
        {
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}

         */
        JSONObject exp=new JSONObject();
        JSONObject data=new JSONObject();
        JSONObject support=new JSONObject();
        support.put("url", "https://reqres.in/#support-heading");
        support.put("text", "To keep ReqRes free, contributions towards server costs are appreciated!");
        data.put("id", 2);
        data.put("email", "janet.weaver@reqres.in");
        data.put("first_name", "Janet");
        data.put("last_name", "Weaver");
        data.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
        exp.put("data",data);
        exp.put("support",support);
        //save response
        JsonPath act=response.jsonPath();
        //Assertions
        response
                .then()
                .assertThat()
                .statusCode(200);
           /*
        {
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}

         */
        assertEquals(exp.getJSONObject("data").get("id"),act.get("data.id"));
        assertEquals(exp.getJSONObject("data").get("email"),act.get("data.email"));
        assertEquals(exp.getJSONObject("data").get("first_name"),act.get("data.first_name"));
        assertEquals(exp.getJSONObject("data").get("last_name"),act.get("data.last_name"));
        assertEquals(exp.getJSONObject("data").get("avatar"),act.get("data.avatar"));
        assertEquals(exp.getJSONObject("support").get("url"),act.get("support.url"));
        assertEquals(exp.getJSONObject("support").get("text"),act.get("support.text"));
    }
    @Test
    public void res() {//"https://reqres.in/api/users/2"
        specReqres.pathParams("pp1","api","pp2","users","pp3",2);
        Response response=given()
                .spec(specReqres)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();
        //epx data
        /*
        {
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}

         */
        JSONObject exp=new JSONObject();
        JSONObject data=new JSONObject();
        JSONObject support=new JSONObject();
        support.put("url", "https://reqres.in/#support-heading");
        support.put("text", "To keep ReqRes free, contributions towards server costs are appreciated!");
        data.put("id", 2);
        data.put("email", "janet.weaver@reqres.in");
        data.put("first_name", "Janet");
        data.put("last_name", "Weaver");
        data.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
        exp.put("data",data);
        exp.put("support",support);
        //save response
        JsonPath act=response.jsonPath();
        //Assertions
        response
                .then()
                .assertThat()
                .statusCode(200);
           /*
        {
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}

         */
        assertEquals(exp.getJSONObject("data").get("id"),act.get("data.id"));
        assertEquals(exp.getJSONObject("data").get("email"),act.get("data.email"));
        assertEquals(exp.getJSONObject("data").get("first_name"),act.get("data.first_name"));
        assertEquals(exp.getJSONObject("data").get("last_name"),act.get("data.last_name"));
        assertEquals(exp.getJSONObject("data").get("avatar"),act.get("data.avatar"));
        assertEquals(exp.getJSONObject("support").get("url"),act.get("support.url"));
        assertEquals(exp.getJSONObject("support").get("text"),act.get("support.text"));
    }
}
