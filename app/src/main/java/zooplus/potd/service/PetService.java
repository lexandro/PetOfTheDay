package zooplus.potd.service;

import java.util.List;

import zooplus.potd.Config;
import zooplus.potd.api.PetRepository;
import zooplus.potd.domain.ImageURL;

public class PetService {

    private PetRepository petRepository;
    private PushService pushService;

    public PetService(PetRepository petRepository, PushService pushService) {
        init(petRepository, pushService);
    }

    public List<ImageURL> getAllUrl() {
        List<ImageURL> allImages = petRepository.getAllImages();
        //
        return allImages;
    }

    public ImageURL like(int imageId) {
        petRepository.submitLikeVote(imageId);
        pushService.push(Config.getUserName() + " likes your picture");
        return getRandom();
    }

    public ImageURL disLike(int imageId) {
        petRepository.submitDislikeVote(imageId);
        return getRandom();
    }

    public ImageURL getRandom() {
        return petRepository.getRandom();
    }

    public void init(PetRepository petRepository, PushService pushService) {
        this.petRepository = petRepository;
        this.pushService = pushService;
    }
}
