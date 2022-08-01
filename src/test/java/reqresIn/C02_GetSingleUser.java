package reqresIn;

import baseUrls.BaseHttpBin;
import baseUrls.BaseReqresIn;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

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
    }
    @Test
    public void res() {
    }
}
