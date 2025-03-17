package tests;

import ShareDataBrowser.Hooks;
import com.aventstack.chaintest.plugins.ChainTestListener;
import data.CreateAnAccountObjectData;
import helper.classes.CreateAnAccount;
import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import xmlReaderUtility.XmlReader;

import java.sql.SQLException;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CreateAnAccountTest extends Hooks {
    CreateAnAccount createAnAccount;
    private Map<String, CreateAnAccountObjectData> createAnAccountObjectDataMap;

    @Test
    public void createAnAccountCorrectDataTest() throws SQLException {
        createAnAccountObjectDataMap = XmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_1");

        createAnAccount = new CreateAnAccount(getDriver());
        createAnAccount.CreateAccount(data);

        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        String actualUrl = getDriver().getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL after account creation does not match.");
        LoggerUtility.infoTest("check that the account was created");
        ChainTestListener.log("check that the account was created");

        String result = getDriver().findElement(By.xpath("//*[@class='box-content']/p")).getText();
        assertEquals(data.getFirstName() + " " + data.getLastName() + "\n" + data.getEmail(), result);
        LoggerUtility.infoTest("check the account data");
        ChainTestListener.log("check the account data");

        assertEquals(getDriver().findElement(By.xpath("//*[@class='page messages']/div/div/div/div[1]")).getText(), "Thank you for registering with Main Website Store.");
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
        assertEquals(result, "Please enter a valid email address (Ex: johndoe@domain.com).");
        LoggerUtility.infoTest("check the email validation message was displayed");

    }
}
