package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseRestApi {
   protected RequestSpecification specRest;
    @BeforeTest
    public void setUp(){
        specRest=new RequestSpecBuilder().setBaseUri("http://restapi.adequateshop.com").build();
    }
}
