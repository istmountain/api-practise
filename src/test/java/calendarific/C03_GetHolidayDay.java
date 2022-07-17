package calendarific;

import baseUrls.BaseUrlCalendarific;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_GetHolidayDay extends BaseUrlCalendarific {
    /*
     {
     specCalendar.pathParam("pp1","holidays").queryParams("api_key","5cb793b30517a6a29591989fb92f22d421bbb521","country","US","year",2019);
     urlid": "world/world-braille-day
                "name": "New Year's Day",
                "description": "New Year's Day is the first day of the Gregorian calendar, which is widely used in many countries such as the USA.",
                "country": {
                    "id": "us",
                    "name": "United States"
                },
                "date": {
                    "iso": "2019-01-01",
                    "datetime": {
                        "year": 2019,
                        "month": 1,
                        "day": 1
                    }
                },
                "type": [
                    "National holiday"
                ],
                "urlid": "us/new-year-day",
                "locations": "All",
                "states": "All"
            },
     */

    @Test
    public void name() {
        specCalendar.pathParam("pp1","holidays").queryParams("api_key","5cb793b30517a6a29591989fb92f22d421bbb521","name", "Name of holiday goes here","year",2019);
        Response response=given()
                .spec(specCalendar)
                .when()
                .get("/{pp1}");
        response.prettyPrint();
    }
}
