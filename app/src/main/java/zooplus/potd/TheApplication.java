package zooplus.potd;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Date;

public class TheApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        TheApplication.context = getApplicationContext();
        Log.i("potd", "### Starting: " + (new Date()).toString());
//        Config.init("http://zooplussalt-test.apigee.net");
//        Config.init("http://10.0.2.2:8080");
        Config.init("http://192.168.100.10:8080");
    }

    public static Context getAppContext() {
        return TheApplication.context;
    }


}
