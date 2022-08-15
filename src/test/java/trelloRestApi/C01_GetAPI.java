package trelloRestApi;

import baseUrls.BaseTrello;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C01_GetAPI extends BaseTrello {
    /*
    key=6e0405633cf470268d0a6579d0082fa8
token=27726bdc8dee5827e17fdd1b4b3148acd6eeec8e8523b5e2b43b84d09e7a0dc6
key1 = 8fc757f0282852be46596d146f4b3b1b
token1 = 30734891211be87438de1e97a03c0185e29b0b8c40be8b12937123da12c85c53
zoken=27726bdc8dee5827e17fdd1b4b3148acd6eeec8e8523b5e2b43b84d09e7a0dc6
"id": "62d7e33f30d34c3a25a2fc28",
https://api.trello.com/1/members/ecenarin1
"https://api.trello.com/1/actions/{id}"
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("https://api.trello.com/1/members/ecenarin1");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Authorization", "Bearer mt0dgHmLJMVQhvjpNXDyA83vA_PxH23Y");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.trello.com/1/members/ecenarin1")
                .addHeader("Authorization", "Bearer mt0dgHmLJMVQhvjpNXDyA83vA_PxH23Y").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);


    }
    @Test
    public void res() {
        //https://api.trello.com/1/members/ecenarin1
        specTrello.pathParams("pp1",1,"pp2","members","pp3","ecenarin1");
        Response response=given()
                .accept(ContentType.JSON)
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        response.then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo("62d7e33f30d34c3a25a2fc28"));


    }
}
