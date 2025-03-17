package pages;

import data.SignInObjectData;
import helper.methods.ElementsMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "pass")
    WebElement passwordField;

    @FindBy(id = "send2")
    WebElement signInButton;


    public void fillUserData(SignInObjectData data) {
        ElementsMethod.writeInTextbox(emailField, data.getEmail());
        ElementsMethod.writeInTextbox(passwordField, data.getPassword());

    }

    public void clickSignInButton(){
        ElementsMethod.clickOnElement(signInButton);
    }

}
