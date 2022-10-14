package gov.nasa.web.pages;

import org.dario.ui.model.WebPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Home extends WebPage {

    @FindBy(xpath = "//h2[contains(text(), 'NASA APIs')]")
    private WebElement nasaApiTitleH2;

    @FindBy(xpath = "//a/span[contains(text(),'Overview')]")
    private WebElement overviewNavBarA;

    @FindBy(xpath = "//a/span[contains(text(),'Generate API Key')]")
    private WebElement generateApiKeyA;

    @FindBy(xpath = "//a/span[contains(text(),'Authentication')]")
    private WebElement authenticationA;

    @FindBy(xpath = "//a/span[contains(text(),'Recover API Key')]")
    private WebElement recoveryApiKeyA;

    @FindBy(xpath = "//a/span[contains(text(),'Browse APIs')]")
    private WebElement BrowseAPIsA;

    @FindBy(id = "headerContent")
    private WebElement headerContentUL;

    @FindBy(id = "basic-search-field-small")
    private WebElement basicSearchSmallInput;

    @FindBy(id = "search-field-big")
    private WebElement seachBigFieldInput;

    @FindBy(xpath = "//span[contains(text(),'Search APIs')]")
    private WebElement basicSeachButton;

    @FindBy(id = "apod")
    private WebElement apodButton;

    public Home() {
        super();
        url = "#getting-started";
    }

    public void assertHeaderNav() {
        this.sleep(2);
        Assert.assertTrue(isElementVisible(getOverviewNavBarA()), "Overview nav header is not displayed.");
        Assert.assertTrue(isElementVisible(getGenerateApiKeyA()), "Generate Api Key nav header is not displayed.");
        Assert.assertTrue(isElementVisible(getAuthenticationA()), "Authentication nav header is not displayed.");
        Assert.assertTrue(isElementVisible(getRecoveryApiKeyA()), "Recovery Api Key nav header is not displayed.");
        Assert.assertTrue(isElementVisible(getBrowseAPIsA()), "Browse Api Key nav header is not displayed.");
    }

    public WebElement getNasaApiTitleH2() {
        return nasaApiTitleH2;
    }

    public WebElement getOverviewNavBarA() {
        return overviewNavBarA;
    }

    public WebElement getGenerateApiKeyA() {
        return generateApiKeyA;
    }

    public WebElement getAuthenticationA() {
        return authenticationA;
    }

    public WebElement getRecoveryApiKeyA() {
        return recoveryApiKeyA;
    }

    public WebElement getBrowseAPIsA() {
        return BrowseAPIsA;
    }

    public WebElement getHeaderContentUL() {
        return headerContentUL;
    }

    public WebElement getBasicSearchSmallInput() {
        return basicSearchSmallInput;
    }

    public WebElement getBasicSeachButton() {
        return basicSeachButton;
    }

    public WebElement getSeachBigFieldInput() {
        return seachBigFieldInput;
    }

    public WebElement getApodButton() {
        return apodButton;
    }
}
