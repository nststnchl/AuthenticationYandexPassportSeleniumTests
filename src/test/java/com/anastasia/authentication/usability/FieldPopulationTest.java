package com.anastasia.authentication.usability;

import com.anastasia.authentication.AuthenticationTestUtils;
import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FieldPopulationTest {
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
    public void checkAllNeededFieldsArePolulated() {
        // <input type="text" id="passp-field-login" name="login" autocorrect="off" autocapitalize="off" autocomplete="username">
        WebElement loginField = driver.findElement(By.id("passp-field-login"));
        Assert.assertEquals("", loginField.getText());
        // Check login field is editable
        AuthenticationTestUtils.checkCouldEnterText(loginField);
        Assert.assertEquals("", loginField.getText());

        WebElement languageSwitcher = driver.findElement(By.className("passp-footer__item"));
        Assert.assertEquals("Ru", languageSwitcher.getText());
        AuthenticationTestUtils.checkClickable(driver, languageSwitcher);

        WebElement textInstruction = driver.findElement(By.className("passp-title"));
        Assert.assertEquals("Войдите, чтобы продолжить", textInstruction.getText());
        AuthenticationTestUtils.checkNotClickable(driver,textInstruction);
        AuthenticationTestUtils.checkCouldNotEnterText(textInstruction);
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(AuthenticationTestUtils.LONG_DELAY);
        if (driver != null) {
            driver.quit();
        }
    }
}