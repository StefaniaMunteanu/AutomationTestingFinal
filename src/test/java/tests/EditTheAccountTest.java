package tests;

import ShareDataBrowser.Hooks;
import com.aventstack.chaintest.plugins.ChainTestListener;
import data.CreateAnAccountObjectData;
import data.EditAccountObjectData;
import helper.classes.CreateAnAccount;
import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.EditAccountPage;
import xmlReaderUtility.XmlReader;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EditTheAccountTest extends Hooks {
    EditAccountPage editAccountPage;
    CreateAnAccount createAnAccount;

    @Test
    public void editAccountInfo() throws SQLException {
        Map<String, CreateAnAccountObjectData> createAnAccountObjectDataMap = XmlReader.loadData("src/test/resources/createAnAccountData.xml", CreateAnAccountObjectData.class);
        CreateAnAccountObjectData data = createAnAccountObjectDataMap.get("dataSet_1");

        createAnAccount = new CreateAnAccount(getDriver());
        createAnAccount.CreateAccount(data);

        editAccountPage = new EditAccountPage(getDriver());
        editAccountPage.clickOnEditAccount();

        Map<String, EditAccountObjectData> editAccountObjectDataMap = XmlReader.loadData("src/test/resources/editAccountData.xml", EditAccountObjectData.class);
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
        assertEquals(actualUrl, expectedUrl, "URL after the account edit doesn't match.");
        LoggerUtility.infoTest("check that the account was saved after edit");
        ChainTestListener.log("check that the account was saved after edit");


        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@class='page messages']//*[@class='message-success success message']"), 1));
        String result = getDriver().findElement(By.xpath("//*[@class='page messages']//*[@class='message-success success message']")).getText();

        assertTrue(result.contains("You saved the account information."), "Edit information saved.");

        createAnAccount.addEntryInTable(data);
        editAccountPage.updateEmailInTable(data, data2);

    }
}
