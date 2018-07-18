package com.androidexamples.app.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.androidexamples.app.R
import com.androidexamples.app.domain.SimpleItem

/**
 * Created by marcos on 14/12/2016.
 */

class SimpleItemAdapter(private val context: Activity, private val listItems: List<SimpleItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return listItems.size
    }

    override fun getItem(position: Int): Any {
        return listItems[position]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        val view: View
        val item = listItems[position]
        val myViewHolder: MyViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.simple_item, viewGroup, false)
            myViewHolder = MyViewHolder(view)
            view.tag = myViewHolder
        } else {
            view = convertView
            myViewHolder = view.tag as MyViewHolder
        }

        myViewHolder.imageView.setImageResource(item.imageResource!!)
        myViewHolder.textView.text = item.name

        return view
    }

    inner class MyViewHolder(view: View) {
        internal val textView: TextView
        internal val imageView: ImageView

        init {
            this.textView = view.findViewById<View>(R.id.textView) as TextView
            this.imageView = view.findViewById<View>(R.id.imageView) as ImageView
        }
    }
}
