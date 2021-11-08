package com.example.collocation_view_fragment

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterCollocationsListView(var context: Context, var list: List<Collocation>) :
    BaseAdapter() {

    var inflater: LayoutInflater

    init {
        inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class Holder() {
        lateinit var collocationTextView: TextView
        lateinit var numberOfCollocationTextView: TextView
        lateinit var example1TextView: TextView
        lateinit var example2TextView: TextView
        lateinit var example3TextView: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.row_collocation, null)
        var holder = Holder()

        holder.numberOfCollocationTextView =
            rootView.findViewById<View>(R.id.number_collocation) as TextView
        holder.collocationTextView =
            rootView.findViewById<View>(R.id.textView_collocation) as TextView
        holder.example1TextView =
            rootView.findViewById<View>(R.id.textView_example_1) as TextView
        holder.example2TextView =
            rootView.findViewById<View>(R.id.textView_example_2) as TextView
        holder.example3TextView =
            rootView.findViewById<View>(R.id.textView_example_3) as TextView

        holder.collocationTextView.text = list.get(position).collocation


        holder.numberOfCollocationTextView.text = "" + (position + 1)+"."
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.example1TextView.text =
                    Html.fromHtml(list.get(position).examples.get(0), Html.FROM_HTML_MODE_COMPACT)
            } else {
                holder.example1TextView.text = list.get(position).examples.get(0)
            }


        } catch (e: Exception) {
            holder.example1TextView.text = ""

        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.example2TextView.text =
                    Html.fromHtml(list.get(position).examples.get(1), Html.FROM_HTML_MODE_COMPACT)
            } else {
                holder.example2TextView.text = list.get(position).examples.get(1)
            }
        } catch (e: Exception) {
            holder.example2TextView.text = ""

        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.example3TextView.text =
                    Html.fromHtml(list.get(position).examples.get(2), Html.FROM_HTML_MODE_COMPACT)
            } else {
                holder.example3TextView.text = list.get(position).examples.get(2)
            }
        } catch (e: Exception) {
            holder.example3TextView.text = ""

        }


        return rootView
    }
}