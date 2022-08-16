package trelloRestApi.actions;

import baseUrls.BaseTrello;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_UpdateActions extends BaseTrello {
    //// This code sample uses the  'Unirest' library:
    //// http://unirest.io/java.html
    //HttpResponse<String> response = Unirest.put("https://api.trello.com/1/actions/62d7e33f30d34c3a25a2fc28")
    //  .queryString("text", "{text}")
    //  .queryString("key", "APIKey")
    //  .queryString("token", "APIToken")
    //  .asString();

    @Test
    public void req() {
        //"id": "62d7e33f30d34c3a25a2fc28",
        //alper id 5a69ec76886d27b15045762f
        //    key=6e0405633cf470268d0a6579d0082fa8
        //token=27726bdc8dee5827e17fdd1b4b3148acd6eeec8e8523b5e2b43b84d09e7a0dc6
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.trello.com/1/actions/62d7e33f30d34c3a25a2fc28")
                .setAccept(ContentType.JSON)
                .addQueryParam("text", "merhaba")
                .addQueryParam("key", "6e0405633cf470268d0a6579d0082fa8")
                .addQueryParam("token", "27726bdc8dee5827e17fdd1b4b3148acd6eeec8e8523b5e2b43b84d09e7a0dc6")
                .build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
    }
}
