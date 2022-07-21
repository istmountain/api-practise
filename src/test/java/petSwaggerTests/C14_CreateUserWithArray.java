package petSwaggerTests;

import baseUrls.BaseUrlPet;
import org.junit.Test;

public class C14_CreateUserWithArray extends BaseUrlPet {
    @Test
    public void name() {
        /*
        Curl

curl -X 'POST' \
  'https://petstore.swagger.io/v2/user/createWithArray' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string",
    "phone": "string",
    "userStatus": 0
  }
]'
Request URL
https://petstore.swagger.io/v2/user/createWithArray
         */
    }
}
