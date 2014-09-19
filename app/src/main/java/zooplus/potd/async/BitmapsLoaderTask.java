package zooplus.potd.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import zooplus.potd.adapter.ImagesAdapter;

public class BitmapsLoaderTask extends AsyncTask<Void, ImageView, Void> {
    private ImagesAdapter myTaskAdapter;

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
        ImageView imageView = new ImageView(context);
        publishProgress(imageView);
        return null;
    }

    @Override
    protected void onProgressUpdate(ImageView... values) {
        Picasso.with(myTaskAdapter.getMContext()).load("http://10.0.2.2:8080/pets/1/image").into(values[0]);
        myTaskAdapter.add(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        myTaskAdapter.notifyDataSetChanged();
    }
}