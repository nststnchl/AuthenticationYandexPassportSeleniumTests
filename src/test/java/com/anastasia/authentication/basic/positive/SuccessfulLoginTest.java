package com.anastasia.authentication.basic.positive;

import com.anastasia.authentication.AuthenticationTestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessfulLoginTest {
    private ChromeDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        AuthenticationTestUtils.setProperties(AuthenticationTestUtils.properties);
        System.setProperty("webdriver.chrome.driver", AuthenticationTestUtils.properties.getProperty("chrome.driver.path"));
        driver = new ChromeDriver();
        driver.get(AuthenticationTestUtils.properties.getProperty("authentication.url"));
    }

    @Test
    public void successfulLogin() throws InterruptedException {
        WebElement loginField = driver.findElement(By.id("passp-field-login"));
        loginField.sendKeys(AuthenticationTestUtils.properties.getProperty("email.success.test"));

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);

        WebElement loginButton = driver.findElement(By.className("passp-sign-in-button"));
        loginButton.click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        Assert.assertFalse("There is no mistake", driver.getPageSource().contains("Такого аккаунта нет"));

        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys(AuthenticationTestUtils.properties.getProperty("password.success.test"));

        loginButton = driver.findElement(By.className("passp-sign-in-button"));
        loginButton.click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        String url = driver.getCurrentUrl();

        if (url.equals("https://passport.yandex.ru/auth/phone")) {
            WebElement buttonLater = driver.findElement(By.linkText("Не сейчас"));
            buttonLater.click();
            Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        }

        url = driver.getCurrentUrl();
        Assert.assertEquals("https://passport.yandex.ru/profile",url);
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(AuthenticationTestUtils.LONG_DELAY);
        if (driver != null) {
            driver.quit();
        }
    }
}