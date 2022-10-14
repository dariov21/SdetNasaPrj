package gov.nasa.api.base;

import org.dario.Rest;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest extends Rest {
    protected final static String NASA_KEY = "1zTNPtdGjArOmsEpblPNGYUBQD0Z8kl5Z4gI5zS9";
    protected final static String URL_NASA_BASE = "https://api.nasa.gov";
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
