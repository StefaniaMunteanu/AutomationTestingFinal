package HelperClasses;


import Logger.LoggerUtility;
import ObjectData.CreateAnAccountObjectData;
import Pages.CreateAnAccountPage;
import Pages.HomePage;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;


public class CreateAnAccount{
    WebDriver driver;
    HomePage homePage;
    CreateAnAccountPage createAnAccountPage;

    public CreateAnAccount(WebDriver driver) throws SQLException {
        this.driver = driver;
        createAnAccountPage = new CreateAnAccountPage(driver);
    }

    public void CreateAccount(CreateAnAccountObjectData data) throws SQLException {
        homePage = new HomePage(driver);

        createAnAccountPage.addEntryInTable(data);

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

    public String getEmail() {
        return createAnAccountPage.emailFinal;
    }
}
