import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;


abstract public class BaseTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.screenshots = true;
        Configuration.browserSize = "1600x1000";
        Configuration.headless = false;
        Configuration.baseUrl = "http://automationpractice.com/";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        Configuration.browserCapabilities = options;
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("@AfterAll executed");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("@AfterEach executed");
        Selenide.closeWindow();
    }
}
