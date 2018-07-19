package com.androidexamples.app.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.androidexamples.app.R
import com.androidexamples.app.domain.ContextExample

/**
 * Created by marcosvbras on 22/01/17.
 */

class ContextAdapter(private val listItems: MutableList<ContextExample>?, private val context: Context) : RecyclerView.Adapter<ContextAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_context, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val item = listItems?.get(position)
        myViewHolder.textView.text = item?.name

        if (item?.isSelected!!)
            myViewHolder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
        else
            myViewHolder.container.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
    }

    override fun getItemCount(): Int {
        return listItems?.size?: 0
    }

    fun getItemAtPosition(position: Int): ContextExample? {
        return listItems?.get(position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textView: TextView
        internal var container: LinearLayout

        init {
            textView = itemView.findViewById<View>(R.id.textView) as TextView
            container = itemView.findViewById<View>(R.id.container) as LinearLayout
        }
    }
}
