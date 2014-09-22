package zooplus.potd.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import potd.zooplus.com.petoftheday.R;
import zooplus.potd.adapter.ImagesAdapter;
import zooplus.potd.async.BitmapsLoaderTask;


public class FragmentGallery extends Fragment {


    private OnFragmentInteractionListener mListener;

    private ImagesAdapter myImagesAdapter;

    private BitmapsLoaderTask bitmapsLoaderTask;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridView gridview = (GridView) getActivity().findViewById(R.id.imagesGridView);
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
                fragmentManager.beginTransaction().replace(R.id.container, voteFragment).commit();
            }
        });

        //
        bitmapsLoaderTask = new BitmapsLoaderTask(myImagesAdapter);
        bitmapsLoaderTask.execute();

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

}
