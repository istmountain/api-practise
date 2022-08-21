package restApi.curlForGraphQL;

import baseUrls.BaseUrlGorest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class C01_ListUsers extends BaseUrlGorest {
    /*
    List users
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"query{users {pageInfo {endCursor startCursor hasNextPage hasPreviousPage} totalCount nodes {id name email gender status}}}"}'
     */

    @Test
    public void http() throws IOException {
          /*
    List users
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"query{users {pageInfo {endCursor startCursor hasNextPage hasPreviousPage} totalCount nodes {id name email gender status}}}"}'
     */

        URL url = new URL("https://gorest.co.in/public/v2/graphql");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");

        String data = "{\"query\":\"query{users {pageInfo {endCursor startCursor hasNextPage hasPreviousPage} totalCount nodes {id name email gender status}}}\"}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void req() {
          /*
    List users
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"query{users {pageInfo {endCursor startCursor hasNextPage hasPreviousPage} totalCount nodes {id name email gender status}}}"}'
     */
      //  JSONObject body=new JSONObject();
        String data = "{\"query\":\"query{users {pageInfo {endCursor startCursor hasNextPage hasPreviousPage} totalCount nodes {id name email gender status}}}\"}";

        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v2/graphql")
                .addHeader("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79")
                .setAccept("application/json")
                .setContentType("application/json")
                .build();
        Response response=given()
                .spec(req)
                .body(data)
                .when()
                .post();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void res() {
          /*
    List users
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/graphql"
-d '{"query":"query{users {pageInfo {endCursor startCursor hasNextPage hasPreviousPage} totalCount nodes {id name email gender status}}}"}'
"data": {
        "users": {
            "pageInfo": {
                "endCursor": "MjA",
                "startCursor": "MQ",
                "hasNextPage": true,
                "hasPreviousPage": false
            },
            "totalCount": 3292,
            "nodes": [{
                "id": 6037,
                "name": "Meenakshi Dutta DO",
                "email": "meenakshi_do_dutta@gleason.com",
                "gender": "male",
                "status": "inactive"
            },
     */

    }
    /*
    {
    "data": {
        "users": {
            "pageInfo": {
                "endCursor": "MjA",
                "startCursor": "MQ",
                "hasNextPage": true,
                "hasPreviousPage": false
            },
            "totalCount": 3292,
            "nodes": [{
                "id": 6037,
                "name": "Meenakshi Dutta DO",
                "email": "meenakshi_do_dutta@gleason.com",
                "gender": "male",
                "status": "inactive"
            }, {
                "id": 3394,
                "name": "Anjali Pandey",
                "email": "pandey_anjali@bechtelar.com",
                "gender": "male",
                "status": "inactive"
            }, {
                "id": 3390,
                "name": "Suman Gowda",
                "email": "suman_gowda@shanahan.co",
                "gender": "male",
                "status": "inactive"
            }, {
                "id": 3389,
                "name": "Adheesh Gill",
                "email": "adheesh_gill@gleichner.biz",
                "gender": "female",
                "status": "inactive"
            }, {
                "id": 3387,
                "name": "Narendra Butt",
                "email": "narendra_butt@turner.biz",
                "gender": "female",
                "status": "active"
            }, {
                "id": 3385,
                "name": "Ojaswini Khan Esq.",
                "email": "esq_ojaswini_khan@bosco.biz",
                "gender": "female",
                "status": "inactive"
            }, {
                "id": 3384,
                "name": "Akshata Johar Esq.",
                "email": "esq_akshata_johar@ortiz-harris.org",
                "gender": "female",
                "status": "active"
            }, {
                "id": 3383,
                "name": "Ganak Ahuja Sr.",
                "email": "ahuja_sr_ganak@boyer.name",
                "gender": "male",
                "status": "inactive"
            }, {
                "id": 3382,
                "name": "Prof. Aslesha Kaul",
                "email": "prof_kaul_aslesha@gleason.biz",
                "gender": "male",
                "status": "active"
            }, {
                "id": 3380,
                "name": "Girik Banerjee",
                "email": "girik_banerjee@morar-conn.name",
                "gender": "female",
                "status": "active"
            }, {
                "id": 3379,
                "name": "Girika Khan",
                "email": "khan_girika@dibbert.biz",
                "gender": "male",
                "status": "active"
            }, {
                "id": 3378,
                "name": "Chandrabhaga Shah",
                "email": "shah_chandrabhaga@bergnaum.org",
                "gender": "female",
                "status": "active"
            }, {
                "id": 3377,
                "name": "Amritambu Malik",
                "email": "malik_amritambu@konopelski.name",
                "gender": "male",
                "status": "inactive"
            }, {
                "id": 3376,
                "name": "Vasudeva Varma",
                "email": "vasudeva_varma@kozey-legros.com",
                "gender": "female",
                "status": "inactive"
            }, {
                "id": 3375,
                "name": "Bhasvan Desai",
                "email": "desai_bhasvan@mante.com",
                "gender": "male",
                "status": "active"
            }, {
                "id": 3374,
                "name": "Adityanandan Mehrotra",
                "email": "mehrotra_adityanandan@runolfsson.org",
                "gender": "male",
                "status": "inactive"
            }, {
                "id": 3373,
                "name": "Rep. Chaitan Desai",
                "email": "desai_chaitan_rep@walsh-heathcote.name",
                "gender": "male",
                "status": "inactive"
            }, {
                "id": 3372,
                "name": "Gov. Krishna Guneta",
                "email": "gov_guneta_krishna@greenholt.biz",
                "gender": "female",
                "status": "inactive"
            }, {
                "id": 3371,
                "name": "Sen. Suryakantam Bhat",
                "email": "suryakantam_bhat_sen@nienow.biz",
                "gender": "male",
                "status": "active"
            }, {
                "id": 3370,
                "name": "Bhavani Bhattathiri LLD",
                "email": "lld_bhattathiri_bhavani@reinger-schaden.biz",
                "gender": "male",
                "status": "inactive"
            }]
        }
    }
}
     */
}
