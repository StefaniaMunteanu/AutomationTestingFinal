package pages;

import helper.methods.ElementsMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PantsWomenPage {
    WebDriver driver;

    public PantsWomenPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='item product product-item'][3]")
    WebElement pantsProduct;

    public void clickOnProduct() {
        ElementsMethod.clickOnElement(pantsProduct);
    }
}
