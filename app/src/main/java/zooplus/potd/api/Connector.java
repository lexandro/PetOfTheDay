package zooplus.potd.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

public class Connector {


    private static String endPoint;


    public Connector(String newEndPoint) {
        endPoint = newEndPoint;
    }

    public String post(String myUrl) {
        return post(myUrl, null);
    }

    public String post(String myUrl, Map<String, String> parameters) {
        InputStream is = null;

        try {
            String paramString = null;
            if (parameters != null && parameters.size() > 0) {
                StringBuilder paramStringBuilder = new StringBuilder();
                for (Map.Entry<String, String> paramEntry : parameters.entrySet()) {
                    if (paramStringBuilder.length() == 0) {
                        paramStringBuilder.append('?');
                    } else {
                        paramStringBuilder.append('&');
                    }
                    paramStringBuilder.append(paramEntry.getKey()).append('=').append(paramEntry.getValue());
                }

                paramString = paramStringBuilder.toString().replace(" ", "%20");
            }
            //
            String urlString = endPoint + myUrl;
            if (paramString != null) {
                urlString += paramString;
            }
            URL url = new URL(urlString);
            //
            HttpURLConnection conn = null;

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String get(String myUrl) {
        InputStream is = null;
        try {
            URL url = new URL(endPoint + myUrl);
            HttpURLConnection conn = null;

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        int len = 500;
        char[] buffer = new char[len];
        //
        int size = reader.read(buffer);
        while (size > 0) {
            if (size == len) {
                result.append(buffer);
            } else {
                result.append(Arrays.copyOf(buffer, size));
            }
            size = reader.read(buffer);
        }


        return result.toString();
    }

}
