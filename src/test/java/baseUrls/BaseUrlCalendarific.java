package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlCalendarific {
   protected RequestSpecification specCalendar;
    @Before
    public void setup(){
        specCalendar=new RequestSpecBuilder().setBaseUri("https://calendarific.com/api/v2").build();
    }
}
