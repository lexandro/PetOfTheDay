package zooplus.potd.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import potd.zooplus.com.petoftheday.R;

public class FragmentVote extends Fragment {

    private static final String ARG_PARAM1 = "imageId";

    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public static FragmentVote newInstance(int imageId) {
        FragmentVote fragment = new FragmentVote();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, String.valueOf(imageId));
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentVote() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vote, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.voteImageView);
        Picasso.with(getActivity().getApplicationContext()).load("http://10.0.2.2:8080/pets/" + mParam1 + "/image").into(imageView);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
