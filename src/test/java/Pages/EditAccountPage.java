package Pages;

import HelperMethods.ElementsMethod;
import ObjectData.CreateAnAccountObjectData;
import ObjectData.EditAccountObjectData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditAccountPage {
    WebDriver driver;
    ElementsMethod elementsMethod;
    public String newEmailFinal;

    public EditAccountPage(WebDriver driver) {
        this.driver = driver;
        this.elementsMethod = new ElementsMethod(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='box box-information']//*[@class='action edit']")
    WebElement editAccountButton;

    @FindBy(id = "firstname")
    WebElement firstNameField;

    @FindBy(id = "lastname")
    WebElement lastNameField;

    @FindBy(id = "change-email")
    WebElement changeEmailCheckbox;

    @FindBy(id = "change-password")
    WebElement changePasswordCheckbox;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "current-password")
    WebElement currentPasswordField;

    @FindBy(id = "password")
    WebElement newPasswordField;

    @FindBy(id = "password-confirmation")
    WebElement newPasswordConfirmationField;

    @FindBy(xpath = "//*[@class='action save primary']")
    WebElement saveButton;



    public void clickOnEditAccount() {
        elementsMethod.clickOnElement(editAccountButton);
    }

    public void clickOnSave(){
        elementsMethod.clickOnElement(saveButton);
    }

    public void editFirstLastNameInfo(EditAccountObjectData data){
        elementsMethod.writeInTextbox(firstNameField, data.getFirstName());
        elementsMethod.writeInTextbox(lastNameField, data.getLastName());
    }

    public void selectChangeEmailOption() {
        elementsMethod.clickOnElement(changeEmailCheckbox);
    }

    public void selectChangePasswordOption() {
        elementsMethod.clickOnElement(changePasswordCheckbox);
    }

    public void changeEmailInfo(EditAccountObjectData data) {

        newEmailFinal = data.getEmail().replace("{random}", System.currentTimeMillis()+"");
        elementsMethod.writeInTextbox(emailField, newEmailFinal);

    }

    public void inputCurrentPassword(String password) {
        elementsMethod.writeInTextbox(currentPasswordField,password);
    }

    public void inputNewPassword(EditAccountObjectData data){
        elementsMethod.writeInTextbox(newPasswordField, data.getNewPassword());
        elementsMethod.writeInTextbox(newPasswordConfirmationField, data.getConfirmNewPassword());
    }


}
