package com.example.collocation_view_fragment

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AdapterCollocationsListView(var context: Context, var list: List<Collocation>) :
    BaseAdapter() {

    var inflater: LayoutInflater
    var isHideTranslation = false

    init {
        inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        Log.d("MY_DEBUG_ADAPTER", "adapter");
        Log.d("ADAPTER", "init");
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

        lateinit var translatedExample1TextView: TextView
        lateinit var translatedExample2TextView: TextView
        lateinit var translatedExample3TextView: TextView

        lateinit var head_view: View
        lateinit var rest_view: View

        init {
            Log.d("MY_DEBUG", "holder");
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView = inflater.inflate(R.layout.row_collocation, null)
        Log.d("ADAPTER", "getView");
        Log.d("MY_DEBUG", "list - view");
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

        holder.translatedExample1TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_1) as TextView
        holder.translatedExample2TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_2) as TextView
        holder.translatedExample3TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_3) as TextView


        holder.head_view =
            rootView.findViewById<View>(R.id.head_colored_view) as View
        holder.rest_view =
            rootView.findViewById<View>(R.id.rest_colored_view) as View

        if (list.get(position).isChecked) {
            holder.head_view.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            holder.rest_view.setBackgroundColor(rootView.resources.getColor(R.color.collocations_rest_chosed))

        }


        holder.collocationTextView.text = list.get(position).collocation


        holder.numberOfCollocationTextView.text = "" + (position + 1) + "."
        if (isHideTranslation) {

        } else {


            try {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.example1TextView.text =
                        Html.fromHtml(
                            list.get(position).examples.get(0),
                            Html.FROM_HTML_MODE_COMPACT
                        )
                } else {
                    holder.example1TextView.text = list.get(position).examples.get(0)
                }


            } catch (e: Exception) {
                holder.example1TextView.text = ""

            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.example2TextView.text =
                        Html.fromHtml(
                            list.get(position).examples.get(1),
                            Html.FROM_HTML_MODE_COMPACT
                        )
                } else {
                    holder.example2TextView.text = list.get(position).examples.get(1)
                }
            } catch (e: Exception) {
                holder.example2TextView.text = ""

            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.example3TextView.text =
                        Html.fromHtml(
                            list.get(position).examples.get(2),
                            Html.FROM_HTML_MODE_COMPACT
                        )
                } else {
                    holder.example3TextView.text = list.get(position).examples.get(2)
                }
            } catch (e: Exception) {
                holder.example3TextView.text = ""

            }
        }

        return rootView
    }

    fun hideTranslations() {
        isHideTranslation = true
//        Log.d("MARCIN", "hideeeee");
//        if (this::holder.isInitialized) {
//            if (holder != null) {
//                Log.d("MARCIN", "hideaaa");
//                try {
//                    holder.translatedExample1TextView.visibility = View.INVISIBLE
//                    holder.translatedExample2TextView.visibility = View.INVISIBLE
//                    holder.translatedExample3TextView.visibility = View.INVISIBLE
//                } catch (e: java.lang.Exception) {
//                    Log.d("MYERROR", "error 1");
//
//                }
//            }
//        }
    }

    open fun showTranslations() {
//        Log.d("MARCIN", "hideeeeebbb");
//        try {
//            if (this::holder.isInitialized) {
//                if (holder != null) {
//                    Log.d("MARCIN", "hidebb");
//
//                    holder.translatedExample1TextView.visibility = View.VISIBLE
//                    holder.translatedExample2TextView.visibility = View.VISIBLE
//                    holder.translatedExample3TextView.visibility = View.VISIBLE
//
//                }
//            }
//        } catch (e: java.lang.Exception) {
//            Log.d("MYERROR", "error 2");
//
//        }
//

    }
}