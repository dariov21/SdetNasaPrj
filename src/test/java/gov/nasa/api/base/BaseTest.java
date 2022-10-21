package gov.nasa.api.base;

import org.dario.PropertyManager;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest extends org.dario.api.BaseTest {
    protected final static String NASA_KEY = PropertyManager.getProperty("nasa.api.key");
    protected final static String URL_NASA_BASE = PropertyManager.getProperty("nasa.qa.url");
    protected final static String URL_NASA_PHOTOS = "/mars-photos/api";
    public ThreadLocal<SoftAssert> softAssertThreadLocal = new ThreadLocal<>();

    public abstract String getUrlForTest();

    public SoftAssert softAssert(){
        if(softAssertThreadLocal.get() == null){
            softAssertThreadLocal.set(new SoftAssert());
        }
        return softAssertThreadLocal.get();
    }
    enum API_NAME{

        SOL("sol"),
        PAGE("page"),
        API_KEY("api_key"),
        EARTH_DATE("earth_date");

        API_NAME(String name){
            this.name = name;
        }
        String name;
    }

}
