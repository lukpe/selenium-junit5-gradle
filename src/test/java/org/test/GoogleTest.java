package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.test.pages.GooglePage;
import org.test.tools.TestResultExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestResultExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoogleTest extends TestBase {
    public WebDriver driver;
    private GooglePage gp;


    @BeforeAll
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = initDriver();
        gp = new GooglePage(driver);

    }

    @BeforeEach
    public void getHomePage(){
        driver.get(prop.getProperty("url"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/search_phrases.csv", numLinesToSkip = 1, delimiterString = ";")
    @DisplayName("Search Google with given phrases")
    public void searchTest(String phrase, String result){
        gp.search(phrase);
        assertTrue(gp.checkResults(result));
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
