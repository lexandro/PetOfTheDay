package zooplus.potd.async;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import zooplus.potd.adapter.ImagesAdapter;
import zooplus.potd.api.domain.ImageURL;
import zooplus.potd.service.ImagesService;

public class BitmapsLoaderTask extends AsyncTask<Void, ImageView, Void> {
    private ImagesAdapter myTaskAdapter;
    private ImagesService imagesService;

    public BitmapsLoaderTask(ImagesAdapter adapter) {
        myTaskAdapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        myTaskAdapter.clear();
    }

    @Override
    protected Void doInBackground(Void... params) {
        Context context = myTaskAdapter.getMContext();
        //
        ImageView imageView = null;
        myTaskAdapter.clear();
        //
        imagesService = new ImagesService();
        List<ImageURL> imageUrls = imagesService.getAllUrl();

        Collections.sort(imageUrls);
        //
        for (ImageURL imageURL : imageUrls) {
            imageView = new ImageView(context);
            try {
                URL url = new URL(imageURL.getUrl());
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageView.setImageBitmap(bmp);
                imageView.setId(imageURL.getId());
//            Picasso.with(myTaskAdapter.getMContext()).load("http://10.0.2.2:8080/pets/1/image").into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            myTaskAdapter.add(imageView);
            publishProgress(imageView);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(ImageView... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        myTaskAdapter.notifyDataSetChanged();
    }
}