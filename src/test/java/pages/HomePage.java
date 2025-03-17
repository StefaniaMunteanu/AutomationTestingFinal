package pages;

import helper.methods.ElementsMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='panel header']/ul[@class='header links']/li[3]")
    WebElement createAnAccountLink;

    @FindBy(xpath = "//*[@class='panel header']/ul[@class='header links']/li[2]")
    WebElement signInLink;

    @FindBy(id = "ui-id-4")
    WebElement womenMenuItem;

    @FindBy(id = "ui-id-10")
    WebElement bottomsWomenMenuItem;

    @FindBy(id = "ui-id-15")
    WebElement pantsWomenMenuItem;

    public void clickCreateAnAccount() {
        ElementsMethod.clickOnElement(createAnAccountLink);
    }

    public void clickSignIn() {
        ElementsMethod.clickOnElement(signInLink);
    }

    public void navigateMenuWomenPants() {
        ElementsMethod.moveToElementInMenu(driver, womenMenuItem);
        ElementsMethod.moveToElementInMenu(driver, bottomsWomenMenuItem);
        ElementsMethod.clickOnElementInMenu(driver, pantsWomenMenuItem);
    }

  }
