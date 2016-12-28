package com.example.marcos.androidexamples.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marcos.androidexamples.R;

public class HelloFragment1 extends Fragment {

    private View fragmentLayout;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentLayout = layoutInflater.inflate(R.layout.fragment_hello_fragment1, container, false);
        return fragmentLayout;
    }

    public void setText(String text) {
        if(text != null) {
            ((TextView)getActivity().findViewById(R.id.textViewFrag1)).setText(text);
        }
    }
}
