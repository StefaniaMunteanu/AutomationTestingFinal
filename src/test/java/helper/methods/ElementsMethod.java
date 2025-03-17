package helper.methods;

import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class ElementsMethod {

    public static String getElementText(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element.getText();
    }

    public static String getElementText2(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until((ExpectedCondition<Boolean>) d -> !d.findElement(by).getText().isEmpty());
        return driver.findElement(by).getText();
    }

    public static String getElementAttribute(WebDriver driver, By by, String attr) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element.getAttribute(attr);
    }

    public static void clickOnElement(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public static void waitFor(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitFor(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void clickOnElement(WebElement element) {
        try {
            // Check if the element is not null and if the text inside the element is blank
            if (element != null) {
                // If the element exists and is blank, click it
                element.click();
                LoggerUtility.infoTest(element.getAttribute("id"));
                LoggerUtility.infoTest("Element clicked successfully.");

            } else {
                LoggerUtility.infoTest("Element is not present.");
            }
        } catch (NoSuchElementException e) {
            LoggerUtility.infoTest("Element not found: " + e.getMessage());
        }
    }

    public static void moveToElementInMenu(WebDriver driver, WebElement element) {
        waitFor(driver, element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .build()
                .perform();
    }

    public static void clickOnElementInMenu(WebDriver driver, WebElement element) {
        waitFor(driver, element);
        Actions actions = new Actions(driver);
        actions.click(element)
                .build()
                .perform();
    }


    public static void writeInTextbox(WebElement element, String text) {

        if(!Objects.requireNonNull(element.getAttribute("value")).isEmpty()) {
            element.clear();
        }
        element.sendKeys(text);
    }
}
