package com.androidexamples.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidexamples.app.R;
import com.androidexamples.app.util.Constants;

/**
 * Created by marcos on 29/12/2016.
 */

public class AnimalFragment extends Fragment {

    // Views
    private TextView textViewAnimals;
    private ImageView imageViewAnimals;
    private LinearLayout linearLayoutAnimals;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_animals, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            textViewAnimals = (TextView)getView().findViewById(R.id.textViewAnimals);
            imageViewAnimals = (ImageView)getView().findViewById(R.id.imageViewAnimals);
            linearLayoutAnimals = (LinearLayout)getView().findViewById(R.id.linearLayoutAnimals);
            int color = getArguments().getInt(Constants.KEY_COLOR);
            int imageResource = getArguments().getInt(Constants.KEY_RESOURCE);
            String text = getArguments().getString(Constants.KEY_TEXT);
            setValues(color, imageResource, text);
        }

        setRetainInstance(true);
    }

    private void setValues(int color, int imageResource, String text) {
        linearLayoutAnimals.setBackgroundColor(ContextCompat.getColor(getActivity(), color));
        imageViewAnimals.setImageResource(imageResource);
        textViewAnimals.setText(text);
    }
}
