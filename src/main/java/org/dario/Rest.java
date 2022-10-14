package org.dario;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Rest {

    protected static Logger log = Logger.getLogger(Rest.class);
    private static Gson gson;
    static {
        gson = new Gson();
    }

    private static void verifyResponse(Response response){
        Assert.assertNotNull(response);
        Assert.assertEquals(response.statusCode(), 200);
    }

    protected static Map<String, String> getJSON_HEADERS(){
        Map<String, String > map =  new HashMap<String, String>();
        map.put("content-type","application/json");
        return map;
    }

    protected  static Map<String, String> getJSON_HEADERS(String ... headers){
        Map<String, String > map =  new HashMap<String, String>();
        for(String header : headers){
            if(header.contains(":")){
                String[] h = header.split(":");
                map.put(h[0],h[1]);
            }else{
                log.warn(header + " is not Ok please review it.");
            }
        }
        return map;
    }

    public static String toJson(Object object){
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> type){
        return gson.fromJson(json,type);
    }

    public static Response put(String url){
        return RestAssured.put(url);
    }

    protected static Response post(String url, Map<String, String> headers, Object body){
        return  given()
                .headers(headers).body(toJson(body))
                .when()
                .log().all()
                .post(url);
    }

    protected static Response post(String url, Map<String, String> headers){
        return  given()
                .headers(headers)
                .when()
                .log().all()
                .post(url);
    }

    protected static Map postMap(String url, Map<String, String> headers, Object body){
        return  post(url, headers, body, Map.class);
    }

    protected static <T> T post(String url, Map<String, String> headers, Object body, Class<T> type){
        Response response = post(url, headers, body);
        verifyResponse(response);
        log.info("RESPONSE : ---------- ");
        log.info(response.toString());
        log.info("BODY : ---------- ");
        log.info(response.getBody().asString());
        log.info("END REPONSE : ---------- ");
        return fromJson(response.getBody().asString(),type);
    }

    public Response get(String url,Map<String, String> headers,Map<String, String> parameters){
        return  getRaw(url,headers,parameters).then().assertThat().statusCode(HttpStatus.SC_OK).extract().response();
    }

    public Response getRaw(String url,Map<String, String> headers,Map<String, String> parameters){
        return  given()
                .headers(headers)
                .params(parameters)
                .when()
                .log().all()
                .get(url).then().log().all().extract().response();
    }

    public Response getRaw(String url,Map<String, String> parameters){
        return  given()
                .params(parameters)
                .when()
                .log().all()
                .get(url).then().log().all().extract().response();
    }

    public Response get(String url,Map<String, String> headers){
        return  given()
                .headers(headers)
                .when()
                .log().all()
                .get(url);
    }

    public <T> T get(String url,Map<String, String> headers,Class<T> type){
        Response response =  given()
                .headers(headers)
                .when()
                .log().all()
                .get(url);
        return response.body().as(type);
    }

    protected static Response put(String url, Map<String, String> headers, Object body){
        return  given()
                .headers(headers).body(toJson(body))
                .when()
                .log().all()
                .put(url);
    }
    protected static Response put(String url, String user, String pass, Map<String, String> headers, Object body){
        return  given().auth().basic(user,pass)
                .headers(headers).body(toJson(body))
                .when()
                .log().all()
                .put(url);
    }


    protected static <T> T put(String url, Map<String, String> headers, Object body, Class<T> type){
        Response response = put(url, headers, body);
        verifyResponse(response);
        log.info("RESPONSE : ---------- ");
        log.info(response.toString());
        log.info("BODY : ---------- ");
        log.info(response.getBody().asString());
        log.info("END REPONSE : ---------- ");
        return fromJson(response.getBody().asString(),type);
        //return response.body().as(type);
    }
}