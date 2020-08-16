package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public Properties prop;

    public WebDriver initDriver(){
        initProp();

        String browser = System.getProperty("browser");
        if(browser == null){
            browser = prop.getProperty("browser").toLowerCase();
        }

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser name, selecting Chrome");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        long timeOut = Long.parseLong(prop.getProperty("timeout"));
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        return driver;
    }

    private void initProp(){
        try{
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\test.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
