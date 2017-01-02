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
import com.androidexamples.app.interfaces.OnDetachFragmentListener;
import com.androidexamples.app.util.Constants;

/**
 * Created by marcos on 29/12/2016.
 */

public class FoodFragment extends Fragment {

    // Views
    private TextView textViewFood;
    private ImageView imageViewFood;
    private LinearLayout linearLayoutFood;

    // Another objects
    private OnDetachFragmentListener onDetachFragmentListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_food, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            textViewFood = (TextView)getView().findViewById(R.id.textViewFood);
            imageViewFood = (ImageView)getView().findViewById(R.id.imageViewFood);
            linearLayoutFood = (LinearLayout)getView().findViewById(R.id.linearLayoutFood);
            int resource = getArguments().getInt(Constants.KEY_RESOURCE);
            String text = getArguments().getString(Constants.KEY_TEXT);
            int color = getArguments().getInt(Constants.KEY_COLOR);
            setValues(resource, text, color);
        }

        setRetainInstance(true);
    }

    private void setValues(int resource, String text, int color) {
        linearLayoutFood.setBackgroundColor(ContextCompat.getColor(getActivity(), color));
        imageViewFood.setImageResource(resource);
        textViewFood.setText(text);
    }

    public void setOnDetachFragmentListener(OnDetachFragmentListener onDetachFragmentListener) {
        this.onDetachFragmentListener = onDetachFragmentListener;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if(onDetachFragmentListener != null)
            onDetachFragmentListener.onDetachFragment(getTag());
    }
}
