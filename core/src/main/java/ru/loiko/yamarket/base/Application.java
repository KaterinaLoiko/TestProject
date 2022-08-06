package ru.loiko.yamarket.base;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Application {

    private WebDriver webDriver;

    public WebDriver getWebDriver(){
        return webDriver;
    }

    private WebDriver initiateDriver(String remoteDriver, String browserType) throws MalformedURLException  {
        DesiredCapabilities dc = null;
        if (browserType.equals("ff")) {
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            dc = DesiredCapabilities.firefox();

            dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        } else if (browserType.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

            dc = DesiredCapabilities.chrome();
            dc.setCapability(ChromeOptions.CAPABILITY, options);
        }
        webDriver = new RemoteWebDriver(
                new URL(remoteDriver),
                dc
        );
        webDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        return webDriver;

    }

    public synchronized void openBrowser(String remoteDriver, String url, String browserType) throws MalformedURLException {
        webDriver = initiateDriver(remoteDriver, browserType);
        WebDriverRunner.setWebDriver(webDriver);
        webDriver.get(url);
        webDriver.manage().window().maximize();
    }

    public synchronized void closeBrowser() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
