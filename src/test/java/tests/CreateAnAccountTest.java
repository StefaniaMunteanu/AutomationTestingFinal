package tests;

import helper.classes.CreateAnAccount;
import logger.LoggerUtility;
import data.CreateAnAccountObjectData;
import ShareDataBrowser.Hooks;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateAnAccountPage;
import xmlReaderUtility.XmlReader;

import java.sql.SQLException;
import java.util.Map;

public class CreateAnAccountTest extends Hooks {
    CreateAnAccount createAnAccount;
    CreateAnAccountPage createAnAccountPage;
    private Map<String, CreateAnAccountObjectData> createAnAccountObjectDataMap;

    @Test
    public void createAnAccountCorrectDataTest() throws SQLException {
        createAnAccountObjectDataMap = XmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_1");

        createAnAccount = new CreateAnAccount(getDriver());
        createAnAccount.CreateAccount(data);

        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL after account creation does not match.");
        LoggerUtility.infoTest("check that the account was created");
        ChainTestListener.log("check that the account was created");

        String result = getDriver().findElement(By.xpath("//*[@class='box-content']/p")).getText();
        Assert.assertEquals(data.getFirstName() + " " + data.getLastName() +"\n"+ data.getEmail(), result);
        LoggerUtility.infoTest("check the account data");
        ChainTestListener.log("check the account data");

        Assert.assertEquals("Thank you for registering with Main Website Store.", getDriver().findElement(By.xpath("//*[@class='page messages']/div/div/div/div[1]")).getText());
        LoggerUtility.infoTest("Check the register account validation message.");
        ChainTestListener.log("Check the register account validation message.");

        createAnAccount.addEntryInTable(data);
        LoggerUtility.infoTest("Data added in the database.");
        ChainTestListener.log("Data added in the database.");

    }



    @Test
    public void createAnAccountWithInvalidEmail() throws SQLException {
        createAnAccountObjectDataMap = XmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_2");

        createAnAccount = new CreateAnAccount(getDriver());
        createAnAccount.CreateAccount(data);

        String result = getDriver().findElement(By.id("email_address-error")).getText();
        Assert.assertEquals("Please enter a valid email address (Ex: johndoe@domain.com).", result);
        LoggerUtility.infoTest("check the email validation message was displayed");

    }
}
