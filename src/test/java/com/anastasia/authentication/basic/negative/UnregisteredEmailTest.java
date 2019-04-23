package com.anastasia.authentication.basic.negative;

import com.anastasia.authentication.AuthenticationTestUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class UnregisteredEmailTest {
    private ChromeDriver driver;

    @Before
    public void setUp() {
        AuthenticationTestUtils.setProperties(AuthenticationTestUtils.properties);
        System.setProperty("webdriver.chrome.driver", AuthenticationTestUtils.properties.getProperty("chrome.driver.path"));
        driver = new ChromeDriver();
        driver.get(AuthenticationTestUtils.properties.getProperty("authentication.url"));
    }

    @Test
    public void nonExistingEmail() throws InterruptedException {
        //<input type="text" id="passp-field-login" name="login" autocorrect="off" autocapitalize="off" autocomplete="username">
        WebElement element = driver.findElement(By.xpath("//input[@name=\"login\"]"));
        element.sendKeys("abc@gmail.com");

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        Assert.assertFalse("Mistake is written beforehand", driver.getPageSource().contains("Такого аккаунта нет"));

        // <span data-lego="react" class="button2__text">Войти</span></button>
        WebElement button = driver.findElement(By.xpath(".//*[text()='Войти']/.."));
        button.click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        Assert.assertTrue("There is no mistake", driver.getPageSource().contains("Такого аккаунта нет"));

        Assert.assertTrue("There is no option to admit forgetting a login name", driver.getPageSource().contains("Не помню логин"));
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(AuthenticationTestUtils.LONG_DELAY);
        if (driver != null) {
            driver.quit();
        }
    }
}