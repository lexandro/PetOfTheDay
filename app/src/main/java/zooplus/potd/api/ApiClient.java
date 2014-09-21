package zooplus.potd.api;

import java.util.List;

import zooplus.potd.api.domain.ImageURL;
import zooplus.potd.api.domain.factory.ImageLinkFactory;

public class ApiClient {

    private Connector connector;

    public ApiClient(Connector connector) {
        this.connector = connector;
    }

    public List<ImageURL> getImages() {
//        final List<ImageLink>[] result = new List<ImageLink>[1];


        connector.call("/pets/images", ImageLinkFactory.getResponseListener());


        return null;
    }
}
