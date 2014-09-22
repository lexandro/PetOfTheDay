package zooplus.potd.service;

import zooplus.potd.api.PushRepository;

public class PushService {


    private PushRepository pushRepository;

    public PushService(PushRepository pushRepository) {

        this.pushRepository = pushRepository;
    }

    public void push(String message) {
        pushRepository.push(message);
    }
}
