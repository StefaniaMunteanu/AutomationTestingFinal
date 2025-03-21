package helper.classes;


import com.aventstack.chaintest.plugins.ChainTestListener;
import data.CreateAnAccountObjectData;
import database.queries.UserInfoTable;
import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import pages.CreateAnAccountPage;
import pages.EditAccountPage;
import pages.HomePage;

import java.sql.SQLException;


public class CreateAnAccount {
    WebDriver driver;
    HomePage homePage;
    CreateAnAccountPage createAnAccountPage;
    EditAccountPage editAccountPage;
    UserInfoTable userInfoTable;

    public CreateAnAccount(WebDriver driver) throws SQLException {
        this.driver = driver;
        createAnAccountPage = new CreateAnAccountPage(driver);
        editAccountPage = new EditAccountPage(driver);
        userInfoTable = new UserInfoTable();
    }

    public void CreateAccount(CreateAnAccountObjectData data) throws SQLException {
        homePage = new HomePage(driver);

        ChainTestListener.log("Test started");

        homePage.clickCreateAnAccount();
        LoggerUtility.infoTest("The user click on create an account.");
        ChainTestListener.log("The user click on create an account.");

        createAnAccountPage.fillCreateAnAccountInfo(data);
        LoggerUtility.infoTest("Fill data for creating an account");
        ChainTestListener.log("Fill data for creating an account");

        createAnAccountPage.clickOnSubmit();
        LoggerUtility.infoTest("click on submit");
        ChainTestListener.log("click on submit");
    }

    public void addEntryInTable(CreateAnAccountObjectData data) throws SQLException {
        userInfoTable.insertTableObject(data);
    }

}
