package HelperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementsMethod {
    WebDriver diver;

    public ElementsMethod(WebDriver diver) {
        this.diver = diver;
    }


    public void clickOnElement(WebElement element){
        element.click();
    }

    public void sendTextToTextbox(WebElement element, String text){
        element.sendKeys(text);
    }
}
