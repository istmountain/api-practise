package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlRestful {
    protected RequestSpecification specRestful;
    @Before
    public void setup(){
        specRestful=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();
    }
}
