package org.test;

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
        String browser = prop.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("W=Invalid browser name, selecting Chrome");
                driver = new ChromeDriver();
        }
        long timeOut = Long.parseLong(prop.getProperty("timeout"));
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        return driver;
    }

    private void initProp(){
        try{
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\test.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
