import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignIn {

    static WebDriver driver;
    static WebDriverWait wait;
    static String url = "https://rahulshettyacademy.com/locatorspractice/";

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        System.out.println(url.contains(driver.getCurrentUrl())); // true
        System.out.println("Page Title: " + driver.getTitle());


        WebElement userName = driver.findElement(By.id("inputUsername"));
        userName.sendKeys("Farid");

        WebElement password = driver.findElement(By.name("inputPassword"));
        password.sendKeys("Ferid1234");

        WebElement chkboxOne = driver.findElement(By.id("chkboxOne"));
        chkboxOne.click();

        WebElement chkboxTwo = driver.findElement(By.id("chkboxTwo"));
        chkboxTwo.click();

        WebElement signInButton = driver.findElement(By.xpath("//button[.='Sign In']"));
        signInButton.click();

        WebElement incorrectMessage = driver.findElement(By.xpath("//p[@class = 'error']"));
        String actual = incorrectMessage.getText();

        if (actual.equals("* Incorrect username or password")){
            System.out.println("Test Passed:");
        }else
            System.out.println("Test Failed:");

        WebElement forgotPassword = driver.findElement(By.linkText("Forgot your password?"));
        forgotPassword.click();



        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        name.sendKeys("Farid");

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        email.sendKeys("ferid@gmail.com");

        WebElement phoneNumber = driver.findElement(By.xpath("//input[@placeholder='Phone Number']"));
        phoneNumber.sendKeys("1234");


        Thread.sleep(1000);
        WebElement resetLogin = driver.findElement(By.cssSelector(".reset-pwd-btn"));
        resetLogin.click();

        WebElement infoMessage = driver.findElement(By.cssSelector(".infoMsg"));
        String newPassword = infoMessage.getText().substring(31,49);

        WebElement goToLoginPage = driver.findElement(By.cssSelector(".go-to-login-btn"));
        goToLoginPage.click();

        userName.sendKeys("Farid");
        password.sendKeys(newPassword);

        Thread.sleep(1000);
        signInButton.click();

        WebElement successMsg = driver.findElement(By.xpath("//p[.='You are successfully logged in.']"));
        String actualMsg = successMsg.getText();

        String expectedMsg = "You are successfully logged in.";
        System.out.println(actualMsg.equals(expectedMsg) ? "Test Passed:" : "Test Failed:");

        WebElement endName = driver.findElement(By.cssSelector(".login-container h2"));
        Assert.assertEquals(endName.getText(), "Hello Farid,", "Message is not equal");

        WebElement logoutBtn = driver.findElement(By.cssSelector(".logout-btn"));
        logoutBtn.click();

        Thread.sleep(3000);
        driver.quit();


    }
}
