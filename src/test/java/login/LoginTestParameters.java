package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import static org.testng.Assert.assertTrue;
import java.time.Duration;

public class LoginTestParameters {

    @Test
    @Parameters({"email", "password"})
    public void loginTest(String email, String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\yandexdriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Lord\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--disable-features=NetworkService");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qa-course-01.andersenlab.com/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);

        WebElement signInButton = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
        signInButton.click();

        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Login test completed for user: " + email + ". Current URL: " + currentUrl);

        assertTrue(!currentUrl.contains("/login"), "Login did not succeed for user: " + email);
        driver.quit();
    }
}
