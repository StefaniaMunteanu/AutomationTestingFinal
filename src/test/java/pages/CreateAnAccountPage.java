package pages;

import data.CreateAnAccountObjectData;
import helper.methods.ElementsMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.SQLException;

public class CreateAnAccountPage {
    WebDriver driver;

    public CreateAnAccountPage(WebDriver driver) throws SQLException {
        this.driver = driver;
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
        ElementsMethod.writeInTextbox(firstNameField, data.getFirstName());
        ElementsMethod.writeInTextbox(lastNameField, data.getLastName());
        ElementsMethod.writeInTextbox(emailField, data.getEmail());
        ElementsMethod.writeInTextbox(passwordField, data.getPassword());
        ElementsMethod.writeInTextbox(confirmPasswordField, data.getConfirmPassword());

    }

    public void clickOnSubmit() {
        ElementsMethod.clickOnElement(createAnAccountButton);
    }

}
