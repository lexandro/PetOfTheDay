package zooplus.potd.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import potd.zooplus.com.petoftheday.R;
import zooplus.potd.Config;
import zooplus.potd.domain.ImageInfo;
import zooplus.potd.domain.ImageURL;
import zooplus.potd.service.PetService;

public class FragmentVote extends Fragment {

    private static final String ARG_PARAM1 = "imageId";

    private PetService petService;

    private int imageId;

    private OnFragmentInteractionListener mListener;

    public static FragmentVote newInstance(Integer imageId) {
        FragmentVote fragment = newInstance();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, String.valueOf(imageId));
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentVote newInstance() {
        FragmentVote fragment = new FragmentVote();
        return fragment;
    }

    public FragmentVote() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        petService = Config.getPetService();
        //
        if (getArguments() != null) {
            imageId = Integer.valueOf(getArguments().getString(ARG_PARAM1));
        } else {
            imageId = petService.getRandom().getId();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vote, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init(imageId);
        configureButtonListeners();
    }

    private void init(int imageId) {
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.voteImageView);
        Picasso.with(getActivity().getApplicationContext()).load(Config.getEndpoint() + "/pets/" + imageId + "/image").into(imageView);
        ImageInfo imageInfo = petService.getImageInfo(imageId);

    }

    private void configureButtonListeners() {
        Button likeButton = (Button) getActivity().findViewById(R.id.btnLike);
        Button dislikeButton = (Button) getActivity().findViewById(R.id.btnDislike);
        //
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageURL result = petService.like(imageId);
                init(result.getId());

            }
        });
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageURL result = petService.disLike(imageId);
                init(result.getId());
            }
        });
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
