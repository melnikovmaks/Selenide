import com.codeborne.selenide.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.element;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class TestClassTestNG extends BaseTestTestNG {

    @Test(description = "It is not useful test", enabled = false)
    public void checkScroll() {
        open("/");
        ElementsCollection images = elements(".item-img");
        images.should(CollectionCondition.size(7)).get(4).scrollTo();
    }

    @Test(description = "Check choosing of color and size in Faded Short Sleeve T-shirts page",
            groups={"addToCart"},
            priority = 3,
            invocationCount = 2)
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

    @Test(dataProvider = "accounts", priority = 4, groups="mail")
    public void loginOnSite(String mail, String password, String message) {
        open("/");
        SelenideElement signInButton = element(".login");
        signInButton.shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        SelenideElement emailField = element("#email");
        emailField.shouldBe(Condition.empty).setValue(mail);
        SelenideElement passwdField = element("#passwd");
        passwdField.shouldBe(Condition.empty).setValue(password).pressEnter();
        SelenideElement headerUserInfo = element(".account");
        assertEquals(message, headerUserInfo.shouldBe(Condition.exist,
                Duration.ofSeconds(20)).getText());
    }

    @Test(description = "Adding an email to the newsletter", priority = 5, groups="mail")
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

    @Test(description = "Adding a product Blouse with parameters Quantity = 3 ,size = L, color = black to the cart",
            groups={"addToCart"},
            priority = 1)
    public void addProductToTheCart() {
        open("/");
        SelenideElement blouseClothes = element("[alt='Blouse']");
        blouseClothes.shouldBe(Condition.enabled, Duration.ofSeconds(20)).hover();
        ElementsCollection moreButtons = elements(".lnk_view span");
        moreButtons.should(CollectionCondition.size(14)).get(1).click();
        SelenideElement quantityField = element("#quantity_wanted");
        quantityField.shouldBe(Condition.name("qty")).setValue("3");
        SelenideElement sizeSelector = element("#group_1");
        sizeSelector.shouldBe(Condition.name("group_1")).click();
        SelenideElement lSizeSelector = element("[title='L']");
        lSizeSelector.click();
        SelenideElement blackColorPick = element("#color_11");
        blackColorPick.shouldBe(Condition.name("Black")).click();
        SelenideElement addToCartButton = element("#add_to_cart > button");
        addToCartButton.click();
        SelenideElement checkoutButton = element(".button-medium");
        checkoutButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        SelenideElement descriptionItem = element("small:nth-child(3) a");
        assertEquals("Color : Black, Size : L", descriptionItem.shouldBe(Condition.exist).getText());
        SelenideElement quantityInfo = element(".cart_quantity_input");
        assertEquals("3", quantityInfo.shouldBe(Condition.exist).getValue());
    }

    @Test(description = "checking the addition of products Faded Short Sleeve T-shirts, Blouse, Printed Dress to the comparison",
            priority = 1)
    public void addToCompare() {
        open("/");
        ElementsCollection menuBlocksButtons = elements(".sf-with-ul");
        menuBlocksButtons.should(CollectionCondition.size(4), Duration.ofSeconds(20)).get(0).click();
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
        ElementsCollection productNames = elements("a.product-name");
        assertTrue(productNames.get(0).getText().contains("Faded Short Sleeve T-shirts"));
        assertTrue(productNames.get(1).getText().contains("Blouse"));
        assertTrue(productNames.get(2).getText().contains("Printed Dress"));
    }
}
