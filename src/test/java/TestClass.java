import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClass extends BaseTest {
    static final String BASE_URL = "http://automationpractice.com/";

    @Disabled("It is not useful test")
    @Test
    public void scrollTo() {
        open(BASE_URL);
        ElementsCollection images = elements(".item-img");
        images.should(CollectionCondition.size(7)).get(4).scrollTo();
    }

    @RepeatedTest(2)
    @DisplayName("Choice of color and size")
    @Order(3)
    public void choiceOfColorAndSize() {
        open(BASE_URL);
        SelenideElement fadedClothes = element("[alt^='Faded']");
        fadedClothes.shouldBe(Condition.appear, Duration.ofSeconds(20)).hover();
        ElementsCollection buttonsMore = elements(".lnk_view > span");
        buttonsMore.should(CollectionCondition.size(14)).get(0).click();
        SelenideElement orangeColor = element("#color_13");
        orangeColor.shouldNot(Condition.image).click();
        assertEquals(orangeColor.getLocation(), new Point(1136, 640));//1019
        assertEquals(orangeColor.getSize(), new Dimension(22, 22));
        assertEquals(orangeColor.getCssValue("background-color"), "rgba(243, 156, 17, 1)");
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.endsWith("color-orange"));
        SelenideElement blueColor = element(".color_pick:not(#color_13)");
        blueColor.click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("color-blue"));
        SelenideElement sizeBar = element("#group_1");
        if (sizeBar.is(Condition.name("group_1"))) {
            sizeBar.click();
        }
        SelenideElement mSize = element("[title='M']");
        mSize.click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("size-m"));
        sizeBar.shouldNotHave(Condition.exactText("Hello")).click();
        SelenideElement lSize = element("[title='L']");
        lSize.click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("size-l"));
    }

    @Tag("mail")
    @ParameterizedTest
    @DisplayName("Login")
    @Order(4)
    @CsvSource({
            "ska@mail.ru, Qwerty123, Maksim test",
            "test22@gamil.com, Qwerty123, test test"
    })
    public void loginOnSite(String mail, String password, String message) {
        open(BASE_URL);
        SelenideElement login = element(".login");
        login.shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        SelenideElement email = element("#email");
        email.shouldBe(Condition.empty).setValue(mail);
        SelenideElement passwd = element("#passwd");
        passwd.shouldBe(Condition.empty).setValue(password).pressEnter();
        SelenideElement account = element(".account");
        assertEquals(message, account.shouldBe(Condition.exist,
                Duration.ofSeconds(20)).getText());
    }

    @Tag("mail")
    @Test
    @DisplayName("Adding an email to the newsletter")
    @Order(5)
    public void newsletterEmail() {
        open(BASE_URL);
        SelenideElement newsletter = element("#newsletter-input");
        newsletter.shouldBe(Condition.name("email")).setValue("test@gmail.com").pressEnter();
        SelenideElement alert = element(".alert");
        assertEquals("Newsletter : This email address is already registered.",
                alert.getText());
        newsletter.shouldBe(Condition.name("email")).setValue("testtest@gmail.com").pressEnter();
        assertEquals("Newsletter : This email address is already registered.",
                alert.getText());

    }

    @Test
    @DisplayName("Adding a product with parameters to the cart")
    @Order(1)
    public void addProductToTheCart() {
        open(BASE_URL);
        SelenideElement blouseClothes = element("[alt='Blouse']");
        blouseClothes.shouldBe(Condition.enabled, Duration.ofSeconds(20)).hover();
        ElementsCollection buttonsMore = elements(".lnk_view span");
        buttonsMore.should(CollectionCondition.size(14)).get(1).click();
        SelenideElement quantity = element("#quantity_wanted");
        quantity.shouldBe(Condition.name("qty")).setValue("3");
        SelenideElement sizeBar = element("#group_1");
        sizeBar.shouldBe(Condition.name("group_1")).click();
        SelenideElement lSize = element("[title='L']");
        lSize.click();
        SelenideElement blackColor = element("#color_11");
        blackColor.shouldBe(Condition.name("Black")).click();
        SelenideElement addToCart = element("#add_to_cart > button");
        addToCart.click();
        SelenideElement checkout = element(".button-medium");
        checkout.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        SelenideElement info = element("small:nth-child(3) a");
        assertEquals("Color : Black, Size : L", info.shouldBe(Condition.exist).getText());
        SelenideElement quantityInfo = element(".cart_quantity_input");
        assertEquals("3", quantityInfo.shouldBe(Condition.exist).getValue());
    }

    @Test
    @DisplayName("Adding products to compare")
    @Order(2)
    public void addToCompare() {
        open(BASE_URL);
        ElementsCollection blocks = elements(".sf-with-ul");
        blocks.should(CollectionCondition.size(4), Duration.ofSeconds(20)).get(0).click();
        SelenideElement shirtsClothes = element("[alt$='shirts']");
        shirtsClothes.should(Condition.visible).hover();
        ElementsCollection addToCompareButtoms = elements(".add_to_compare");
        addToCompareButtoms.should(CollectionCondition.size(7)).get(0).click();
        SelenideElement compareTotal = element(".total-compare-val");
        compareTotal.shouldHave(Condition.text("1"), Duration.ofSeconds(20));
        SelenideElement blouseClothes = element("[alt='Blouse']");
        blouseClothes.should(Condition.visible, Duration.ofSeconds(10)).hover();
        addToCompareButtoms.should(CollectionCondition.size(7), Duration.ofSeconds(10)).get(1).click();
        compareTotal.shouldHave(Condition.text("2"), Duration.ofSeconds(20));
        SelenideElement printedClothes = element("[alt='Printed Dress']");
        printedClothes.should(Condition.visible).hover();
        addToCompareButtoms.should(CollectionCondition.size(7)).get(2).click();
        SelenideElement compare = element(".bt_compare");
        compare.click();
        ElementsCollection info = elements("a.product-name");
        assertTrue(info.get(0).getText().contains("Faded Short Sleeve T-shirts"));
        assertTrue(info.get(1).getText().contains("Blouse"));
        assertTrue(info.get(2).getText().contains("Printed Dress"));

    }
}
