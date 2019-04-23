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
        System.setProperty("webdriver.chrome.driver", AuthenticationTestUtils.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        System.out.println(AuthenticationTestUtils.YANDEX_PASSPORT_URL + "/auth");
        driver.get(AuthenticationTestUtils.YANDEX_PASSPORT_URL + "/auth");
        Thread.sleep(AuthenticationTestUtils.PAGE_LOAD_DELAY);
    }
// User can login successfully using valid credentials (email and password)
    @Test
    public void successfulLogin() throws InterruptedException {
        WebElement loginField = driver.findElement(By.id("passp-field-login"));
        loginField.sendKeys(AuthenticationTestUtils.EMAIL_SUCCESS_TEST);

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);

        WebElement loginButton = driver.findElement(By.className("passp-sign-in-button"));
        loginButton.click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        Assert.assertFalse("There should not be a mistake written saying there is no such account as the account is valid", driver.getPageSource().contains(AuthenticationTestUtils.NO_SUCH_ACCOUNT_ERROR_TEXT));

        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys(AuthenticationTestUtils.PASSWORD_SUCCESS_TEST);

        loginButton = driver.findElement(By.className("passp-sign-in-button"));
        loginButton.click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        String url = driver.getCurrentUrl();

        if (url.equals(AuthenticationTestUtils.YANDEX_PASSPORT_URL + "/auth/phone")) {
            WebElement buttonLater = driver.findElement(By.linkText(AuthenticationTestUtils.NOT_NOW_LINK_TEXT));
            buttonLater.click();
            Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        }

        int iterations = 0;
        while (!url.equals(AuthenticationTestUtils.YANDEX_PASSPORT_URL + "/profile") && iterations < 5)
        {
            iterations++;
            Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
            url = driver.getCurrentUrl();
        }

        url = driver.getCurrentUrl();
        Assert.assertEquals("Test should go to a personal account page after successful login", AuthenticationTestUtils.YANDEX_PASSPORT_URL + "/profile", url);
    }

    @After
    public void tearDown() {
        //Thread.sleep(AuthenticationTestUtils.LONG_DELAY);
        if (driver != null) {
            driver.quit();
        }
    }
}