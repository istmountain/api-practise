package httpBin;

import baseUrls.BaseHttpBin;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_DeleteRequest extends BaseHttpBin {
    @Test
    public void name() {
        /*
        Curl
curl -X DELETE "http://httpbin.org/delete" -H "accept: application/json"
Request URL
http://httpbin.org/delete
         */
        specHttpbin.pathParam("pp1","delete");
        Response response=given()
                .spec(specHttpbin)
                .accept(ContentType.JSON)
                .when()
                .delete("/{pp1}");
        response.prettyPrint();
        //expData
        /*
         "headers": {
        "Accept": "application/json, application/javascript, text/javascript, text/json",
        "Accept-Encoding": "gz1p,deflate",
        "Host": "httpbin.org",
        "User-Agent": "Apache-HttpClient/4.5.3 (Java/18.0.1.1)",
        "X-Amzn-Trace-Id": "Root=1-62db0084-7448457262a1e293071f9851"
    },
    "json": null,
    "origin": "88.236.86.164",
    "url": "http://httpbin.org/delete"
}
         */
        JsonPath actual=response.jsonPath();
        response.then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals("88.236.86.164",actual.get("origin"));
        Assert.assertEquals("http://httpbin.org/delete",actual.get("url"));

    }
}
