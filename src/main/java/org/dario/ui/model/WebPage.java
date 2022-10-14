package org.dario.ui.model;


import org.dario.ui.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is the base class for Web pages
 *
 */
public class WebPage extends BasePage {

    public WebPage(){
        super(DriverManager.getDriverInstance());
        PageFactory.initElements(DriverManager.getDriverInstance(),this);
    }

    @Override
    public void clickElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", element);
        element.click();
    }

    @Override
    public void clickElement(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        clickElement(element);
    }

    public void clickElementWithoutScroll(By locator) {
        super.clickElement(locator);
    }

    public void clickElementWithoutScroll(WebElement element) {
        super.clickElement(element);
    }


    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * Method that returns the complet url to  the page
     * BASE_URL + url
     *
     * @return String complete
     */
    public String getCompleteURL() {
        return BASE_URL + url;
    }

    /**
     * Method is used to navigated to page complete url
     * BASE_URL + url
     *
     * @return String complete
     */
    public void visit() {
        driver.get(getCompleteURL());
    }

    /**
     * Method is used to navigated to certain page
     *
     * @param url to go. Example: https://api.nasa.gov/index.html
     * @return String complete
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * switch to the last tab
     */
    public void switchToLastTab() {
        String lastHandle = "";
        for (String winHandle : getDriver().getWindowHandles()) {
            lastHandle = winHandle;
        }
        getDriver().switchTo().window(lastHandle);
    }

    /**
     * Wait to appear more than one tab
     */
    public void waitTabOpening() {
        int attempts = 0;
        WebDriver wd = getDriver();
        long wait = Constants.getWaitScriptTimeout() * 2;
        while ((wd.getWindowHandles().size() == 1) && attempts <= wait) {
            sleep(500);
            attempts++;
        }
    }

    private Select getSelect(WebElement element) {
        return new Select(element);
    }

    public void selectOptionDropdownByText(WebElement element, String text) {
        Select dropdown = getSelect(element);
        dropdown.selectByVisibleText(text);
    }

    public void selectOptionDropdownByText(By locator, String text) {
        selectOptionDropdownByText(getWebElement(locator), text);
    }

    public void selectOptionDropdownByValue(WebElement element, String value) {
        Select dropdown = getSelect(element);
        dropdown.selectByValue(value);
    }

    public void selectOptionDropdownByValue(By locator, String value) {
        selectOptionDropdownByValue(getWebElement(locator), value);
    }

    public void deselectAllOptionsDropdown(WebElement element) {
        Select dropdown = getSelect(element);
        dropdown.deselectAll();
    }

    public void deselectAllOptionsDropdown(By locator) {
        deselectAllOptionsDropdown(getWebElement(locator));
    }

    public void deselectOptionDropdownByValue(WebElement element, String value) {
        Select dropdown = getSelect(element);
        dropdown.deselectByValue(value);
    }

    public void deselectOptionDropdownByText(WebElement element, String text) {
        Select dropdown = getSelect(element);
        dropdown.deselectByValue(text);
    }

    public void deselectOptionDropdownByValue(By locator, String value) {
        deselectOptionDropdownByValue(getWebElement(locator), value);
    }

    public void deselectOptionDropdownByText(By locator, String text) {
        deselectOptionDropdownByText(getWebElement(locator), text);
    }

    /**
     * Similar to does element exist, but also verifies that only one such
     * element exists and that it is displayed
     *
     * @param by By statement locating the element.
     * @return boolean if one and only one element matching the locator is
     * found, and if it is displayed and enabled, F otherwise.
     */
    protected boolean isElementPresentAndDisplayed(By by) {
        boolean isPresent = false;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            List<WebElement> elements = driver.findElements(by);
            isPresent = (elements.size() == 1) && elements.get(0).isDisplayed();
        } finally {
            driver.manage().timeouts().implicitlyWait(Constants.getWaitImlicitTimeout(), TimeUnit.SECONDS);
        }

        return isPresent;
    }

}