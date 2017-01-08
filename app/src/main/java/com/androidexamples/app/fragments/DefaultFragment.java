package com.androidexamples.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.androidexamples.app.R;
import com.androidexamples.app.utils.Constants;

/**
 * Created by marcos on 08/01/2017.
 */

public class DefaultFragment extends BaseFragment {

    // Views
    private TextView textView;
    private FrameLayout container;

    private int stringResource;
    private int colorResource;
    private int textColorResource;

    public DefaultFragment() {}

    public static DefaultFragment newInstance(int stringResource, int colorResource, int textColorResource) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_COLOR, colorResource);
        bundle.putInt(Constants.KEY_TEXT, stringResource);
        bundle.putInt(Constants.KEY_TEXT_COLOR, textColorResource);
        DefaultFragment defaultFragment = new DefaultFragment();
        defaultFragment.setArguments(bundle);
        return defaultFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_default, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            stringResource = getArguments().getInt(Constants.KEY_TEXT);
            colorResource = getArguments().getInt(Constants.KEY_COLOR);
            textColorResource = getArguments().getInt(Constants.KEY_TEXT_COLOR);
            textView = (TextView)getView().findViewById(R.id.textView);
            container = (FrameLayout)getView().findViewById(R.id.container_root);
            setValues();
        }
    }

    private void setValues() {
        textView.setText(getString(stringResource));
        textView.setTextColor(ContextCompat.getColor(getActivity(), textColorResource));
        container.setBackgroundColor(ContextCompat.getColor(getActivity(), colorResource));
    }
}
