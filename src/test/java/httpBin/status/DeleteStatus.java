package httpBin.status;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteStatus {
    @Test
    public void reqDelete() {
    }
    @Test
    public void resDelete() {
    }
    @Test
    public void httpDelete() throws IOException {
        URL url = new URL("http://httpbin.org/status/200");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Authorization", "Basic ZWNlOjEyMzQ1Njc=");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
