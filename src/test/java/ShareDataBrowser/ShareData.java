package ShareDataBrowser;

import ShareDataBrowser.browser.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class ShareData {
    private WebDriver driver;

    public void initializeBrowser() {
        driver = new BrowserFactory().getBrowserFactory();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public void clearBrowser() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }


}
