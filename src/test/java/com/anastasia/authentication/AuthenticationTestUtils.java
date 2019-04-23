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
    public static final int PAGE_RELOAD_DELAY = 200;  // ms PAGE_RELOAD_DELAY for page reload
    public static final int LONG_DELAY = 5000;

    private static File file = new File("src\\test\\resources\\config.properties");
    public static Properties properties = new Properties();

    public static void setProperties(Properties properties) {
        AuthenticationTestUtils.properties = properties;
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AuthenticationTestUtils() { }

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
