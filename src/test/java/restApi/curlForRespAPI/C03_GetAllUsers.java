package restApi.curlForRespAPI;

import baseUrls.BaseRestApi;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_GetAllUsers extends BaseRestApi {
    /*
    Get All Users
This API returns all users in the organization

http://restapi.adequateshop.com/api/users?page=1

GET/api/users?page=1
API Response

{
    "$id": "1",
    "page": 1,
    "per_page": 10,
    "totalrecord": 2812,
    "total_pages": 282,
    "data": [
        {
            "$id": "2",
            "id": 1,
            "name": "ashish thapliyal",
            "email": "Ashulive6123@gmail.com",
            "profilepicture": "https://www.adequatetravel.com/ATMultimedia/Images/1a30600f-3b07-4797-b883-981b455f2e84.png",
            "location": "USA",
     */

    @Test
    public void name() {
        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("http://restapi.adequateshop.com/api/users?page=1").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);

    }
}
