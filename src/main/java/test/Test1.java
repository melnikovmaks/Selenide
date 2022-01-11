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
//public class Test1 {
//    public static void test1() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("http://automationpractice.com/");
//        WebElement element = driver.findElement(By.xpath("(//a[text()='Dresses'])[2]"));
//
//        String url = driver.getCurrentUrl();
//
//        System.out.println("test1");
//        System.out.println("Start Url is correct: " + url.equals("http://automationpractice.com/index.php"));
//
//        element.click();
//        wait.until(ExpectedConditions.stalenessOf(element));
//
//        element = driver.findElement(By.xpath("(//a[text()='Dresses'])[2]"));
//        String title = driver.getTitle();
//        System.out.println("Title is correct: " + title.equals("Dresses - My Store"));
//        driver.close();
//    }
//}
