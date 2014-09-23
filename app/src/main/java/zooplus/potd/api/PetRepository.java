package zooplus.potd.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import zooplus.potd.domain.ImageURL;
import zooplus.potd.domain.factory.ImageUrlFactory;

public class PetRepository {

    private ApiClient apiClient;

    private ConcurrentHashMap imageUrls;

    public PetRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<ImageURL> getAllImages() {

        JSONObject resultJson = apiClient.get("/pets/images");
        List<ImageURL> allImages = null;
        try {
            allImages = ImageUrlFactory.createImageUrlListfrom(resultJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allImages;
    }

    public void submitLikeVote(int imageId) {
        apiClient.post(String.format("/pets/%d/like", imageId));

    }

    public void submitDislikeVote(int imageId) {
        apiClient.post(String.format("/pets/%d/dislike", imageId));
    }

    public ImageURL getRandom() {
        JSONObject resultJson = apiClient.get("/pets/random/image");
        ImageURL imageURL = null;
        try {
            imageURL = ImageUrlFactory.createRandomImageFrom(resultJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageURL;
    }
}
