package Tests;

import HelperClasses.CreateAnAccount;
import Logger.LoggerUtility;
import ObjectData.CreateAnAccountObjectData;
import ShareDataBrowser.Hooks;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.CreateAnAccountPage;
import Pages.HomePage;
import ShareDataBrowser.ShareData;
import xmlReaderUtility.xmlReader;

import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

public class CreateAnAccountTest extends Hooks {
    CreateAnAccount createAnAccount;
    CreateAnAccountPage createAnAccountPage;
    private Map<String, CreateAnAccountObjectData> createAnAccountObjectDataMap;

    @Test
    public void createAnAccountCorrectDataTest() throws SQLException {
        createAnAccountObjectDataMap = xmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
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
    }

    @Test
    public void createAnAccountWithInvalidEmail() throws SQLException {
        createAnAccountObjectDataMap = xmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_2");

        createAnAccount = new CreateAnAccount(getDriver());
        createAnAccount.CreateAccount(data);

        String result = getDriver().findElement(By.id("email_address-error")).getText();
        Assert.assertEquals("Please enter a valid email address (Ex: johndoe@domain.com).", result);
        LoggerUtility.infoTest("check the email validation message was displayed");

    }
}
