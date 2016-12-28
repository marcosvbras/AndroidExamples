package com.example.marcos.androidexamples.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marcos.androidexamples.R;
import com.example.marcos.androidexamples.app.entity.SimpleItem;
import com.example.marcos.androidexamples.app.interfaces.RecyclerViewTouchListener;
import com.example.marcos.androidexamples.app.util.Animations;
import com.example.marcos.androidexamples.app.util.Constants;
import com.example.marcos.androidexamples.app.util.RecyclerSettings;

import java.util.List;

/**
 * Created by marcos on 25/12/2016.
 */

public class BlueHairAdapter extends RecyclerView.Adapter {

    private List<SimpleItem> simpleItems;
    private Context context;
    private int lastLoadedViewPosition;
    private RecyclerSettings recyclerSettings;

    public BlueHairAdapter(List<SimpleItem> simpleItems, Context context, RecyclerSettings recyclerSettings) {
        this.simpleItems = simpleItems;
        this.context = context;
        this.lastLoadedViewPosition = -1;
        this.recyclerSettings = recyclerSettings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_blue_hair, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SimpleItem simpleItem = simpleItems.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)viewHolder;
        myViewHolder.textView.setText("Item " + simpleItem.getName());
        myViewHolder.imageView.setImageResource(simpleItem.getImageResource());

        if(recyclerSettings.getAnimationConstant() == Constants.ANIMATION_SLIDE_IN_LEFT)
            Animations.setSlideInLeftAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings.isAlwaysAnimate(), context);
        else if(recyclerSettings.getAnimationConstant() == Constants.ANIMATION_SLIDE_OUT_RIGHT)
            Animations.setSlideOutRightAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings.isAlwaysAnimate(), context);
        else if(recyclerSettings.getAnimationConstant() == Constants.ANIMATION_FADE_IN)
            Animations.setFadeInAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings.isAlwaysAnimate(), context);
        else if(recyclerSettings.getAnimationConstant() == Constants.ANIMATION_FADE_OUT)
            Animations.setFadeOutAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings.isAlwaysAnimate(), context);
        else if(recyclerSettings.getAnimationConstant() == Constants.ANIMATION_SCALE)
            Animations.setScaleAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings.isAlwaysAnimate(), context);

        lastLoadedViewPosition = position;
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) { ((MyViewHolder)holder).clearAnimation(); }

    @Override
    public int getItemCount() {
        return simpleItems.size();
    }

    public void setRecyclerSettings(RecyclerSettings recyclerSettings) { this.recyclerSettings = recyclerSettings; }

    public void setLastLoadedViewPosition(int lastLoadedViewPosition) { this.lastLoadedViewPosition = lastLoadedViewPosition; }

    public SimpleItem getItemAtPosition(int position) { return simpleItems.get(position); }

    public void addItemToList(SimpleItem blueHair, int position) {
        simpleItems.add(blueHair);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        simpleItems.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

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
