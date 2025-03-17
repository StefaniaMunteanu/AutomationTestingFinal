package pages;

import helper.methods.ElementsMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartViewPage {

    WebDriver driver;

    public CartViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCart() {
        ElementsMethod.clickOnElement(driver, By.xpath("//*[@class='counter qty']"));
    }

    public String getCheckOutButtonText() {
        return ElementsMethod.getElementAttribute(driver, By.id("top-cart-btn-checkout"), "title");
    }

    public String getProductPrice() {
        return ElementsMethod.getElementText2(driver,
                By.xpath("//*[@class='amount price-container']//*[@class='price']"));
    }

    public void waitForCartToShow() {
        ElementsMethod.waitFor(driver, By.xpath("//*[@class='minicart-wrapper active']"));
    }

}
