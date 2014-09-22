package zooplus.potd.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import potd.zooplus.com.petoftheday.R;
import zooplus.potd.Config;
import zooplus.potd.adapter.ImagesAdapter;
import zooplus.potd.domain.ImageURL;
import zooplus.potd.service.PetService;


public class FragmentGallery extends Fragment {


    private OnFragmentInteractionListener mListener;

    private ImagesAdapter myImagesAdapter;

    private BitmapsLoaderTask bitmapsLoaderTask;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final GridView gridview = (GridView) getActivity().findViewById(R.id.imagesGridView);
        myImagesAdapter = new ImagesAdapter(getActivity());
        gridview.setAdapter(myImagesAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ActionBar actionBar = getActivity().getActionBar();
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                //
                FragmentManager fragmentManager = getFragmentManager();
                Fragment voteFragment = FragmentVote.newInstance(v.getId());
                FragmentTransaction tx = fragmentManager.beginTransaction();
                tx.replace(R.id.container, voteFragment).setCustomAnimations(R.animator.fade_in, R.animator.fade_out);
                tx.addToBackStack(null);
                tx.commit();
            }
        });
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle(getActivity().getString(R.string.title_gallery));
        //
        bitmapsLoaderTask = new BitmapsLoaderTask(myImagesAdapter);
        bitmapsLoaderTask.execute();

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myImagesAdapter.clear();
                gridview.refreshDrawableState();
                bitmapsLoaderTask = new BitmapsLoaderTask(myImagesAdapter);
                bitmapsLoaderTask.execute();
            }
        });


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    public class BitmapsLoaderTask extends AsyncTask<Void, ImageView, Void> {
        private ImagesAdapter myTaskAdapter;
        private PetService petService;

        public BitmapsLoaderTask(ImagesAdapter adapter) {
            myTaskAdapter = adapter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myTaskAdapter.clear();
            petService = Config.getPetService();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Context context = myTaskAdapter.getMContext();
            //
            ImageView imageView = null;
            myTaskAdapter.clear();
            //
            List<ImageURL> imageUrls = petService.getAllUrl();

            Collections.sort(imageUrls);
            //
            for (ImageURL imageURL : imageUrls) {
                imageView = new ImageView(context);
                int size = (int) context.getResources().getDimension(R.dimen.image_size);
                imageView.setLayoutParams(new GridView.LayoutParams(size, size));
                //
                try {
                    URL url = new URL(imageURL.getUrl());
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    imageView.setImageBitmap(bmp);
                    imageView.setId(imageURL.getId());
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
            myTaskAdapter.notifyDataSetChanged();

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            myTaskAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
