package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    private static final Logger logger = LogManager.getLogger(TestBase.class);

    private WebDriver driver;

    public WebDriver initDriver() {
        String browser = System.getProperty("browser");
        if (browser.isEmpty()) {
            browser = getProp("browser");
        }
        browser = browser.toLowerCase();

        String remote = System.getProperty("remote");
        if (remote.isEmpty()) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    logger.warn("Invalid browser name, selecting Chrome");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
        } else {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setBrowserName(browser);
            try {
                driver = new RemoteWebDriver(new URL(remote), dc);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        int timeOut = Integer.parseInt(getProp("timeout"));
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        return driver;
    }

    public String getProp(String name) {
        String propFile = System.getProperty("user.dir") + "\\src\\main\\resources\\test.properties";
        try (FileInputStream file = new FileInputStream(propFile)) {
            Properties prop = new Properties();
            prop.load(file);
            return prop.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
