package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class BaseUrlReqbin {
    protected RequestSpecification specReqbin;

    @Before
    public void name() {
        specReqbin=new RequestSpecBuilder().setBaseUri("https://www.reqbin.com").build();
    }
}
