package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlGorest {
    protected RequestSpecification specGorest;
    @Before
    public void setup(){
        specGorest= new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v2").build();
    }
}
