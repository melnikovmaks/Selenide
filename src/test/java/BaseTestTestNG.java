import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseTestTestNG {

    @BeforeTest
    public void beforeTest() {
        Configuration.browser = "chrome";
        Configuration.screenshots = true;
        Configuration.browserSize = "1600x1000";
        Configuration.headless = false;
        Configuration.baseUrl = "http://automationpractice.com/";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        Configuration.browserCapabilities = options;
    }

    @AfterTest
    public void cleanUp() {
        System.out.println("@afterTest executed");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("@AfterEach executed");
        Selenide.closeWindow();
    }

    @DataProvider(name = "accounts")
    public Object[][] createAccounts() {
        return new Object[][] {
                { "ska@mail.ru", "Qwerty123", "Maksim test" },
                { "test22@gamil.com", "Qwerty123", "test test"},
        };
    }

    @AfterGroups("mail")
    public void setUpSecurity() {
        System.out.println("Test with mail");
    }
    @AfterGroups("addToCart")
    public void tearDownSecurity() {
        System.out.println("addToCart");
    }
}
