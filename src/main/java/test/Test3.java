//package test;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class Test3 {
//    public static void test3() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        driver.get("http://automationpractice.com/");
//
//        WebElement element = driver.findElement(By.xpath("//input[@class='search_query form-control ac_input']"));
//
//        element.sendKeys("Printed", Keys.ENTER);
//
//
//        element = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/h1/span[2]"));
//        String text = element.getText();
//        System.out.println("test3");
//        System.out.println("Search is correct: " + text.equals("5 results have been found."));
//        driver.close();
//    }
//}
