package httpBin.anything;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C06_DeleteAnything extends BaseHttpBin {
    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/anything");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + "http://httpbin.org/anything" + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/anything").setAccept("application/json").build();
        Response response=given()
                .spec(req)
                .when()
                .post();
        response.prettyPrint();
        //expected body
        JSONObject headers=new JSONObject();
        JSONObject expected=new JSONObject();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        expected.put("origin","176.42.164.88");
        expected.put("url", "http://httpbin.org/anything");
        expected.put("headers",headers);
        expected.put("json", "null");
        expected.put("method", "POST");
        // save response
        JsonPath actual=response.jsonPath();
        //assertions
        assertEquals(expected.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(expected.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(expected.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(expected.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(expected.get("origin"),actual.get("origin"));
        assertEquals(expected.get("url"),actual.get("url"));
        assertEquals(expected.get("method"),actual.get("method"));
        assertEquals(expected.get("json"),actual.get("json"));


    }
    @Test
    public void res() {
        //http://httpbin.org/anything
        specHttpbin.pathParam("pp1","anything");
        Response response=given()
                .spec(specHttpbin)
                .accept("application/json")
                .when()
                .post("/{pp1}");
        response.prettyPrint();
        //expected body
        JSONObject headers=new JSONObject();
        JSONObject expected=new JSONObject();
        headers.put("Accept", "application/json");
        headers.put("Accept-Encoding", "gz1p,deflate");
        headers.put("Host", "httpbin.org");
        headers.put("User-Agent", "Apache-HttpClient/4.5.3 (Java/18.0.1.1)");
        expected.put("origin","176.42.164.88");
        expected.put("url", "http://httpbin.org/anything");
        expected.put("headers",headers);
        expected.put("json", "null");
        expected.put("method", "POST");
        // save response
        JsonPath actual=response.jsonPath();
        //assertions
        assertEquals(expected.getJSONObject("headers").get("Accept"),actual.get("headers.Accept"));
        assertEquals(expected.getJSONObject("headers").get("Accept-Encoding"),actual.get("headers.Accept-Encoding"));
        assertEquals(expected.getJSONObject("headers").get("Host"),actual.get("headers.Host"));
        assertEquals(expected.getJSONObject("headers").get("User-Agent"),actual.get("headers.User-Agent"));
        assertEquals(expected.get("origin"),actual.get("origin"));
        assertEquals(expected.get("url"),actual.get("url"));
        assertEquals(expected.get("method"),actual.get("method"));
        assertEquals(expected.get("json"),actual.get("json"));


    }

}
