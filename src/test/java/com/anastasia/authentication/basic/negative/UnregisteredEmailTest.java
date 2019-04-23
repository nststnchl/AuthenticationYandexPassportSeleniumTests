package com.anastasia.authentication.basic.negative;

import com.anastasia.authentication.AuthenticationTestUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.util.Properties;

public class UnregisteredEmailTest {
    private ChromeDriver driver;
    private Properties properties;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", AuthenticationTestUtils.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(AuthenticationTestUtils.YANDEX_PASSPORT_URL + "/auth");
        Thread.sleep(AuthenticationTestUtils.PAGE_LOAD_DELAY);
    }
    // User gets a reject if email he entered is not registered
    @Test
    public void nonExistingEmail() throws InterruptedException {
        driver.findElement(By.id("passp-field-login")).sendKeys(AuthenticationTestUtils.EMAIL_FAIL_TEST);

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);

        Assert.assertFalse("There should not be a mistake written saying there is no such account before account is entered", driver.getPageSource().contains(AuthenticationTestUtils.NO_SUCH_ACCOUNT_ERROR_TEXT));

        driver.findElement(By.className("passp-sign-in-button")).click();

        Thread.sleep(AuthenticationTestUtils.PAGE_RELOAD_DELAY);
        Assert.assertTrue("There should be a mistake written saying there is no such account", driver.getPageSource().contains(AuthenticationTestUtils.NO_SUCH_ACCOUNT_ERROR_TEXT));

        Assert.assertTrue("There should be a link allowing to admit forgetting a login name", driver.getPageSource().contains(AuthenticationTestUtils.FORGOT_PASSWORD_LINK_TEXT));
    }

    @After
    public void tearDown() {
        //Thread.sleep(AuthenticationTestUtils.LONG_DELAY);
        if (driver != null) {
            driver.quit();
        }
    }
}