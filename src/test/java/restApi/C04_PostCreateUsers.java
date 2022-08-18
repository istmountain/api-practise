package restApi;

import baseUrls.BaseUrlGorest;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class C04_PostCreateUsers extends BaseUrlGorest {
    /*
    Create user
curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:
 Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/users" -d '
 {"name":"Tenali Ramakrishna", "gender":"male",
 "email":"tenali.ramakrishna@15ce.com", "status":"active"}
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("https://gorest.co.in/public/v2/users");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"name\":\"Tenali Ramakrishna\",\n \"gender\":\"male\",\n \"email\":\"tenali898.ramakrishna@15ce.com\", \n \"status\":\"active\"}\n";

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
