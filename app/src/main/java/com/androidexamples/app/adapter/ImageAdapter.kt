package com.androidexamples.app.adapter

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.androidexamples.app.R
import com.androidexamples.app.domain.SimpleItem

/**
 * Created by marcos on 14/12/2016.
 */

class ImageAdapter(private val context: Activity, private val listSimpleItems: List<SimpleItem>?) : PagerAdapter() {

    override fun getCount(): Int {
        return listSimpleItems?.size ?: 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val simpleItemItem = listSimpleItems!![position]
        val view = context.layoutInflater.inflate(R.layout.grid_item, container, false)
        (view.findViewById<View>(R.id.imageView) as ImageView).setImageResource(simpleItemItem.imageResource!!)
        container.addView(view)

        return view
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listSimpleItems!![position].name
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) { // Remove a view do container
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        // Determina se a view informada é igual ao Object retornado pelo instantiateItem
        return view === `object`
    }
}
