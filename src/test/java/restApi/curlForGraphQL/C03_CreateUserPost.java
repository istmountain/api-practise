package restApi.curlForGraphQL;

import baseUrls.BaseUrlGorest;
import org.junit.Test;

public class C03_CreateUserPost extends BaseUrlGorest {
    /*
    Create User
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"mutation{createUser(input: {name: \"Tenali Ramakrishna\" gender: \"male\" email: \"tenali.ramakrishna@15ce.com\" status: \"active\"}) {user{id name gender email status}}}"}'
     */

    @Test
    public void http() {

    }
    @Test
    public void req() {
    }
    @Test
    public void res() {
    }

}
