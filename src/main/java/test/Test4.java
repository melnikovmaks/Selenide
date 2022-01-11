//package test;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.Random;
//
//public class Test4 {
//    public static void test4() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("http://automationpractice.com/");
//        WebElement element = driver.findElement(By.xpath("//input[@class='inputNew form-control grey newsletter-input']"));
//
//        String email = Test4.mailRandom();
//        element.sendKeys(email, Keys.ENTER);
//
//
//        element = driver.findElement(By.xpath("//p[@class='alert alert-success']"));
//        String text = element.getText();
//        System.out.println("test4");
//        System.out.printf("Newsletter is correct: " + text.equals("Newsletter : You have successfully subscribed to this newsletter."));
//
//        driver.close();
//    }
//
//    public static String mailRandom() {
//        String letters = "abcdefghijklmnopqrstuvwxyz";
//        char[] chArray = letters.toCharArray();
//        Random random = new Random();
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < 10; i++) {
//            buffer.append(chArray[random.nextInt(chArray.length)]);
//        }
//        buffer.append("@gmail.com");
//        String email = buffer.toString();
//        return buffer.toString();
//    }
//}
//
//
