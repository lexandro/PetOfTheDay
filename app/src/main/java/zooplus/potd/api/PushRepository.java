package zooplus.potd.api;

import java.util.HashMap;
import java.util.Map;

public class PushRepository {
    private ApiClient apiClient;

    public PushRepository(ApiClient apiClient) {

        this.apiClient = apiClient;
    }

    public void push(String message) {
        // IOS??? SUCH A BLASPHEMY!!! :)
        Map<String, String> params = new HashMap<>();
        params.put("message", message);
        apiClient.post("/push/ios", params);
    }
}
