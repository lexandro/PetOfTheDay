package zooplus.potd.domain.factory;

import com.android.volley.Response;

import java.util.List;

import zooplus.potd.domain.ImageURL;

public class ImageLinkFactory {


    public static Response.Listener<String> getResponseListener() {
        Response.Listener<String> result = null;

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
//                result[0] = ImageLinkFactory.getImagesfromJson(s);

            }
        };


        return result;

    }

    public static List<ImageURL> getImagesfromJson(String json) {
        return null;
    }

    public static ImageURL getImagefromJson(String json) {
        return null;
    }


}