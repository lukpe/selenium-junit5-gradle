package org.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.test.pages.DuckDuckGoPage;
import org.test.tools.TestResultExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestResultExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchTest extends TestBase {
    public WebDriver driver;
    private DuckDuckGoPage ddg;


    @BeforeAll
    public void setUp() {
        driver = initDriver();
        ddg = new DuckDuckGoPage(driver);
    }

    @BeforeEach
    public void getHomePage(){
        driver.get(getProp("url"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/search_phrases.csv", numLinesToSkip = 1, delimiterString = ";")
    @DisplayName("Search Google with given phrases")
    void searchTest(String phrase, String result) {
        ddg.search(phrase);
        assertTrue(ddg.checkResults(result));
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
