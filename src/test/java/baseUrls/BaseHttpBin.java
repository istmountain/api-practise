package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseHttpBin {
    protected RequestSpecification specHttpbin;
    @Before
    public void setup(){
        specHttpbin=new RequestSpecBuilder().setBaseUri("http://httpbin.org").build();
    }
   ///
}
