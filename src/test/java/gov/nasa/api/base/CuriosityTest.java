package gov.nasa.api.base;

import gov.nasa.api.model.Photo;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuriosityTest extends BaseTest {
    protected final static String URL_V1_CURIOSITY_PHOTOS = "/v1/rovers/curiosity/photos";

    public List<Photo> getCuriosityPhotos(long sol, long page){
        Response response = getCuriosityPhotosResponse(sol, page);
        return response.jsonPath().getList("photos",Photo.class);
    }

    public Response getCuriosityPhotosResponse(long sol, long page){
        Map<String, String> parameters = new HashMap<>();
        parameters.put(API_NAME.SOL.name, Long.toString(sol));
        parameters.put(API_NAME.PAGE.name, Long.toString(page));
        parameters.put(API_NAME.API_KEY.name,NASA_KEY );
        return  get(getUrlForTest(),new HashMap<>(),parameters);
    }

    public List<Photo> getCuriosityPhotosByEarthDate(String earth_Date, long page){
        Response response = getCuriosityPhotosByEarthDateResponse(earth_Date, page);
        return response.jsonPath().getList("photos",Photo.class);
    }

    public Response getCuriosityPhotosByEarthDateResponse(String earth_Date, long page){
        Map<String, String> parameters = new HashMap<>();
        parameters.put(API_NAME.EARTH_DATE.name, earth_Date);
        if(page != 0)
        parameters.put(API_NAME.PAGE.name, Long.toString(page));
        parameters.put(API_NAME.API_KEY.name,NASA_KEY );
        return  get(getUrlForTest(),new HashMap<>(),parameters);
    }

    @Override
    public String getUrlForTest() {
        return URL_NASA_BASE+URL_NASA_PHOTOS+URL_V1_CURIOSITY_PHOTOS;
    }
}
