package com.example.marcos.androidexamples.app.fragment;

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

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.interfaces.OnDetachFragmentListener;
import com.example.marcos.androidexamples.app.util.Constants;

/**
 * Created by marcos on 29/12/2016.
 */

public class StarWarsFragment extends Fragment {

    private TextView textViewFrag1;
    private ImageView imageViewStarWars;
    private OnDetachFragmentListener onDetachFragmentListener;
    private LinearLayout linearLayoutStarWars;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_star_wars, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            String text = getArguments().getString(Constants.KEY_TEXT);
            int resource = getArguments().getInt(Constants.KEY_RESOURCE);
            int color = getArguments().getInt(Constants.KEY_COLOR);
            textViewFrag1 = (TextView)getView().findViewById(R.id.textViewStarWars);
            imageViewStarWars = (ImageView)getView().findViewById(R.id.imageViewStarWars);
            linearLayoutStarWars = (LinearLayout)getView().findViewById(R.id.linearLayoutStarWars);
            setValues(resource, text, color);
        }

        setRetainInstance(true);
    }

    public void setValues(int resource, String text, int color) {
        imageViewStarWars.setImageResource(resource);
        textViewFrag1.setText(text);
        linearLayoutStarWars.setBackgroundColor(ContextCompat.getColor(getActivity(), color));
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
