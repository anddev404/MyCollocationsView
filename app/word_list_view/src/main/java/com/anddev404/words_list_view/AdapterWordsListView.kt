package com.anddev404.words_list_view

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
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

        holder.nr =
            rootView.findViewById<View>(R.id.nr_list_view_row) as TextView
        holder.word =
            rootView.findViewById<View>(R.id.word_list_view_row) as TextView
        holder.translation =
            rootView.findViewById<View>(R.id.translation_list_view_row) as TextView
        holder.prouncination =
            rootView.findViewById<View>(R.id.prouncination_list_view_row) as TextView
        holder.layout =
            rootView.findViewById<View>(R.id.layout_list_view) as LinearLayout

        if (list.get(position).id > 0) holder.nr.text = "" + (position + 1)
        if (list.get(position).id < 0) holder.layout.setBackgroundColor(context.resources.getColor(R.color.dark))

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.word.text =
                    Html.fromHtml(
                        list.get(position).word,
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.word.text = list.get(position).word
            }


        } catch (e: Exception) {
            holder.word.text = list.get(position).word

        }
        holder.translation.text = list.get(position).translation
        holder.prouncination.text = list.get(position).prouncination

        return rootView
    }

    class Holder() {
        lateinit var nr: TextView
        lateinit var word: TextView
        lateinit var translation: TextView
        lateinit var prouncination: TextView
        lateinit var layout: LinearLayout

        init {
            Log.d("MY_DEBUG", "holder");
        }
    }

    //endregion


}