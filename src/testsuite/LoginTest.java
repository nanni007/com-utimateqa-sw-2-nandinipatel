package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        driver.findElement(By.xpath("//li[contains(@class, 'nav-sign-in')]")).click();
        String expectedText = "Welcome Back!";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[contains(@class, 'page__heading')]"));  //finds the element and verify if actual and expected match
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Text Error", expectedText, actualText); //compares if expected and actual is equal
    }

    @Test
    public void verifyTheErrorMessage() {
        driver.findElement(By.xpath("//li[contains(@class, 'nav-sign-in')]")).click();
        driver.findElement(By.name("user[email]")).sendKeys("prime1234@gmail.com");
        driver.findElement(By.id("user[password]")).sendKeys("prime1234");
        driver.findElement(By.className("g-recaptcha")).click(); //finds the login button and clicks
        driver.findElement(By.xpath("//button[starts-with(@type, 'submit')]")).click(); //finds the computer button and clicks
        String expectedText = "Invalid email or password.";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[contains(@id, 'notice')]"));  //finds the element and verify if actual and expected match
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Text Error", expectedText, actualText); //compares if expected and actual is equal
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
