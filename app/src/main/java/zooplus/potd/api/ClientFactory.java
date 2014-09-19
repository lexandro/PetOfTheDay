package zooplus.potd.api;

public class ClientFactory {

    private Connector connector;


    public ClientFactory(Connector connector) {
        this.connector = connector;
    }

    public ApiClient newInstance() {
        return new ApiClient(connector);
    }
}
