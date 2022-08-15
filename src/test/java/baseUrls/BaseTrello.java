package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseTrello {
   protected RequestSpecification specTrello;
    @Before
    public void setUp(){
        specTrello=new RequestSpecBuilder().setBaseUri("https://api.trello.com").build();
    }
}
