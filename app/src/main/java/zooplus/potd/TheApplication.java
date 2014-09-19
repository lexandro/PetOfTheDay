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
    }

    public static Context getAppContext() {
        return TheApplication.context;
    }
}
