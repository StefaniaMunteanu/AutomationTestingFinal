package pages;

import helper.methods.ElementsMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    ElementsMethod elementsMethod;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.elementsMethod = new ElementsMethod(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='panel header']/ul[@class='header links']/li[3]")
    WebElement createAnAccountLink;

    @FindBy(xpath = "//*[@class='panel header']/ul[@class='header links']/li[2]")
    WebElement signInLink;

    public void clickCreateAnAccount() {
        elementsMethod.clickOnElement(createAnAccountLink);
    }

    public void clickSignIn() {
        elementsMethod.clickOnElement(signInLink);
    }

  }
