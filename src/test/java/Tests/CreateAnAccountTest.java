package Tests;

import Logger.LoggerUtility;
import ObjectData.CreateAnAccountObjectData;
import ShareDataBrowser.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.CreateAnAccountPage;
import Pages.HomePage;
import ShareDataBrowser.ShareData;
import xmlReaderUtility.xmlReader;

import java.util.Map;
import java.util.Random;

public class CreateAnAccountTest extends Hooks {
    HomePage homePage;
    CreateAnAccountPage createAnAccountPage;
    private Map<String, CreateAnAccountObjectData> createAnAccountObjectDataMap;

    @Test
    public void createAnAccountCorrectDataTest() {
        createAnAccountObjectDataMap = xmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_1");

        homePage = new HomePage(getDriver());
        createAnAccountPage = new CreateAnAccountPage(getDriver());

        homePage.clickCreateAnAccount();
        LoggerUtility.infoTest("The user click on create an account.");
        createAnAccountPage.fillCreateAnAccountInfo(data);
        LoggerUtility.infoTest("Fill data for creating an account");
        createAnAccountPage.clickOnSubmit();
        LoggerUtility.infoTest("click on submit");

        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL after account creation does not match.");
        LoggerUtility.infoTest("check that the account was created");

        String result = getDriver().findElement(By.xpath("//*[@class='box-content']/p")).getText();
        Assert.assertEquals(data.getFirstName() + " " + data.getLastName() +"\n"+ createAnAccountPage.emailFinal, result);
        LoggerUtility.infoTest("check the account data");
    }

    @Test
    public void createAnAccountWithInvalidEmail() {
        createAnAccountObjectDataMap = xmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_2");

        homePage = new HomePage(getDriver());
        createAnAccountPage = new CreateAnAccountPage(getDriver());

        homePage.clickCreateAnAccount();
        LoggerUtility.infoTest("The user click on create an account.");
        createAnAccountPage.fillCreateAnAccountInfo(data);
        LoggerUtility.infoTest("Fill data for creating an account");
        createAnAccountPage.clickOnSubmit();
        LoggerUtility.infoTest("click on submit");

        String result = getDriver().findElement(By.id("email_address-error")).getText();
        Assert.assertEquals("Please enter a valid email address (Ex: johndoe@domain.com).", result);
        LoggerUtility.infoTest("check the email validation message was displayed");

    }
}
