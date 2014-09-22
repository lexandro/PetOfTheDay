package zooplus.potd;

import zooplus.potd.service.PetService;

public class Config {

    private static PetService petService;
    private static String endpoint;


    public static void init(String endpoint) {
        Config.endpoint = endpoint;
        //
        petService = new PetService();
    }

    public static PetService getPetService() {
        return petService;
    }

    public static String getEndpoint() {
        return endpoint;
    }
}
