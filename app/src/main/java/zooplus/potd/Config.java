package zooplus.potd;

import zooplus.potd.api.ApiClient;
import zooplus.potd.api.Connector;
import zooplus.potd.api.PetRepository;
import zooplus.potd.api.PushRepository;
import zooplus.potd.service.PetService;
import zooplus.potd.service.PushService;

public class Config {

    //
    private static Connector connector;
    //
    private static PushRepository pushRepository;
    private static PetRepository petRepository;
    //
    private static PushService pushService;
    private static PetService petService;

    private static String userName = "Robert";
    //
    private static String endpoint;
    private static ApiClient apiClient;

    public static void init(String endpoint) {
        Config.endpoint = endpoint;
        connector = new Connector(endpoint);
        apiClient = new ApiClient(connector);
        //
        pushRepository = new PushRepository(apiClient);
        petRepository = new PetRepository(apiClient);

        pushService = new PushService(pushRepository);

        if (petService == null) {
            petService = new PetService(petRepository, pushService);
        } else {
            petService.init(petRepository, pushService);
        }
    }

    public static PetService getPetService() {
        return petService;
    }


    public static String getEndpoint() {
        return endpoint;
    }

    public static String getUserName() {
        return userName;
    }


}
