package zooplus.potd.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import potd.zooplus.com.petoftheday.R;
import zooplus.potd.Config;

public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        AutoCompleteTextView textView = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteEndpoint);
        textView.setText(Config.getEndpoint());
        String[] endpoints = getResources().getStringArray(R.array.endpointList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, endpoints);
        textView.setAdapter(adapter);

        Button okButton = (Button) getActivity().findViewById(R.id.btnSettingsOk);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView textView = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteEndpoint);
                String endPoint = textView.getText().toString();
                Config.init(endPoint);
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
}
