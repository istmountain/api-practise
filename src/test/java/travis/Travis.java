package travis;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Travis {
    /*
    curl -H "Travis-API-Version: 3" -H "User-Agent: API Explorer" \
  -H "Authorization: token 3RhyhGHITKycyCL-ZjGeGg" \
  https://api.travis-ci.org/user
     */

    @Test
    public void name() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.travis-ci.org/user").addHeader("Authorization","token 3RhyhGHITKycyCL-ZjGeGg")
                .addHeader("User-Agent","API Explorer").addHeader("Travis-API-Version","3").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
    }
}
