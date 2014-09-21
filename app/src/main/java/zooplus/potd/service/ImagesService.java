package zooplus.potd.service;

import java.util.ArrayList;
import java.util.List;

import zooplus.potd.api.domain.ImageURL;

public class ImagesService {


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
        return allUrl;
    }
}
