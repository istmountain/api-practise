package reqresIn;

import baseUrls.BaseReqresIn;
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

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C15_DelayedResponse extends BaseReqresIn {
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/users?delay=3");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Bearer mt0dgHmLJMVQhvjpNXDyA83vA_PxH23Y");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users?delay=3").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        /*[
             {
            "id": 6,
            "email": "tracey.ramos@reqres.in",
            "first_name": "Tracey",
            "last_name": "Ramos",
            "avatar": "https://reqres.in/img/faces/6-image.jpg"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
         */
        JSONObject exp=new JSONObject();
        JSONArray data=new JSONArray();
        JSONObject users=new JSONObject();
        JSONObject support=new JSONObject();
        users.put("id", 1);
        users.put("email", "george.bluth@reqres.in");
        users.put("first_name", "George");
        users.put("last_name", "Bluth");
        users.put("avatar", "https://reqres.in/img/faces/1-image.jpg");
        support.put("url", "https://reqres.in/#support-heading");
        support.put("text", "To keep ReqRes free, contributions towards server costs are appreciated!");
        data.put(0,users);
        exp.put("data",data);
        exp.put("support",support);
        //save response
        JsonPath act=response.jsonPath();
        //assert
        assertEquals();

    }
    @Test
    public void res() {
        /*
         {
            "id": 4,
            "email": "eve.holt@reqres.in",
            "first_name": "Eve",
            "last_name": "Holt",
            "avatar": "https://reqres.in/img/faces/4-image.jpg"
        },
        {
            "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
        },
         */
    }
}
