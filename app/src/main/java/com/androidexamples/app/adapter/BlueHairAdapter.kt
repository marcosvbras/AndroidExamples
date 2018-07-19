package com.androidexamples.app.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.androidexamples.app.R
import com.androidexamples.app.domain.SimpleItem
import com.androidexamples.app.utils.Animations
import com.androidexamples.app.utils.Constants
import com.androidexamples.app.utils.RecyclerSettings

/**
 * Created by marcos on 25/12/2016.
 */

class BlueHairAdapter(private val simpleItems: MutableList<SimpleItem>, private val context: Context, private var recyclerSettings: RecyclerSettings?) : RecyclerView.Adapter<BlueHairAdapter.MyViewHolder>() {
    private var lastLoadedViewPosition: Int = 0

    init {
        this.lastLoadedViewPosition = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_blue_hair, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val simpleItem = simpleItems[position]
        val myViewHolder = holder as MyViewHolder
        myViewHolder.textView.text = "Item " + simpleItem.name!!
        myViewHolder.imageView.setImageResource(simpleItem.imageResource!!)

        if (recyclerSettings!!.animationConstant == Constants.ANIMATION_SLIDE_IN_LEFT)
            Animations.setSlideInLeftAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings!!.isAlwaysAnimate, context)
        else if (recyclerSettings!!.animationConstant == Constants.ANIMATION_SLIDE_OUT_RIGHT)
            Animations.setSlideOutRightAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings!!.isAlwaysAnimate, context)
        else if (recyclerSettings!!.animationConstant == Constants.ANIMATION_FADE_IN)
            Animations.setFadeInAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings!!.isAlwaysAnimate, context)
        else if (recyclerSettings!!.animationConstant == Constants.ANIMATION_FADE_OUT)
            Animations.setFadeOutAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings!!.isAlwaysAnimate, context)
        else if (recyclerSettings!!.animationConstant == Constants.ANIMATION_SCALE)
            Animations.setScaleAnimation(myViewHolder.container, position, lastLoadedViewPosition, recyclerSettings!!.isAlwaysAnimate, context)

        lastLoadedViewPosition = position
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        holder.clearAnimation()
    }

    override fun getItemCount(): Int {
        return simpleItems.size
    }

    fun setRecyclerSettings(recyclerSettings: RecyclerSettings) {
        this.recyclerSettings = recyclerSettings
    }

    fun setLastLoadedViewPosition(lastLoadedViewPosition: Int) {
        this.lastLoadedViewPosition = lastLoadedViewPosition
    }

    fun getItemAtPosition(position: Int): SimpleItem {
        return simpleItems[position]
    }

    fun addItemToList(blueHair: SimpleItem, position: Int) {
        simpleItems.add(blueHair)
        notifyItemInserted(position)
    }

    fun removeItem(position: Int) {
        simpleItems.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView: TextView
        internal var imageView: ImageView
        internal var container: LinearLayout

        init {
            textView = itemView.findViewById<View>(R.id.textView) as TextView
            imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            container = itemView.findViewById<View>(R.id.container) as LinearLayout
        }

        fun clearAnimation() {
            container.clearAnimation()
        }
    }
}