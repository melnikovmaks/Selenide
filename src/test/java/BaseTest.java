import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;

abstract public class BaseTest {

    @BeforeAll
    public static void setAll() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "92.1";
        Configuration.screenshots = true;
        Configuration.browserSize = "1600x1000";
        Configuration.headless = false;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        Configuration.browserCapabilities = options;
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("@AfterAll executed");
    }

    @AfterEach
    public void tearThis() {
        System.out.println("@AfterEach executed");
        closeWebDriver();
    }
}
