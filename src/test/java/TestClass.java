import com.codeborne.selenide.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class TestClass {
    public static String baseUrl = "http://automationpractice.com/";

    @Before
    public void setup() {
        Configuration.baseUrl = baseUrl;
        Configuration.timeout = 4000;
        Configuration.pollingInterval = 200;
        Configuration.holdBrowserOpen = false;
        Configuration.reopenBrowserOnFail = true;
        Configuration.browser = "chrome";
        Configuration.screenshots = true;
        Configuration.browserSize = "1600x1000";
        Configuration.pageLoadStrategy = "normal";
        Configuration.pageLoadTimeout = 20000;
        Configuration.clickViaJs = false;
        Configuration.savePageSource = true;
        Configuration.fastSetValue = true;
        Configuration.headless = false;
    }

    @Test
    public void test1() {
        open(baseUrl);
        $("[alt^='Faded']").hover();
        $$(".lnk_view > span").get(0).click();
        $("#color_13").click();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("color-orange"));
        $(".color_pick:not(#color_13)").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("color-blue"));
        $("#group_1").click();
        $("[title='M']").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("size-m"));
        $("#group_1").click();
        $("[title='L']").click();
    }

    @Test
    public void test2() {
        open("http://automationpractice.com/");
        $(".login").click();
        $("#email").setValue("ska@mail.ru");
        $("#passwd").setValue("Qwerty123").pressEnter();
        Assert.assertEquals("Maksim test", $(".account").getText());
    }

    @Test
    public void test3() {
        open("http://automationpractice.com/");
        $("#newsletter-input").setValue("test@gmail.com").pressEnter();
        Assert.assertEquals("Newsletter : This email address is already registered.", $(".alert").getText());
        String randomEmail = "abcdefghijklmnopqrstuvwxyz";
        char[] chArray = randomEmail.toCharArray();
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append(chArray[random.nextInt(chArray.length)]);
        }
        buffer.append("@gmail.com");
        randomEmail = buffer.toString();
        $("#newsletter-input").setValue(randomEmail).pressEnter();
        Assert.assertEquals("Newsletter : You have successfully subscribed to this newsletter.", $(".alert").getText());
        $("#newsletter-input").setValue(randomEmail).pressEnter();
        Assert.assertEquals("Newsletter : This email address is already registered.", $(".alert").getText());

    }

    @Test
    public void test4() {
        open("http://automationpractice.com/");
        $("[alt='Blouse']").hover();
        $$(".lnk_view span").get(1).click();
        $("#quantity_wanted").setValue("3");
        $("#group_1").click();
        $("[title='L']").click();
        $("#color_11").click();
        $("#add_to_cart > button").click();
        $(".button-medium").click();
        Assert.assertEquals("Color : Black, Size : L", $("small:nth-child(3) a").getText());
        Assert.assertEquals("3", $(".cart_quantity_input").getValue());
    }

    @Test
    public void test5() {
        open("http://automationpractice.com/");
        $$(".sf-with-ul").get(0).click();
        $("[alt$='shirts']").hover();
        $$(".add_to_compare").get(0).click();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        $("[alt='Blouse']").hover();
        SelenideElement element = $$(".add_to_compare").get(1);
        element.should(Condition.visible, Duration.ofSeconds(5)).click();
        $("[alt='Printed Dress']").hover();
        element = $$(".add_to_compare").get(2);
        element.should(Condition.visible, Duration.ofSeconds(5)).click();
        $(".bt_compare").click();
        Assert.assertTrue($$("a.product-name").get(0).getText().contains("Faded Short Sleeve T-shirts"));
        Assert.assertTrue($$("a.product-name").get(1).getText().contains("Blouse"));
        Assert.assertTrue($$("a.product-name").get(2).getText().contains("Printed Dress"));
    }
}
