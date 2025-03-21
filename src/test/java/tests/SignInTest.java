package tests;

import ShareDataBrowser.Hooks;
import data.SignInObjectData;
import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import xmlReaderUtility.XmlReader;

import java.time.Duration;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignInTest extends Hooks {
    HomePage homePage;
    SignInPage signInPage;
    private Map<String, SignInObjectData> signInObjectDataMap;

    @Test
    public void signInWithCorrectData() {
        signInObjectDataMap = XmlReader.loadData("src/test/resources/signInData.xml", SignInObjectData.class);
        SignInObjectData data = signInObjectDataMap.get("dataSet_1");

        completeSignInForm(data);

        String expectedUrl = "https://magento.softwaretestingboard.com/";
        String actualUrl = getDriver().getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL after account creation does not match.");
        LoggerUtility.infoTest("Check the redirect after sign in");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@class='page-header']//*[@class='logged-in']"), 1));
        String result = getDriver().findElement(By.xpath("//*[@class='page-header']//*[@class='logged-in']")).getText();

        assertTrue(result.contains("Welcome,"), "Sign in correct");
        LoggerUtility.infoTest("check the sign in was done successfully");

    }

    @Test
    public void trySignInWithIncorrectEmail() {
        trySignInWithIncorrectData("dataSet_2");
    }

    @Test
    public void trySignInWithIncorrectPassword() {
        trySignInWithIncorrectData("dataSet_3");
    }

    public void trySignInWithIncorrectData(String dataSetNumber){
        signInObjectDataMap = XmlReader.loadData("src/test/resources/signInData.xml", SignInObjectData.class);
        SignInObjectData data = signInObjectDataMap.get(dataSetNumber);

        completeSignInForm(data);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='page messages']"), 1));

        String result = getDriver().findElement(By.xpath("//div[@class='page messages']")).getText();
        assertEquals(result, "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.", "Validation message correct.");
        LoggerUtility.infoTest("check the validation message for incorrect data");

    }

    public void completeSignInForm(SignInObjectData data) {

        homePage = new HomePage(getDriver());
        signInPage = new SignInPage(getDriver());

        homePage.clickSignIn();
        LoggerUtility.infoTest("The user click on sign in.");

        signInPage.fillUserData(data);
        LoggerUtility.infoTest("The user fill the data.");

        signInPage.clickSignInButton();
        LoggerUtility.infoTest("The user clicks sign in button.");
    }

}
