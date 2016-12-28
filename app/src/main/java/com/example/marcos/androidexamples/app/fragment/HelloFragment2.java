package com.example.marcos.androidexamples.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.activity.HelloWorldFragmentActivity;

public class HelloFragment2 extends Fragment {

    private View fragmentLayout;
    private String text;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentLayout = layoutInflater.inflate(R.layout.fragment_hello_fragment2, container, false);
        return fragmentLayout;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getArguments() != null) {
            ((TextView)getActivity().findViewById(R.id.textViewFrag2)).setText(
                    getArguments().getString(HelloWorldFragmentActivity.TEXT_KEY));
        }
    }
}
