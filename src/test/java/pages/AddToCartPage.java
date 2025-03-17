package pages;

import helper.methods.ElementsMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {

    WebDriver driver;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "option-label-size-143-item-171")
    WebElement sizeElement;

    @FindBy(id = "option-label-color-93-item-50")
    WebElement colorElement;

    @FindBy(id = "product-addtocart-button")
    WebElement addToCartButton;

    @FindBy(id = "qty")
    WebElement quantityElement;


    public void gotoBuyProduct() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateMenuWomenPants();

        PantsWomenPage pantsWomenPage = new PantsWomenPage(driver);
        pantsWomenPage.clickOnProduct();

    }

    public void gotoPageAndEnterData(String qty) {
        gotoBuyProduct();
        clickOnSize();
        clickOnColor();
        setQuantity(qty);
        clickOnAddToCart();
    }


    public void clickOnSize() {
        ElementsMethod.clickOnElement(sizeElement);
    }

    public void clickOnColor() {
        ElementsMethod.clickOnElement(colorElement);
    }

    public void clickOnAddToCart() {
        ElementsMethod.clickOnElement(addToCartButton);
    }

    public String getConfirmationMessage() {
        return ElementsMethod.getElementText(driver, By.xpath("//*[@class='page messages']/div/div/div"));
    }

    public String getCartCounter() {
        return ElementsMethod.getElementText(driver, By.xpath("//*[@class='counter-number']"));
    }

    public String getSizeErrorMessage() {
        return ElementsMethod.getElementText(driver, By.id("super_attribute[143]-error"));
    }

    public String getColorErrorMessage() {
        return ElementsMethod.getElementText(driver, By.id("super_attribute[93]-error"));
    }

    public void setQuantity(String text) {
        ElementsMethod.writeInTextbox(quantityElement, text);
    }

}
