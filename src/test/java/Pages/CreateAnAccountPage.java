package Pages;

import HelperMethods.ElementsMethod;
import ObjectData.CreateAnAccountObjectData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class CreateAnAccountPage {
    WebDriver driver;
    ElementsMethod elementsMethod;
    public String emailFinal;

    public CreateAnAccountPage(WebDriver driver) {
        this.driver = driver;
        this.elementsMethod = new ElementsMethod(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "firstname")
    WebElement firstNameField;

    @FindBy(id = "lastname")
    WebElement lastNameField;

    @FindBy(id = "email_address")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "password-confirmation")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//*[@class='action submit primary']")
    WebElement createAnAccountButton;

    public void fillCreateAnAccountInfo(CreateAnAccountObjectData data){
        elementsMethod.writeInTextbox(firstNameField, data.getFirstName());
        elementsMethod.writeInTextbox(lastNameField, data.getLastName());
        emailFinal = data.getEmail().replace("{random}", System.currentTimeMillis()+"");
        elementsMethod.writeInTextbox(emailField, emailFinal);
        elementsMethod.writeInTextbox(passwordField, data.getPassword());
        elementsMethod.writeInTextbox(confirmPasswordField, data.getConfirmPassword());

    }

    public void clickOnSubmit() {
        elementsMethod.clickOnElement(createAnAccountButton);
    }


}
