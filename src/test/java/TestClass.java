import com.codeborne.selenide.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class TestClass extends BaseTest {
    public static String baseUrl = "http://automationpractice.com/";

    @Test
    public void test(){
        open(baseUrl);
        $$(".item-img").should(CollectionCondition.size(7)).get(4).scrollTo();
    }

    @Test
    public void test1() {
        open(baseUrl);
        $("[alt^='Faded']").shouldBe(Condition.appear, Duration.ofSeconds(20)).hover();
        $$(".lnk_view > span").should(CollectionCondition.size(14)).get(0).click();
        $("#color_13").shouldNot(Condition.image).click();
        Assert.assertEquals($("#color_13").getLocation().toString(),"(1136, 640)");
        Assert.assertEquals($("#color_13").getSize().toString(),"(22, 22)");
        Assert.assertEquals($("#color_13").getCssValue("background-color"),"rgba(243, 156, 17, 1)");
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("color-orange"));
        $(".color_pick:not(#color_13)").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("color-blue"));
        SelenideElement element =$("#group_1");
        if(element.is(Condition.name("group_1"))){
            element.click();
        }
        $("[title='M']").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("size-m"));
        $("#group_1").shouldNotHave(Condition.exactText("Hello")).click();
        $("[title='L']").click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("size-l"));
    }

    @Test
    public void test2() {
        open("http://automationpractice.com/");
        $(".login").shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        $("#email").shouldBe(Condition.empty).setValue("ska@mail.ru");
        $("#passwd").shouldBe(Condition.empty).setValue("Qwerty123").pressEnter();
        Assert.assertEquals("Maksim test", $(".account").shouldBe(Condition.exist,
                Duration.ofSeconds(20)).getText());
    }

    @Test
    public void test3() {
        open("http://automationpractice.com/");
        $("#newsletter-input").shouldBe(Condition.name("email")).setValue("test@gmail.com").pressEnter();
        Assert.assertEquals("Newsletter : This email address is already registered.",
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
        Assert.assertEquals("Newsletter : You have successfully subscribed to this newsletter.",
                $(".alert").getText());
        $("#newsletter-input").shouldBe(Condition.name("email")).setValue(randomEmail).pressEnter();
        Assert.assertEquals("Newsletter : This email address is already registered.",
                $(".alert").getText());

    }

    @Test
    public void test4() {
        open("http://automationpractice.com/");
        $("[alt='Blouse']").shouldBe(Condition.enabled, Duration.ofSeconds(20)).hover();
        $$(".lnk_view span").should(CollectionCondition.size(14)).get(1).click();
        $("#quantity_wanted").shouldBe(Condition.name("qty")).setValue("3");
        $("#group_1").shouldBe(Condition.name("group_1")).click();
        $("[title='L']").click();
        $("#color_11").shouldBe(Condition.name("Black")).click();
        $("#add_to_cart > button").click();
        $(".button-medium").click();
        Assert.assertEquals("Color : Black, Size : L", $("small:nth-child(3) a")
                .shouldBe(Condition.exist, Duration.ofSeconds(20)).getText());
        Assert.assertEquals("3", $(".cart_quantity_input").shouldBe(Condition.exist).getValue());
    }

    @Test
    public void test5() {
        open("http://automationpractice.com/");
        $$(".sf-with-ul").should(CollectionCondition.size(4), Duration.ofSeconds(20)).get(0).click();
        $("[alt$='shirts']").should(Condition.visible).hover();
        $$(".add_to_compare").should(CollectionCondition.size(7)).get(0).click();
        $(".total-compare-val").shouldNotHave(Condition.text("2"), Duration.ofSeconds(10));
        $("[alt='Blouse']").should(Condition.visible).hover();
        $$(".add_to_compare").should(CollectionCondition.size(7)).get(1).click();;
        $(".total-compare-val").shouldBe(Condition.text("2"), Duration.ofSeconds(10));
        $("[alt='Printed Dress']").should(Condition.visible).hover();
        $$(".add_to_compare").should(CollectionCondition.size(7)).get(2).click();
        $(".bt_compare").click();
        Assert.assertTrue($$("a.product-name").get(0).getText().contains("Faded Short Sleeve T-shirts"));
        Assert.assertTrue($$("a.product-name").get(1).getText().contains("Blouse"));
        Assert.assertTrue($$("a.product-name").get(2).getText().contains("Printed Dress"));

    }
}
