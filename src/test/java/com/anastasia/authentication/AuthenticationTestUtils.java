package com.anastasia.authentication;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AuthenticationTestUtils {
    public static final int PAGE_RELOAD_DELAY = 300;  // ms PAGE_RELOAD_DELAY for page reload
    public static final int PAGE_LOAD_DELAY = 3000;  // ms PAGE_RELOAD_DELAY for page reload
    public static final int LONG_DELAY = 5000;

    public static final String NO_SUCH_ACCOUNT_ERROR_TEXT = "Такого аккаунта нет";
    public static final String FORGOT_PASSWORD_LINK_TEXT = "Не помню логин";
    public static final String ENTER_TO_CONTINUE_INSTRUCTION_TEXT = "Войдите, чтобы продолжить";
    public static final String RU_LINK_TEXT = "Ru";
    public static final String NOT_NOW_LINK_TEXT = "Не сейчас";

    public static String YANDEX_PASSPORT_URL;
    public static String CHROME_DRIVER_PATH;
    public static String EMAIL_SUCCESS_TEST;
    public static String PASSWORD_SUCCESS_TEST;
    public static String EMAIL_FAIL_TEST;

    static {
        Properties properties = new Properties();
        File file = new File("src\\test\\resources\\config.properties");
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        YANDEX_PASSPORT_URL = properties.getProperty("yandex.passport.url");
        CHROME_DRIVER_PATH = properties.getProperty("chrome.driver.path");
        EMAIL_SUCCESS_TEST = properties.getProperty("email.success.test");
        PASSWORD_SUCCESS_TEST = properties.getProperty("password.success.test");
        EMAIL_FAIL_TEST = properties.getProperty("email.fail.test");
    }

    public static void checkClickable(WebDriver driver, WebElement webElementToCheck)
    {
        Actions builder = new Actions(driver);
        builder.moveToElement(webElementToCheck).build().perform();
        String cursorTypeAfter = webElementToCheck.getCssValue("cursor");
        Assert.assertTrue(cursorTypeAfter.equalsIgnoreCase("pointer"));
    }
    public static void checkNotClickable(WebDriver driver, WebElement webElementToCheck)
    {
        Actions builder = new Actions(driver);
        builder.moveToElement(webElementToCheck).build().perform();
        String cursorTypeAfter = webElementToCheck.getCssValue("cursor");
        Assert.assertFalse(cursorTypeAfter.equalsIgnoreCase("pointer"));
    }
    public static void checkCouldEnterText(WebElement webElementToCheck)
    {
        try {
            webElementToCheck.sendKeys("");
        }
        catch (Exception e)
        {
            assert false;
        }
    }
    public static void checkCouldNotEnterText(WebElement webElementToCheck)
    {
        try {
            webElementToCheck.sendKeys("");
            assert false;
        }
        catch (Exception ignored)
        {
        }
    }
}
