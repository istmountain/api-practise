package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlPet {
    protected RequestSpecification specPet;
    @Before
    public void setup(){
        specPet=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
    }
}
