package httpBin.auth;

import baseUrls.BaseHttpBin;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import static io.restassured.RestAssured.digest;
import static io.restassured.RestAssured.given;

public class C04_DigestAuthQopUserPasswLogarithm extends BaseHttpBin {
    /*
    Curl
curl -X GET "http://httpbin.org/digest-auth/auth/ece/1234567/MD5" -H "accept: application/json"
Request URL
http://httpbin.org/digest-auth/auth/ece/1234567/MD5
Server response
Code	Details
200
Response body
Download
{
  "authenticated": true,
  "user": "ece"
}
     */
    /*
    Name	Description
qop
string
(path)
auth or auth-int

auth
user
string
(path)
ece
passwd
string
(path)
1234567
algorithm
string
(path)
MD5, SHA-256, SHA-512

MD5
Hash1=MD5(username:realm:password)
Hash2=MD5(method:digestURI)
response=MD5(Hash1:nonce:Hash2)

     */

    @Test
    public void digestAuthWithlogarithmREQ() throws NoSuchAlgorithmException {
        //request//http://httpbin.org/digest-auth/auth/ece/1234567/MD5

        RequestSpecification req= (RequestSpecification) new RequestSpecBuilder().setAuth(digest("ece", "1234567"))
                .setBaseUri("http://httpbin.org/digest-auth/auth/ece/1234567/MD5").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
        System.out.println(response.getStatusCode());
        //expData
        JSONObject exp=new JSONObject();
        /*
        {
  "authenticated": true,
  "user": "ece"
}
         */
        exp.put("authenticated",true);
        exp.put("user", "ece");
        //response
        JsonPath act=response.jsonPath();
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),act.get("authenticated"));
        Assert.assertEquals(exp.get("user"),act.get("user"));
    }
    @Test
    public void digestAuthWithLogarithmRES() {
        //request//http://httpbin.org/digest-auth/auth/ece/1234567/MD5
        Response response=given()
                .accept(ContentType.JSON)
                .auth()
                .digest("ece", "1234567")
                .when()
                .get("http://httpbin.org/digest-auth/auth/ece/1234567/MD5");
        //expData
        JSONObject exp=new JSONObject();
        exp.put("authenticated",true);
        exp.put("user", "ece");
        //response
        JsonPath act=response.jsonPath();
        //assert
        response
                .then()
                .assertThat()
                .statusCode(200);
        Assert.assertEquals(exp.get("authenticated"),act.get("authenticated"));
        Assert.assertEquals(exp.get("user"),act.get("user"));

    }
    @Test
    public void digestAuthWithLogarithmHTTP() {
    }
}
