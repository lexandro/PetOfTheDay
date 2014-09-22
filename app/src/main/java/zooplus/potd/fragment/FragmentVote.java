package zooplus.potd.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import potd.zooplus.com.petoftheday.R;
import zooplus.potd.Config;
import zooplus.potd.activity.Main;
import zooplus.potd.domain.ImageURL;
import zooplus.potd.service.PetService;

import static zooplus.potd.fragment.VoteOperation.DISLIKE;
import static zooplus.potd.fragment.VoteOperation.LIKE;
import static zooplus.potd.fragment.VoteOperation.RANDOM;

public class FragmentVote extends Fragment {

    private static final String ARG_PARAM1 = "imageId";

    private PetService petService;

    private static Integer imageId = null;

    private OnFragmentInteractionListener mListener;

    public static FragmentVote newInstance(Integer imageId) {
        FragmentVote fragment = new FragmentVote();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, String.valueOf(imageId));
        fragment.setArguments(args);
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
        } else if (imageId == null) {
            (new VoteAsyncTask()).executeInBackground(RANDOM);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vote, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (imageId != null) {
            init(imageId);
        }
        configureButtonListeners();
    }

    private void init(int imageId) {
        FragmentVote.imageId = imageId;
        Activity activity = getActivity();
        if (activity != null) {
            ImageView imageView = (ImageView) activity.findViewById(R.id.voteImageView);
            Picasso.with(activity.getApplicationContext()).load(Config.getEndpoint() + "/pets/" + imageId + "/image").into(imageView);
        }
    }

    private void animatedInit(int imageId) {
        Main main = (Main) getActivity();
        if (main != null) {
            main.onNavigationDrawerItemSelected(1);
            FragmentManager fragmentManager = getFragmentManager();
            Fragment voteFragment = FragmentVote.newInstance(imageId);
            fragmentManager.beginTransaction().replace(R.id.container, voteFragment).setCustomAnimations(R.animator.fade_in, R.animator.fade_out).commit();
        }
        init(imageId);
    }

    private void configureButtonListeners() {
        Button likeButton = (Button) getActivity().findViewById(R.id.btnLike);
        Button dislikeButton = (Button) getActivity().findViewById(R.id.btnDislike);
        //
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new VoteAsyncTask()).executeInBackground(LIKE);
                FragmentManager fragmentManager = getFragmentManager();
            }
        });
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new VoteAsyncTask()).executeInBackground(DISLIKE);
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

    private class VoteAsyncTask extends AsyncTask<Integer, Void, Integer> {

        private VoteOperation operation;

        @Override
        protected Integer doInBackground(Integer... params) {
            ImageURL result = null;
            if (LIKE.equals(operation)) {
                result = petService.like(imageId);
            } else if (DISLIKE.equals(operation)) {
                result = petService.disLike(imageId);
            } else if (RANDOM.equals(operation)) {
                result = petService.getRandom();
            }
            return result.getId();
        }

        @Override
        protected void onPostExecute(Integer imageId) {
            super.onPostExecute(imageId);
            animatedInit(imageId);

        }

        public void executeInBackground(VoteOperation operation) {
            this.operation = operation;
            execute();
        }

    }

}
