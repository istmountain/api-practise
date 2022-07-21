package petSwaggerTests;

import org.junit.Test;

public class C10_GetStoreOrderId {

    @Test
    public void name() {
        /*
        Curl

curl -X 'GET' \
  'https://petstore.swagger.io/v2/store/order/2' \
  -H 'accept: application/json'
Request URL
https://petstore.swagger.io/v2/store/order/2

Response body
Download
{
  "id": 2,
  "petId": 2,
  "quantity": 200,
  "shipDate": "2023-07-20T19:55:02.322+0000",
  "status": "placed",
  "complete": true
}
         */
    }
}
