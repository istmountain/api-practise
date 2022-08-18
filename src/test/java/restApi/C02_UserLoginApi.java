package restApi;

import baseUrls.BaseRestApi;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class C02_UserLoginApi extends BaseRestApi {
    /*
    http://restapi.adequateshop.com/api/authaccount/login
    st/api/authaccount/login
API Request
{
	"email":"Developer5@gmail.com",
	"password":123456
}
API Response

{
    "$id": "1",
    "code": 0,
    "message": "success",
    "data": {
        "$id": "2",
        "Id": 7075,
        "Name": "Developer",
        "Email": "Developer5@gmail.com",
        "Token": "02b869e4-ea45-4b5c-b764-642a39e95bb7"
    }
}
If email or password is wrong then API Response

{
    "$id": "1",
    "code": 1,
    "message": "invalid username or password",
    "data": null
}
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://restapi.adequateshop.com/api/authaccount/login");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n     \"email\":\"Developer5@gmail.com\",\n            \"password\":123456\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
    }
    @Test
    public void res() {
    }
}
