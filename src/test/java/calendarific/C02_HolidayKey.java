package calendarific;

import baseUrls.BaseUrlCalendarific;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02_HolidayKey extends BaseUrlCalendarific {
    /*
    https://calendarific.com/api/v2/holidays?api_key=baa9dc110aa712sd3a9fa2a3dwb6c01d4c875950dc32vs&country=US&year=2019
     */

    @Test
    public void holiday() {
        specCalendar.pathParam("pp1","holidays").queryParams("api_key","5cb793b30517a6a29591989fb92f22d421bbb521","country","US","year",2019);
       Response response=given()
               .spec(specCalendar)
               .when()
               .get("/{pp1}");
       response.prettyPrint();
       //assert
        int totalSize=response.jsonPath().getList("response.holidays").size();
        System.out.println(totalSize);
        response
                 .then()
                 .assertThat()
                 .statusCode(200);
       assertEquals(totalSize,response.jsonPath().getList("response.holidays").size());
    }
}
