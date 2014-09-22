package zooplus.potd.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ApiClient {

    private Connector connector;

    public ApiClient(Connector connector) {
        this.connector = connector;
    }


    public JSONObject get(String urlString) {
        String resultString = connector.get(urlString);
        //
        JSONObject result = null;
        try {
            result = new JSONObject(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void post(String urlString, Map<String, String> params) {
        String resultString = connector.post(urlString, params);

    }

    public void post(String urlString) {
        String resultString = connector.post(urlString);

    }
}
