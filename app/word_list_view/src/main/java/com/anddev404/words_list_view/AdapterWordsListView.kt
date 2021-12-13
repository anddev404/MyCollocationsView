package com.anddev404.words_list_view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterWordsListView(var context: Context, var list: ArrayList<Word>) :
    BaseAdapter() {

    //region variables

    var inflater: LayoutInflater

    //endregion

    init {
        inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        Log.d("MY_DEBUG_ADAPTER", "adapter");
        Log.d("ADAPTER", "init");
    }

    //region adapter

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.word_list_view_row, null)
        Log.d("ADAPTER", "getView");
        Log.d("MY_DEBUG", "list - view");
        var holder = Holder()

        holder.word =
            rootView.findViewById<View>(R.id.word_list_view_row) as TextView

        holder.word.text = list.get(position).word

        return rootView
    }

    class Holder() {
        lateinit var word: TextView

        init {
            Log.d("MY_DEBUG", "holder");
        }
    }

    //endregion


}