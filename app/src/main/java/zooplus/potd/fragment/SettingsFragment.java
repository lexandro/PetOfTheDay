package zooplus.potd.fragment;


import android.app.ActionBar;
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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle(getString(R.string.title_settings));
    }

    @Override
    public void onStart() {
        super.onStart();
        setupAutoComplete();

        Button okButton = (Button) getActivity().findViewById(R.id.btnSettingsOk);
        okButton.setOnClickListener(new OkButtonListener());

    }

    private class OkButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            AutoCompleteTextView textView = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteEndpoint);
            String endPoint = textView.getText().toString();
            Config.init(endPoint);
            getFragmentManager().popBackStackImmediate();
        }
    }

    private void setupAutoComplete() {
        AutoCompleteTextView textView = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteEndpoint);
        textView.setText(Config.getEndpoint());
        String[] endpoints = getResources().getStringArray(R.array.endpointList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, endpoints);
        textView.setAdapter(adapter);
    }


}
