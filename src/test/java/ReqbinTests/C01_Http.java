package ReqbinTests;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class C01_Http {
    @Test
    public void name() throws IOException {
        URL url = new URL("https://reqbin.com/echo/post/json");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Basic bGEuY3J5bW9zYTMyMUBnbWFpbC5jb206MTIzNDU2Nzg=");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n  \"Id\": 78912,\n  \"Customer\": \"Jason Sweet\",\n  \"Quantity\": 1,\n  \"Price\": 18.00\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
