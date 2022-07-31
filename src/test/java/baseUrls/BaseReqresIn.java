package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseReqresIn {
   protected RequestSpecification specReqres;
    @Before
    public void setUo(){
        specReqres=new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();

    }
}
