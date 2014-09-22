package zooplus.potd.service;

import java.util.ArrayList;
import java.util.List;

import zooplus.potd.domain.ImageInfo;
import zooplus.potd.domain.ImageURL;

public class PetService {

    public List<ImageURL> getAllUrl() {
        List<ImageURL> allUrl = new ArrayList<>();
        ImageURL imageURL = new ImageURL();
        imageURL.setId(1);
        imageURL.setUrl("http://10.0.2.2:8080/pets/1/image");
        allUrl.add(imageURL);
        //
        imageURL = new ImageURL();
        imageURL.setId(2);
        imageURL.setUrl("http://10.0.2.2:8080/pets/2/image");
        allUrl.add(imageURL);
        //
        imageURL = new ImageURL();
        imageURL.setId(3);
        imageURL.setUrl("http://10.0.2.2:8080/pets/3/image");
        allUrl.add(imageURL);
        //
        return allUrl;
    }

    public ImageURL like(int imageId) {
        ImageURL imageURL = new ImageURL();
        imageURL.setId(3);
        imageURL.setUrl("http://10.0.2.2:8080/pets/3/image");
        return imageURL;
    }

    public ImageURL disLike(int imageId) {
        ImageURL imageURL = new ImageURL();
        imageURL.setId(2);
        imageURL.setUrl("http://10.0.2.2:8080/pets/3/image");
        return imageURL;
    }

    public ImageURL getRandom() {
        ImageURL imageURL = new ImageURL();
        imageURL.setId(1);
        imageURL.setUrl("http://10.0.2.2:8080/pets/1/image");
        return imageURL;
    }

    public ImageInfo getImageInfo(int imageId) {
        return null;
    }
}
