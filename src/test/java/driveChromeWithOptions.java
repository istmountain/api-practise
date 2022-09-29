import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class driveChromeWithOptions {

    public static void main(String[] args) throws InterruptedException {

        String SUT_URL = "https://webrtc.github.io/samples/src/content/peerconnection/pc1/";
        String FAKE_DEVICE = "--use-fake-device-for-media-stream";
        String FAKE_UI = "--use-fake-ui-for-media-stream";
        String FAKE_VIDEO = "--use-file-for-fake-video-capture=src/BOMBOMBOMBOM.y4m";
        String FAKE_AUDIO = "--use-file-for-fake-audio-capture=src/BOMBOMBOMBOM.wav";
        ChromeOptions options = new ChromeOptions();
// Add some chrome Parameters at startup
        options.addArguments("--no-sandbox");
        options.addArguments(FAKE_DEVICE);
        options.addArguments(FAKE_UI);
        options.addArguments(FAKE_VIDEO);
        options.addArguments(FAKE_AUDIO);
// start-up Chromes
        System.setProperty("webdriver.chrome.driver","C:\\Users\\himer\\OneDrive\\Masaüstü\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.get(SUT_URL);
        driver.findElement(By.id("startButton")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@id=\"callButton\"]")).click();
//driver.quit();
    }
}
