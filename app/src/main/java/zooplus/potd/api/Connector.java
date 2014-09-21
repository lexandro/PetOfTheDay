package zooplus.potd.api;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import zooplus.potd.TheApplication;

public class Connector {


    private static String endPoint;


    public Connector(String newEndPoint) {
        endPoint = newEndPoint;
    }

    public void call(String link, Response.Listener<String> callbackListener) {
        RequestQueue queue = Volley.newRequestQueue(TheApplication.getAppContext());
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, endPoint + link, callbackListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("potd", "Something wrong happened " + error);
            }
        });
        //
        queue.add(stringRequest);
    }
}
