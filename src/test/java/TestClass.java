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

    @Disabled("It is not useful test")
    @Test
    public void checkScroll() {
        open("/");
        ElementsCollection images = elements(".item-img");
        images.should(CollectionCondition.size(7)).get(4).scrollTo();
    }

    @RepeatedTest(2)
    @DisplayName("Check choosing of color and size in Faded Short Sleeve T-shirts page")
    @Order(3)
    public void choiceOfColorAndSize() {
        open("/");
        SelenideElement fadedTShirt = element("[alt^='Faded']");
        fadedTShirt.shouldBe(Condition.appear, Duration.ofSeconds(20)).hover();
        ElementsCollection buttonsMore = elements(".lnk_view > span");
        buttonsMore.should(CollectionCondition.size(14)).get(0).click();
        SelenideElement orangeColorPick = element("#color_13");
        orangeColorPick.shouldNot(Condition.image).click();
        assertEquals(orangeColorPick.getLocation(), new Point(1136, 640));
        assertEquals(orangeColorPick.getSize(), new Dimension(22, 22));
        assertEquals(orangeColorPick.getCssValue("background-color"), "rgba(243, 156, 17, 1)");
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.endsWith("color-orange"));
        SelenideElement blueColorPick = element(".color_pick:not(#color_13)");
        blueColorPick.click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("color-blue"));
        SelenideElement sizeSelector = element("#group_1");
        if (sizeSelector.is(Condition.name("group_1"))) {
            sizeSelector.click();
        }
        SelenideElement mSizeSelector = element("[title='M']");
        mSizeSelector.click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("size-m"));
        sizeSelector.shouldNotHave(Condition.exactText("Hello")).click();
        SelenideElement lSizeSelector = element("[title='L']");
        lSizeSelector.click();
        currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("size-l"));
    }

    @Tag("mail")
    @ParameterizedTest
    @DisplayName("Сhecking the login on the site")
    @Order(4)
    @CsvSource({
            "ska@mail.ru, Qwerty123, Maksim test",
            "test22@gamil.com, Qwerty123, test test"
    })
    public void loginOnSite(String mail, String password, String message) {
        open("/");
        SelenideElement loginField = element(".login");
        loginField.shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        SelenideElement emailField = element("#email");
        emailField.shouldBe(Condition.empty).setValue(mail);
        SelenideElement passwdField = element("#passwd");
        passwdField.shouldBe(Condition.empty).setValue(password).pressEnter();
        SelenideElement account = element(".account");
        assertEquals(message, account.shouldBe(Condition.exist,
                Duration.ofSeconds(20)).getText());
    }

    @Tag("mail")
    @Test
    @DisplayName("Adding an email to the newsletter")
    @Order(5)
    public void newsletterEmail() {
        open("/");
        SelenideElement newsletterField = element("#newsletter-input");
        newsletterField.shouldBe(Condition.name("email")).setValue("test@gmail.com").pressEnter();
        SelenideElement alertMessage = element(".alert");
        assertEquals("Newsletter : This email address is already registered.",
                alertMessage.getText());
        newsletterField.shouldBe(Condition.name("email")).setValue("testtest@gmail.com").pressEnter();
        assertEquals("Newsletter : This email address is already registered.",
                alertMessage.getText());
    }

    @Test
    @DisplayName("Adding a product Blouse with parameters Quantity = 3 ,size = L, color = black to the cart")
    @Order(1)
    public void addProductToTheCart() {
        open("/");
        SelenideElement blouseClothes = element("[alt='Blouse']");
        blouseClothes.shouldBe(Condition.enabled, Duration.ofSeconds(20)).hover();
        ElementsCollection moreButtons = elements(".lnk_view span");
        moreButtons.should(CollectionCondition.size(14)).get(1).click();
        SelenideElement quantity = element("#quantity_wanted");
        quantity.shouldBe(Condition.name("qty")).setValue("3");
        SelenideElement sizeSelector = element("#group_1");
        sizeSelector.shouldBe(Condition.name("group_1")).click();
        SelenideElement lSizeSelector = element("[title='L']");
        lSizeSelector.click();
        SelenideElement blackColorPick = element("#color_11");
        blackColorPick.shouldBe(Condition.name("Black")).click();
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
    @DisplayName("checking the addition of products Faded Short Sleeve T-shirts, Blouse, Printed Dress to the comparison")
    @Order(2)
    public void addToCompare() {
        open("/");
        ElementsCollection blocks = elements(".sf-with-ul");
        blocks.should(CollectionCondition.size(4), Duration.ofSeconds(20)).get(0).click();
        SelenideElement fadedTShirt = element("[alt$='shirts']");
        fadedTShirt.should(Condition.visible).hover();
        ElementsCollection addToCompareButtons = elements(".add_to_compare");
        addToCompareButtons.should(CollectionCondition.size(7)).get(0).click();
        SelenideElement compareTotal = element(".total-compare-val");
        compareTotal.shouldHave(Condition.text("1"), Duration.ofSeconds(20));
        SelenideElement blouseClothes = element("[alt='Blouse']");
        blouseClothes.should(Condition.visible, Duration.ofSeconds(10)).hover();
        addToCompareButtons.should(CollectionCondition.size(7), Duration.ofSeconds(10)).get(1).click();
        compareTotal.shouldHave(Condition.text("2"), Duration.ofSeconds(20));
        SelenideElement printedDress = element("[alt='Printed Dress']");
        printedDress.should(Condition.visible).hover();
        addToCompareButtons.should(CollectionCondition.size(7)).get(2).click();
        SelenideElement compareButton = element(".bt_compare");
        compareButton.click();
        ElementsCollection info = elements("a.product-name");
        assertTrue(info.get(0).getText().contains("Faded Short Sleeve T-shirts"));
        assertTrue(info.get(1).getText().contains("Blouse"));
        assertTrue(info.get(2).getText().contains("Printed Dress"));
    }
}
