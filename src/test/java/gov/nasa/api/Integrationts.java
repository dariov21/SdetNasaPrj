package gov.nasa.api;

import gov.nasa.api.base.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Integrationts extends BaseTest {


    @Test(testName="Bad Api Key")
    public void authenticateBadAPiKey(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("api_key","BadKEY");
        Response response = getRaw(getUrlForTest()+"/planetary/apod",parameters);
        response.then().statusCode(HttpStatus.SC_FORBIDDEN);
        response.then().assertThat().extract().jsonPath().get("error.code").equals("API_KEY_INVALID");
        response.then().assertThat().extract().jsonPath().get("error.message").equals("An invalid api_key was supplied. Get one at https://api.nasa.gov:443");
    }

    @Test(testName="Bad URL")
    public void authenticateBadURL(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("api_key","BadKEY");
        Response response = getRaw(getUrlForTest()+"/planetary",parameters);
        response.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test(testName="DEMO_KEY AND RATE Limits")
    public void authenticateDemoKeyIsOk(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("api_key","DEMO_KEY");
        Response response = getRaw(getUrlForTest()+"/planetary/apod",parameters);
        response.then().statusCode(HttpStatus.SC_OK);
        response.then().assertThat().header("X-Ratelimit-Limit","40");
    }

    @Test(testName="NORMAL_KEY AND Rate Limits")
    public void authenticateNormalKeyIsOkAndRateLimits(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("api_key","MWz9yfrC6UYOfTwinHKRIsbZHY9aAO10fl9mQxv2");
        Response response = getRaw(getUrlForTest()+"/planetary/apod",parameters);
        response.then().statusCode(HttpStatus.SC_OK);
        response.then().assertThat().header("X-Ratelimit-Limit","2000");
        int usage = Integer.parseInt(response.header("X-Ratelimit-Remaining"));
        Response response2 = getRaw(getUrlForTest()+"/planetary/apod",parameters);
        response2.then().assertThat().header("X-Ratelimit-Remaining",Integer.toString(--usage));
    }

    @Override
    public String getUrlForTest() {
        return URL_NASA_BASE;
    }
}
