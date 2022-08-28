package restApi.curlForGraphQL;

import baseUrls.BaseUrlGorest;

public class C05_DeleteUser extends BaseUrlGorest {
    /*
    curl -i -H "Accept:application/json"
    -H "Content-Type:application/json"
    -H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql" ,
    -d '{"query":"mutation{deleteUser(input: {id: 9}){user {id name email gender status}}}"}'
     */
}
