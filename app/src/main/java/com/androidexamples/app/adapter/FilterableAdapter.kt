package com.androidexamples.app.adapter

/**
 * Created by marcosvbras on 28/12/16.
 */

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView

import com.androidexamples.app.R
import com.androidexamples.app.interfaces.FilterObserverListener

import java.util.ArrayList

class FilterableAdapter(private val context: Activity, private val listItemsOriginal: List<String>) : BaseAdapter(), Filterable {
    private var listItems: List<String>? = null
    private var filterObserverListener: FilterObserverListener? = null

    init {
        this.listItems = listItemsOriginal
    }

    override fun getCount(): Int {
        return listItems!!.size
    }

    override fun getItem(i: Int): Any {
        return this.listItems!![i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun setFilterObserverListener(filterObserverListener: FilterObserverListener) {
        this.filterObserverListener = filterObserverListener
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        val view: View
        val myViewHolder: MyViewHolder
        val item = listItems!![position]

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_filter, viewGroup, false)
            myViewHolder = MyViewHolder(view)
            view.tag = myViewHolder
        } else {
            view = convertView
            myViewHolder = view.tag as MyViewHolder
        }

        myViewHolder.textView.text = item

        return view
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            // Método que executa a filtragem
            override fun performFiltering(query: CharSequence?): FilterResults {
                var query = query
                val filterResults = FilterResults()

                if (query == null || query.length == 0) {
                    filterResults.count = listItemsOriginal.size
                    filterResults.values = listItemsOriginal
                } else {
                    val listItemsFiltered = ArrayList<String>()
                    query = query.toString().toLowerCase()

                    for (item in listItemsOriginal) {
                        if (item.toLowerCase().contains(query)) {
                            listItemsFiltered.add(item)
                        }
                    }

                    filterResults.count = listItemsFiltered.size
                    filterResults.values = listItemsFiltered
                }

                return filterResults
            }

            // Método que exibe o resultado da busca
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                listItems = filterResults.values as ArrayList<String>
                notifyDataSetInvalidated()

                if (filterObserverListener != null)
                    filterObserverListener!!.onFinishFiltering(filterResults.count)
            }
        }
    }

    inner class MyViewHolder(view: View) {
        internal val textView: TextView

        init {
            textView = view.findViewById<View>(R.id.textView) as TextView
        }
    }
}
