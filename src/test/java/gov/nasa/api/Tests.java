package gov.nasa.api;

import gov.nasa.api.model.Camera;
import gov.nasa.api.base.CuriosityTest;
import gov.nasa.api.model.Photo;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tests extends CuriosityTest {

    @Test(testName = "Retrieve the first 10 Mars photos made by \"Curiosity\" on 1000 Martian sol.")
    public void retrieveFirst10MarsPhotosWithSol(){
        List<Photo> photos = getCuriosityPhotos(1000l,1l);
        int index = 0;
        long[] ids = {102693l, 102694l, 102850l, 102851l, 424905l, 424906l, 424907l, 424908l, 424909l, 424910l };
        for (Photo p : photos.subList(0,10)
             ) {
            softAssert().assertEquals(p.getId(),ids[index++]);
        }
        softAssert().assertAll();
    }

    @Test(testName = "Retrieve the first 10 Mars photos made by \"Curiosity\" on Earth date equal to 1000 Martian sol.")
    public void retrieveFirst10MarsPhotosWithEarthDate(){
        List<Photo> photos = getCuriosityPhotosByEarthDate("2015-05-30",1);
        int index = 0;
        long[] ids = {102693l, 102694l, 102850l, 102851l, 424905l, 424906l, 424907l, 424908l, 424909l, 424910l };
        for (Photo p : photos.subList(0,10)
        ) {
            softAssert().assertEquals(p.getId(),ids[index++]);
        }
        softAssert().assertAll();
    }

    @Test(testName = "Retrieve and compare the first 10 Mars photos made by \"Curiosity\" on 1000 sol and on Earth date equal to 1000 Martian sol.")
    public void compareFirst10MarsPhotosBetweenSOlEarthDate(){
        List<Photo> photosEarthDate = getCuriosityPhotosByEarthDate("2015-05-30",1);
        List<Photo> photosSol = getCuriosityPhotos(1000l,1l);
        softAssert().assertEquals(photosSol.subList(0,10),photosEarthDate.subList(0,10));
        softAssert().assertAll();
    }

    @Test(testName = "Validate that the amounts of pictures that each \"Curiosity\" camera took on 1000 Mars sol is not greater than 10 times the amount taken by other cameras on the same date.")
    public void validateAmountsOfPicturesEachSOlPhotos(){
        List<Photo> photosSol = getCuriosityPhotos(1000l,0l);
        Map<Camera, Long> countPhotosByCamera = photosSol.stream().collect(Collectors.groupingBy(Photo::getCamera, Collectors.counting()));

        for (Map.Entry<Camera,Long> entry: countPhotosByCamera.entrySet()) {
           for(Map.Entry<Camera,Long> entry1: countPhotosByCamera.entrySet()){
               if(entry.getKey().equals(entry1.getKey()));
                 double times = (double)entry.getValue() / entry1.getValue();
                 System.out.println(times);
                 softAssert().assertFalse(times > 10,"Amount of photos in :"+entry.getKey().getName()+"is "+times+" times greater than "+ entry1.getKey().getName()+" photos are :"+ entry1.getValue());
               }
        }

        softAssert().assertAll();
    }

}
