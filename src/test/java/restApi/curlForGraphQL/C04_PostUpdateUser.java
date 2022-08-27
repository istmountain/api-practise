package restApi.curlForGraphQL;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class C04_PostUpdateUser {
    @Test
    public void HTTP() throws IOException {
        /*
        Update user
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPATCH "https://gorest.co.in/public/v2/users/9"
-d '{"name":"Allasani Peddana", "email":"allasani.peddana@15ce.com", "status":"active"}'
         */
              /*
        {
    "email": "allasani.peddana@15ce.com",
    "name": "Allasani Peddana",
    "status": "active",
    "id": 9,
    "gender": "male"
} Response
         */
        URL url = new URL("https://gorest.co.in/public/v2/users/9");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PATCH");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer acc5b803a5df9bc89143ebc8a78d79e0c7dd9ea02e0f98717e2cf22dd60fac79");

        String data = "{\"name\":\"Allasani Peddana\", \"email\":\"allasani.peddana@15ce.com\", \"status\":\"active\"}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

    }

    @Test
    public void req() {
             /*
        Update user
curl -i -H "Accept:application/json" -H "Content-Type:application/json"
-H "Authorization: Bearer ACCESS-TOKEN" -XPATCH "https://gorest.co.in/public/v2/users/9"
-d '{"name":"Allasani Peddana", "email":"allasani.peddana@15ce.com", "status":"active"}'
         */

    }
}
