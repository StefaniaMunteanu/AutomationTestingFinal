package ShareDataBrowser;

import ChainTestUtility.ChainUtility;
import Logger.LoggerUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(ChainTestListener.class)
public class Hooks extends ShareData {

    String testName;

    @BeforeMethod
    public void prepareEnvironment() {
        testName = this.getClass().getSimpleName();
        initializeBrowser();
        LoggerUtility.startTestCase(testName);

    }

    @AfterMethod
    public void clearEnvironment(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE){
            byte[] screenshot  = ChainUtility.getScreenshot(getDriver(), testName);
            ChainTestListener.embed(screenshot, "image/png");
            LoggerUtility.errorLog(result.getThrowable().getMessage());
        }
        LoggerUtility.endTestCase(testName);
      //  clearBrowser();
    }

    @AfterSuite
    public void finishLogs(){
        LoggerUtility.mergeFilesIntoOne();
    }

}
