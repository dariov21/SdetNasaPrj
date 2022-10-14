package gov.nasa.web;

import org.dario.ui.DriverManager;
import gov.nasa.web.pages.Home;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class Tests {

   private Home homePage = new Home();


   @Test(testName = "Verify Nasa api home page is shown properly.")
   public void verifyNasaApiHomeIsShownProperly(){
       homePage.visit();
       Assert.assertTrue(homePage.getHeaderContentUL().isDisplayed(),"Header content is not displayed.");
   }

    @Test(testName = "Verify Header Navigation bar is working OK.")
    public void verifyHeaderNavigationIsWorkingOK(){
        homePage.visit();
        homePage.assertHeaderNav();
        homePage.getOverviewNavBarA().click();
        homePage.assertHeaderNav();
        homePage.getGenerateApiKeyA().click();
        homePage.assertHeaderNav();
        homePage.getAuthenticationA().click();
        homePage.assertHeaderNav();
        homePage.getBrowseAPIsA().click();
        homePage.assertHeaderNav();
    }


   @Test(testName = "Verify search api is working properly.")
   public void verifySearchAPIIsWorkingOK(){
       homePage.visit();
       homePage.getBrowseAPIsA().click();
       homePage.getSeachBigFieldInput().sendKeys("APOD");
       homePage.sleep(2);
       Assert.assertTrue(homePage.getApodButton().isDisplayed());
       homePage.getSeachBigFieldInput().clear();
       homePage.getSeachBigFieldInput().sendKeys("Earth");
       homePage.sleep(2);
       Assert.assertFalse(homePage.getApodButton().isDisplayed());
   }


   @AfterSuite
   public void after() throws Exception {
       DriverManager.dismissDriver();
   }

}
