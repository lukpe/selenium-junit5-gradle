package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
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
    public WebDriver driver;
    public Properties prop;

    public WebDriver initDriver() {
        initProp();

        String browser = System.getProperty("browser");
        if (browser.isEmpty()) {
            browser = prop.getProperty("browser");
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
                    System.out.println("Invalid browser name, selecting Chrome");
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
        int timeOut = Integer.parseInt(prop.getProperty("timeout"));
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        return driver;
    }

    private void initProp() {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
                    "\\src\\main\\resources\\test.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
