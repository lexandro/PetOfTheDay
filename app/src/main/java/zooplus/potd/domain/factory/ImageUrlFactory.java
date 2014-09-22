package zooplus.potd.domain.factory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zooplus.potd.domain.ImageURL;

public class ImageUrlFactory {


    private static final String REL = "rel";
    private static final String HREF = "href";
    private static final String LINKS = "links";

    private static Pattern idPattern = Pattern.compile("pets/(\\d+)/image");

    public static List<ImageURL> createImageUrlListfrom(JSONObject jsonObject) throws JSONException {
        List<ImageURL> result = new ArrayList<>();
        //
        JSONArray linksArray = jsonObject.getJSONArray(LINKS);
        for (int i = 0; i < linksArray.length(); i++) {
            ImageURL imageUrl = createImageUrlFrom(linksArray.getJSONObject(i));
            result.add(imageUrl);
        }
        //
        return result;
    }

    public static ImageURL createImageUrlFrom(JSONObject jsonObject) throws JSONException {
        String href = jsonObject.getString(HREF);
        int rel = Integer.valueOf(jsonObject.getString(REL));
        return new ImageURL(rel, href);
    }

    public static ImageURL createRandomImageFrom(JSONObject jsonObject) throws JSONException {
        JSONArray jsonLinks = jsonObject.getJSONArray(LINKS);
        ImageURL result = null;
        for (int i = 0; i < jsonLinks.length(); i++) {
            JSONObject jsonLink = jsonLinks.getJSONObject(i);
            if ("image".equalsIgnoreCase(jsonLink.getString(REL))) {
                String href = jsonLink.getString(HREF);
                Matcher matcher = idPattern.matcher(href);
                if (matcher.find()) {
                    String id = matcher.group(1);
                    result = new ImageURL(Integer.valueOf(id), href);
                }
            }
        }
        return result;
    }
}
