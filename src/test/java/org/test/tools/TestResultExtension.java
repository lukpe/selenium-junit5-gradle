package org.test.tools;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.lang.reflect.Field;

public class TestResultExtension implements TestWatcher {

    private static final Logger logger = LogManager.getLogger(TestResultExtension.class);

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.trace("[" + context.getRequiredTestClass().getName() + "] " +
                context.getRequiredTestMethod().getName() +
                context.getDisplayName() + " - PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("[" + context.getRequiredTestClass().getName() + "] " +
                context.getRequiredTestMethod().getName() +
                context.getDisplayName() + " - FAILED");

        try {
            Object test = context.getRequiredTestInstance();
            Field d = test.getClass().getDeclaredField("driver");
            d.setAccessible(true);
            WebDriver driver = (WebDriver) d.get(test);

            File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filename = "scrShot_" + context.getRequiredTestClass().getName() + "_" +
                    context.getDisplayName().replace(" ","_") +
                    "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(scrShot, new File (System.getProperty("user.dir") + "\\build\\screenshots\\" + filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
