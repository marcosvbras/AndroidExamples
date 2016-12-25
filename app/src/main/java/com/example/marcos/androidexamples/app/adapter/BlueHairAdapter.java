package com.example.marcos.androidexamples.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.entity.BlueHair;

import java.util.List;

/**
 * Created by marcos on 25/12/2016.
 */

public class BlueHairAdapter extends RecyclerView.Adapter {

    private List<BlueHair> listBlueHair;
    private Context context;
    private int lastLoadedViewPosition;
    private static final int FADE_DURATION = 1000; // em milisegundos

    public BlueHairAdapter(List<BlueHair> listBlueHair, Context context) {
        this.listBlueHair = listBlueHair;
        this.context = context;
        this.lastLoadedViewPosition = -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_blue_hair, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        BlueHair blueHair = listBlueHair.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)viewHolder;
        myViewHolder.textView.setText("Item " + blueHair.getId());
        myViewHolder.imageView.setImageResource(blueHair.getDrawableId());
        setScaleAnimation(myViewHolder.container, position);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        ((MyViewHolder)holder).clearAnimation();
    }

    private void setAnimation(View viewToAnimate, int currentPosition) {
        // Se a última view não foi exibida anteriormente na lista, ela recebe a animação
        if (currentPosition > lastLoadedViewPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastLoadedViewPosition = currentPosition;
        }
    }

    private void setFadeAnimation(View viewToAnimate, int currentPosition) {
        // Se a última view não foi exibida anteriormente na lista, ela recebe a animação
        if(currentPosition > lastLoadedViewPosition) {
            AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(FADE_DURATION);
            viewToAnimate.startAnimation(anim);
            lastLoadedViewPosition = currentPosition;
        }
    }

    private void setScaleAnimation(View viewToAnimate, int currentPosition) {
        // Se a última view não foi exibida anteriormente na lista, ela recebe a animação
        if(currentPosition > lastLoadedViewPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(FADE_DURATION);
            viewToAnimate.startAnimation(anim);
            lastLoadedViewPosition = currentPosition;
        }
    }

    @Override
    public int getItemCount() {
        return listBlueHair.size();
    }

    public BlueHair getItemAtPosition(int position) {
        return listBlueHair.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        LinearLayout container;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            container = (LinearLayout) itemView.findViewById(R.id.container);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }
}
