//package test;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class Test2 {
//    public static void test2() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        Actions action = new Actions(driver);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        driver.get("http://automationpractice.com/");
//
//        WebElement element = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[1]/div/a[1]/img"));
//
//        action.moveToElement(element).build().perform();
//        element = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/div[2]/a[1]/span"));
//        element.click();
//
//        element = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]"));
//
//        String text = element.getCssValue("display");
//
//        while (text.equals("none")) {
//            text = element.getCssValue("display");
//        }
//        element = driver.findElement(By.cssSelector("#layer_cart_product_title"));
//        System.out.println("test2");
//        System.out.println("Product added is correct: " + element.getText().equals("Faded Short Sleeve T-shirts"));
//        driver.close();
//
//    }
//}
