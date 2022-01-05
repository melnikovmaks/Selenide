import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClass extends BaseTest {
    public static String baseUrl = "http://automationpractice.com/";

    @Disabled("It is not useful test")
    @Test
    public void test() {
        open(baseUrl);
        $$(".item-img").should(CollectionCondition.size(7)).get(4).scrollTo();
    }

    @RepeatedTest(2)
    @DisplayName("Choice of color and size")
    @Order(3)
    public void test1() {
        open(baseUrl);
        $("[alt^='Faded']").shouldBe(Condition.appear, Duration.ofSeconds(20)).hover();
        $$(".lnk_view > span").should(CollectionCondition.size(14)).get(0).click();
        $("#color_13").shouldNot(Condition.image).click();
        Assertions.assertEquals($("#color_13").getLocation(), new Point(1136, 640));//1019
        Assertions.assertEquals($("#color_13").getSize(), new Dimension(22, 22));
        Assertions.assertEquals($("#color_13").getCssValue("background-color"), "rgba(243, 156, 17, 1)");
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.endsWith("color-orange"));
        $(".color_pick:not(#color_13)").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("color-blue"));
        SelenideElement element = $("#group_1");
        if (element.is(Condition.name("group_1"))) {
            element.click();
        }
        $("[title='M']").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("size-m"));
        $("#group_1").shouldNotHave(Condition.exactText("Hello")).click();
        $("[title='L']").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("size-l"));
    }

    @Tag("mail")
    @ParameterizedTest
    @DisplayName("Login")
    @Order(4)
    @CsvSource({
            "ska@mail.ru, Qwerty123, Maksim test",
            "test22@gamil.com, Qwerty123, test test"
    })
    public void test2(String mail, String password, String message) {
        open(baseUrl);
        $(".login").shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        $("#email").shouldBe(Condition.empty).setValue(mail);
        $("#passwd").shouldBe(Condition.empty).setValue(password).pressEnter();
        Assertions.assertEquals(message, $(".account").shouldBe(Condition.exist,
                Duration.ofSeconds(20)).getText());
    }

    @Tag("mail")
    @Test
    @DisplayName("Adding an email to the newsletter")
    @Order(5)
    public void test3() {
        open(baseUrl);
        $("#newsletter-input").shouldBe(Condition.name("email")).setValue("test@gmail.com").pressEnter();
        Assertions.assertEquals("Newsletter : This email address is already registered.",
                $(".alert").getText());
        String randomEmail = "abcdefghijklmnopqrstuvwxyz";
        char[] chArray = randomEmail.toCharArray();
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append(chArray[random.nextInt(chArray.length)]);
        }
        buffer.append("@gmail.com");
        randomEmail = buffer.toString();
        $("#newsletter-input").shouldBe(Condition.name("email")).setValue(randomEmail).pressEnter();
        Assertions.assertEquals("Newsletter : You have successfully subscribed to this newsletter.",
                $(".alert").getText());
        $("#newsletter-input").shouldBe(Condition.name("email")).setValue(randomEmail).pressEnter();
        Assertions.assertEquals("Newsletter : This email address is already registered.",
                $(".alert").getText());

    }

    @Test
    @DisplayName("Adding a product with parameters to the cart")
    @Order(1)
    public void test4() {
        open(baseUrl);
        $("[alt='Blouse']").shouldBe(Condition.enabled, Duration.ofSeconds(20)).hover();
        $$(".lnk_view span").should(CollectionCondition.size(14)).get(1).click();
        $("#quantity_wanted").shouldBe(Condition.name("qty")).setValue("3");
        $("#group_1").shouldBe(Condition.name("group_1")).click();
        $("[title='L']").click();
        $("#color_11").shouldBe(Condition.name("Black")).click();
        $("#add_to_cart > button").click();
        $(".button-medium").shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        Assertions.assertEquals("Color : Black, Size : L", $("small:nth-child(3) a")
                .shouldBe(Condition.exist, Duration.ofSeconds(20)).getText());
        Assertions.assertEquals("3", $(".cart_quantity_input").shouldBe(Condition.exist).getValue());
        closeWebDriver();
    }

    @Test
    @DisplayName("Adding products to compare")
    @Order(2)
    public void test5() {
        open(baseUrl);
        $$(".sf-with-ul").should(CollectionCondition.size(4), Duration.ofSeconds(20)).get(0).click();
        $("[alt$='shirts']").should(Condition.visible).hover();
        $$(".add_to_compare").should(CollectionCondition.size(7)).get(0).click();
        $(".total-compare-val").shouldHave(Condition.text("1"), Duration.ofSeconds(20));
        $("[alt='Blouse']").should(Condition.visible, Duration.ofSeconds(10)).hover();
        $$(".add_to_compare").should(CollectionCondition.size(7), Duration.ofSeconds(10)).get(1).click();
        $(".total-compare-val").shouldHave(Condition.text("2"), Duration.ofSeconds(20));
        $("[alt='Printed Dress']").should(Condition.visible).hover();
        $$(".add_to_compare").should(CollectionCondition.size(7)).get(2).click();
        $(".bt_compare").click();
        Assertions.assertTrue($$("a.product-name").get(0).getText().contains("Faded Short Sleeve T-shirts"));
        Assertions.assertTrue($$("a.product-name").get(1).getText().contains("Blouse"));
        Assertions.assertTrue($$("a.product-name").get(2).getText().contains("Printed Dress"));

    }
}
