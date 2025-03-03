package Tests;

import HelperClasses.CreateAnAccount;
import Logger.LoggerUtility;
import ObjectData.CreateAnAccountObjectData;
import ObjectData.EditAccountObjectData;
import Pages.EditAccountPage;
import ShareDataBrowser.Hooks;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import xmlReaderUtility.xmlReader;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Map;

public class EditTheAccountTest extends Hooks {
    EditAccountPage editAccountPage;

    CreateAnAccount createAnAccount;
    private Map<String, CreateAnAccountObjectData> createAnAccountObjectDataMap;
    private Map<String, EditAccountObjectData> editAccountObjectDataMap;

    @Test
    public void editAccountInfo() throws SQLException {
        createAnAccountObjectDataMap = xmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_1");

        createAnAccount = new CreateAnAccount(getDriver());
        createAnAccount.CreateAccount(data);

        editAccountPage = new EditAccountPage(getDriver());
        editAccountPage.clickOnEditAccount();

        editAccountObjectDataMap = xmlReader.loadData("src/test/resources/editAccountData.xml", EditAccountObjectData.class);
        EditAccountObjectData data2 = editAccountObjectDataMap.get("dataSet_1");

        editAccountPage.editFirstLastNameInfo(data2);

        editAccountPage.selectChangeEmailOption();
        editAccountPage.selectChangePasswordOption();

        editAccountPage.changeEmailInfo(data2);

        editAccountPage.inputCurrentPassword(data.getPassword());
        editAccountPage.inputNewPassword(data2);

        editAccountPage.clickOnSave();


        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/login/";
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL after the account edit doesn't match.");
        LoggerUtility.infoTest("check that the account was saved after edit");
        ChainTestListener.log("check that the account was saved after edit");


        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@class='page messages']//*[@class='message-success success message']"), 1));
        String result = getDriver().findElement(By.xpath("//*[@class='page messages']//*[@class='message-success success message']")).getText();

        Assert.assertTrue(result.contains("You saved the account information."), "Edit information saved.");

    }
}
