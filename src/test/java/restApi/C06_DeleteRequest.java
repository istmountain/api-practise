package restApi;

import baseUrls.BaseRestApi;
import baseUrls.BaseUrlGorest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C06_DeleteRequest extends BaseUrlGorest {
    /*
    Delete user
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN"
-XDELETE "https://gorest.co.in/public/v2/users/24"
     */

    @Test
    public void http() throws IOException {
           /*
    Delete user
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN"
-XDELETE "https://gorest.co.in/public/v2/users/24"
     */
        URL url = new URL("https://gorest.co.in/public/v2/users/3697");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
           /*
    Delete user
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN"
-XDELETE "https://gorest.co.in/public/v2/users/24"
     */
        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/users/3697")
                .setAccept("application/json")
                .setAccept("application/json")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .build();
        Response response=given()
                .spec(req)
                .when().delete();
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void res() {
            /*
    Delete user
curl -i -H "Accept:application/json"
-H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN"
-XDELETE "https://gorest.co.in/public/v2/users/24"
     */
             specGorest.pathParams("pp1","users","pp2","3702");
        Response response=given()
                .spec(specGorest)
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .when().delete();
        response
                .then()
                .assertThat()
                .statusCode(200);
    }
}
