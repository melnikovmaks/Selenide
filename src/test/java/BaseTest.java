import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

abstract public class BaseTest {
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "92.1";
        Configuration.screenshots = true;
        Configuration.browserSize = "1600x1000";
        Configuration.headless = false;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        Configuration.browserCapabilities = options;
    }

    @BeforeEach
    @Before
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearThis() {
        System.out.println("@AfterEach executed");
    }
}
