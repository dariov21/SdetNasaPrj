package gov.nasa.web;

import com.google.inject.Inject;
import gov.nasa.BasicGuiceModule;
import org.dario.ui.DriverManager;
import gov.nasa.web.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.file.attribute.BasicFileAttributes;

@Guice(modules = BasicGuiceModule.class)
public class HomeTests {



    private HomePage homePage;


    @BeforeSuite(alwaysRun = true)
    public void setBeforeTests(){
        homePage = new HomePage();
    }


   @Test(testName = "Verify Nasa api home page is shown properly.", groups = { "functional", "web"})
   public void verifyNasaApiHomeIsShownProperly(){
       homePage.visit();
       Assert.assertTrue(homePage.getHeaderContentUL().isDisplayed(),"Header content is not displayed.");
   }

    @Test(testName = "Verify Header Navigation bar is working OK.", groups = { "functional", "web"})
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


   @Test(testName = "Verify search api is working properly.", groups = { "functional", "web"})
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


   @AfterSuite(alwaysRun = true)
   public void after() throws Exception {
       DriverManager.dismissDriver();
   }

}
