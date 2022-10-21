package org.dario.ui;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum TestType implements IDriverBuilder {
    WEB_CHROME{
        @Override
        public RemoteWebDriver getDriver() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("test-type", "new-window", "disable-extensions","start-maximized");
            Map<String, Object> prefs = new HashMap<>();
            options.addArguments("disable-logging", "silent", "ignore-certificate-errors");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches",
                    Collections.singletonList("enable-automation"));
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            options.setCapability(ChromeOptions.CAPABILITY, options);
            options.setCapability("chrome.switches", Arrays.asList("--disable-extensions", "--disable-logging",
                    "--ignore-certificate-errors", "--log-level=0", "--silent"));
            options.setCapability("silent", true);
            System.setProperty("webdriver.chrome.silentOutput", "true");
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        }

    },
    WEB_CHROME_HEADLESS{
        @Override
        public RemoteWebDriver getDriver() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("test-type", "new-window", "disable-extensions","start-maximized");
            options.addArguments("--headless");
            Map<String, Object> prefs = new HashMap<>();
            options.addArguments("disable-logging", "silent", "ignore-certificate-errors");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches",
                    Collections.singletonList("enable-automation"));
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            options.setCapability(ChromeOptions.CAPABILITY, options);
            options.setCapability("chrome.switches", Arrays.asList("--disable-extensions", "--disable-logging",
                    "--ignore-certificate-errors", "--log-level=0", "--silent"));
            options.setCapability("silent", true);
            System.setProperty("webdriver.chrome.silentOutput", "true");
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        }

    };
    ;




    @Override
    public RemoteWebDriver getDriver() throws Exception {
        return null;
    }

    public static TestType getType(String t){
       for(TestType testType: TestType.values()){
           if(testType.name().contains(t)){
               return testType;
           }
       }
       Assert.fail("Please review configuration "+t+" is not supported");
       return null;
    }
}
