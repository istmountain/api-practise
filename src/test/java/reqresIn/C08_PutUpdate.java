package reqresIn;

import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C08_PutUpdate extends BaseReqresIn {
    /*
    Request
/api/users/2

{
    "name": "morpheus",
    "job": "zion resident"
}

Response
200

{
    "name": "morpheus",
    "job": "zion resident",
    "updatedAt": "2022-08-02T13:40:42.151Z"
}
     */
    @Test
    public void http() throws IOException {
        URL url = new URL("https://reqres.in/api/users/2");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n    \"name\": \"morpheus\",\n    \"job\": \"zion resident\"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
            /*
    Request
/api/users/2

{
    "name": "morpheus",
    "job": "zion resident"
}

Response
200

{
    "name": "morpheus",
    "job": "zion resident",
    "updatedAt": "2022-08-02T13:40:42.151Z"
}
     */
        JSONObject body=new JSONObject();
        body.put("name", "morpheus");
        body.put("job", "zion resident");
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users/2").setAccept(ContentType.JSON).setBody(body.toString()).build();
        Response response=given()
                .spec(req)
                .contentType(ContentType.JSON)
                .when()
                .put();
        response.prettyPrint();


        JSONObject exp=new JSONObject();
        exp.put("name", "morpheus");
        exp.put("job", "zion resident");
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        //save response
        JsonPath act=response.jsonPath();
        assertEquals(exp.get("name"),act.get("name"));
        assertEquals(exp.get("job"),act.get("job"));

    }
    @Test
    public void res() {
        //https://reqres.in/api/users
            /*
    Request
/api/users/2

{
    "name": "morpheus",
    "job": "zion resident"
}

Response
200

{
    "name": "morpheus",
    "job": "zion resident",
    "updatedAt": "2022-08-02T13:40:42.151Z"
}
     */
        specReqres.pathParams("pp1","api","pp2","users","pp3",2);
        JSONObject body=new JSONObject();
        body.put("name", "morpheus");
        body.put("job", "zion resident");
        Response response=given()
                .spec(specReqres)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .put("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();
        //response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200);
        //save response
        JSONObject exp=new JSONObject();
        exp.put("name", "morpheus");
        exp.put("job", "zion resident");
        JsonPath act=response.jsonPath();
        assertEquals(exp.get("name"),act.get("name"));
        assertEquals(exp.get("job"),act.get("job"));

    }

}
