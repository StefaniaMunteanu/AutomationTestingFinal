package Pages;

import HelperMethods.ElementsMethod;
import ObjectData.CreateAnAccountObjectData;
import ObjectData.signInObjectData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    WebDriver driver;
    ElementsMethod elementsMethod;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.elementsMethod = new ElementsMethod(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "pass")
    WebElement passwordField;

    @FindBy(id = "send2")
    WebElement signInButton;


    public void fillUserData(signInObjectData data){
        elementsMethod.writeInTextbox(emailField, data.getEmail());
        elementsMethod.writeInTextbox(passwordField, data.getPassword());

    }

    public void clickSignInButton(){
        elementsMethod.clickOnElement(signInButton);
    }



}
