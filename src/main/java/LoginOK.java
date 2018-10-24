import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginOK {

    public LoginOK() {
    }

    public static void main(String[] args ) throws InterruptedException {

        int result_list_before = 0;
        int result_list_after = 1;

        System.setProperty("webdriver.chrome.driver" ,  "/Applications/chromedriver 3");


        WebDriver driver = new ChromeDriver();

        driver.get("https://ok.ru");


        driver.findElement(By.id("field_email")).sendKeys("+4915259532695");
        driver.findElement(By.id("field_password")).sendKeys("deinaol1");
        driver.findElement(By.xpath("//input[@class='button-pro __wide']")).click();

        driver.get("https://ok.ru/search?st.gender=f&st.fromAge=25&st.tillAge=30&st.country=10397571399&st.onSite=on&st.mode=Users&st.grmode=Groups&st.posted=set");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        JavascriptExecutor jse = (JavascriptExecutor)driver;

        while (result_list_before < result_list_after){
            result_list_before = driver.findElements(By.xpath("//div[@data-l]")).size();

            jse.executeScript("window.scrollBy(0,document.body.scrollHeight || document.documentElement.scrollHeight)", "");

            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                                 Thread.sleep(2000);

            if(driver.findElement(By.xpath("//a[@class='js-show-more link-show-more']")).isDisplayed()){
                driver.findElement(By.xpath("//a[@class='js-show-more link-show-more']")).click();
            }

            result_list_after = driver.findElements(By.xpath("//div[@data-l]")).size();

        }





    }
}
