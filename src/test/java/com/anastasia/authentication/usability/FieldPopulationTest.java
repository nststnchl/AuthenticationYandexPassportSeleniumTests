package com.anastasia.authentication.usability;

import com.anastasia.authentication.AuthenticationTestUtils;
import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

public class FieldPopulationTest {
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
    public void checkAllNeededFieldsArePolulated() {
        WebElement loginField = driver.findElement(By.id("passp-field-login"));
        Assert.assertEquals("", loginField.getText());
        // Check login field is editable
        AuthenticationTestUtils.checkCouldEnterText(loginField);
        Assert.assertEquals("", loginField.getText());

        WebElement languageSwitcher = driver.findElement(By.className("passp-footer__item"));
        Assert.assertEquals(AuthenticationTestUtils.RU_LINK_TEXT, languageSwitcher.getText());
        AuthenticationTestUtils.checkClickable(driver, languageSwitcher);

        WebElement textInstruction = driver.findElement(By.className("passp-title"));
        Assert.assertEquals(AuthenticationTestUtils.ENTER_TO_CONTINUE_INSTRUCTION_TEXT, textInstruction.getText());
        AuthenticationTestUtils.checkNotClickable(driver,textInstruction);
        AuthenticationTestUtils.checkCouldNotEnterText(textInstruction);
    }

    @After
    public void tearDown() {
        //Thread.sleep(AuthenticationTestUtils.LONG_DELAY);
        if (driver != null) {
            driver.quit();
        }
    }
}