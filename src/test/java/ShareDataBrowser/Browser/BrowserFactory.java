package ShareDataBrowser.Browser;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    public WebDriver getBrowserFactory() {
        String browser = System.getProperty("browser");
        System.out.println("Ruleaza: " + browser);

        switch (browser){
            case BrowserType.BROWSER_CHROME:
                ChromeService chromeBrowser = new ChromeService();
                chromeBrowser.openBrowser();
                return chromeBrowser.getDriver();
            case BrowserType.BROWSER_EDGE:
                EdgeService edgeBrowser = new EdgeService();
                edgeBrowser.openBrowser();
                return edgeBrowser.getDriver();

        }
        return null;
    }
}
