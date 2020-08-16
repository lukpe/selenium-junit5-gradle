package org.test.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GooglePage {
    public WebDriver driver;

    public GooglePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchField;

    @FindBy(xpath = "//h3")
    List<WebElement> results;

    public void search(String phrase){
        new Actions(driver).moveToElement(searchField).sendKeys(phrase).sendKeys(Keys.RETURN).perform();
    }

    public boolean checkResults(String phrase){
        for(WebElement element: results){
            if(element.getText().contains(phrase)){
                return true;
            }
        }
        return false;
    }

}
