package org.dario;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {
    private static Logger logger = Logger.getLogger(PropertyManager.class);
    private static ThreadLocal<Map<String, String>> testInfo = new ThreadLocal<>();
    private static final String PROPERTY_FILE_NAME = "/config.properties";
    private static final String QA_ENV_URL = "https://api.nasa.gov/index.html";
    private static Properties properties;
    private PropertyManager() {
    }

    private static Properties getProperties() {
        if (properties == null) {
            try {
                loadProperties();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }
        return properties;
    }

    public static String getProperty(String propertyKey) {
        String value = getProperties().getProperty(propertyKey);
        return value;
    }

    public static boolean isPropertyPresentAndNotEmpty(String propertyKey) {
        return getProperties().containsKey(propertyKey) && !getProperties().getProperty(propertyKey).isEmpty();
    }

    private static void loadProperties() throws IOException {
        InputStream inputStream = PropertyManager.class.getResourceAsStream(PROPERTY_FILE_NAME);
        properties = new Properties();
        properties.load(inputStream);
    }

    public static String getEnv(){
        return getProperty("nasa.env");
    }

    public static boolean isQAEnv(){
        return getProperty("nasa.env").equalsIgnoreCase("QA");
    }

    public static String getEnvUrl() {
        String env = getEnv();
        switch (env){
            case "QA" : return getProperty("nasa.qa.url");
            default: return "";
        }
    }

    public static void setTestInfo(String key, String value) {
        if(testInfo.get() == null){
            testInfo.set(new HashMap<>());
        }
        testInfo.get().put(key,value);
    }

    public static String getTestInfo(String key){
        return testInfo.get().get(key);
    }

    public static int getFluentWaitPollingTime() {
        String pollingTime = getProperty("nasa.wait.fluent.polling.time");
        if(StringUtils.isEmpty(pollingTime)){
            return 10;
        }else{
            return Integer.parseInt(pollingTime);
        }
    }
}
