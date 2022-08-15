package trelloRestApi;

import baseUrls.BaseTrello;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetanActionWithId extends BaseTrello {
    //"https://api.trello.com/1/actions/{id}"62d7e33f30d34c3a25a2fc28   https://api.trello.com/1/actions/62d7e33f30d34c3a25a2fc28

    @Test
    public void http() {

    }
    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.trello.com/1/actions/62d7e33f30d34c3a25a2fc28").setAccept(ContentType.JSON)
        .addHeader("Authorization", "Bearer mt0dgHmLJMVQhvjpNXDyA83vA_PxH23Y")
        .build()
        .queryParams("key","6e0405633cf470268d0a6579d0082fa8","token","27726bdc8dee5827e17fdd1b4b3148acd6eeec8e8523b5e2b43b84d09e7a0dc6");
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();

    }
    @Test
    public void res() {
    }
}
