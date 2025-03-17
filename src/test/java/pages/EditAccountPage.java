package pages;

import data.CreateAnAccountObjectData;
import data.EditAccountObjectData;
import database.queries.UserInfoTable;
import helper.methods.ElementsMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.SQLException;

public class EditAccountPage {
    WebDriver driver;
    UserInfoTable userInfoTable;

    public EditAccountPage(WebDriver driver) throws SQLException {
        this.driver = driver;
        userInfoTable = new UserInfoTable();
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
        ElementsMethod.clickOnElement(editAccountButton);
    }

    public void clickOnSave(){
        ElementsMethod.clickOnElement(saveButton);
    }

    public void editFirstLastNameInfo(EditAccountObjectData data){
        ElementsMethod.writeInTextbox(firstNameField, data.getFirstName());
        ElementsMethod.writeInTextbox(lastNameField, data.getLastName());
    }

    public void selectChangeEmailOption() {
        ElementsMethod.clickOnElement(changeEmailCheckbox);
    }

    public void selectChangePasswordOption() {
        ElementsMethod.clickOnElement(changePasswordCheckbox);
    }

    public void changeEmailInfo(EditAccountObjectData data) {

        ElementsMethod.writeInTextbox(emailField, data.getEmail());

    }

    public void inputCurrentPassword(String password) {
        ElementsMethod.writeInTextbox(currentPasswordField, password);
    }

    public void inputNewPassword(EditAccountObjectData data){
        ElementsMethod.writeInTextbox(newPasswordField, data.getNewPassword());
        ElementsMethod.writeInTextbox(newPasswordConfirmationField, data.getConfirmNewPassword());
    }

    public void updateEmailInTable (CreateAnAccountObjectData data, EditAccountObjectData data2) throws SQLException {
        userInfoTable.updateEmailAfterEdit(data, data2);
    }

}
