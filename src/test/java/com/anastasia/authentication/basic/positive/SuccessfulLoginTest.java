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

import java.util.Properties;

public class SuccessfulLoginTest {
    private ChromeDriver driver;
    private Properties properties;

    @Before
    public void setUp() throws InterruptedException {
        properties = AuthenticationTestUtils.getProperties();
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver.path"));
        driver = new ChromeDriver();
        driver.get(properties.getProperty("main.url") + "/auth");
        Thread.sleep(AuthenticationTestUtils.PAGE_LOAD_DELAY);
    }

    @Test
    public void successfulLogin() throws InterruptedException {
        WebElement loginField = driver.findElement(By.id("passp-field-login"));
        loginField.sendKeys(properties.getProperty("email.success.test"));

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);

        WebElement loginButton = driver.findElement(By.className("passp-sign-in-button"));
        loginButton.click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        Assert.assertFalse("There should not be a mistake written saying there is no such account as the account is valid", driver.getPageSource().contains(AuthenticationTestUtils.NO_SUCH_ACCOUNT_ERROR_TEXT));

        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys(properties.getProperty("password.success.test"));

        loginButton = driver.findElement(By.className("passp-sign-in-button"));
        loginButton.click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        String url = driver.getCurrentUrl();

        if (url.equals(properties.getProperty("main.url") + "/auth/phone")) {
            WebElement buttonLater = driver.findElement(By.linkText(AuthenticationTestUtils.NOT_NOW_LINK_TEXT));
            buttonLater.click();
            Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        }

        int iterations = 0;
        while (url.equals(properties.getProperty("main.url") + "/auth/welcome") && iterations < 5)
        {
            iterations++;
            Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
            url = driver.getCurrentUrl();
        }

        url = driver.getCurrentUrl();
        Assert.assertEquals("Test should go to a personal account page after successful login", properties.getProperty("main.url") + "/profile", url);
    }

    @After
    public void tearDown() {
        //Thread.sleep(AuthenticationTestUtils.LONG_DELAY);
        if (driver != null) {
            driver.quit();
        }
    }
}