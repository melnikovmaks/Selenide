//package test;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class Test5 {
//    public static void test5() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("http://automationpractice.com/");
//        WebElement element = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[3]/div/a/b"));
//
//        System.out.println("test5");
//
//        element.click();
//        element = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p"));
//        System.out.println("Shopping cart is correct: " + element.getText().equals("Your shopping cart is empty."));
//        driver.close();
//    }
//}
