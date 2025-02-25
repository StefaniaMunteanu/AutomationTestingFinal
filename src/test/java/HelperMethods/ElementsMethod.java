package HelperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.Objects;

public class ElementsMethod {
    WebDriver diver;

    public ElementsMethod(WebDriver diver) {
        this.diver = diver;
    }


    public void clickOnElement(WebElement element){
        element.click();
    }

    public void writeInTextbox(WebElement element, String text){

        if(!Objects.requireNonNull(element.getAttribute("value")).isEmpty()) {
            element.clear();
        }
        element.sendKeys(text);
    }

    public void clearTextbox(WebElement element) {
        element.clear();
    }

    public void writeInTextboxAfterClear(WebElement element, String text){
        clearTextbox(element);
        element.sendKeys(text);
    }
}
