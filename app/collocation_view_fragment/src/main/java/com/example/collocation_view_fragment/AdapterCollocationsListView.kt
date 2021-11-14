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
import androidx.core.view.isVisible
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AdapterCollocationsListView(var context: Context, var list: ArrayList<Collocation>) :
    BaseAdapter() {

    //region variables

    var inflater: LayoutInflater
    var isHideTranslation = false
    var isHideSentences = false

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

        holder.number1TextView =
            rootView.findViewById<View>(R.id.number_example1) as TextView
        holder.number2TextView =
            rootView.findViewById<View>(R.id.number_example2) as TextView
        holder.number3TextView =
            rootView.findViewById<View>(R.id.number_example3) as TextView

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

////////////


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

////////////////////////////// translations


        try {
            holder.translatedExample1TextView.text = list.get(position).translations.get(0)

        } catch (e: Exception) {
            holder.translatedExample1TextView.text = ""
        }

        try {
            holder.translatedExample2TextView.text = list.get(position).translations.get(1)

        } catch (e: Exception) {
            holder.translatedExample2TextView.text = ""
        }

        try {
            holder.translatedExample3TextView.text = list.get(position).translations.get(2)

        } catch (e: Exception) {
            holder.translatedExample3TextView.text = ""

        }
        if (isHideSentences) {
            holder.example1TextView.visibility = View.GONE
            holder.example2TextView.visibility = View.GONE
            holder.example3TextView.visibility = View.GONE

            holder.number1TextView.visibility = View.GONE
            holder.number2TextView.visibility = View.GONE
            holder.number3TextView.visibility = View.GONE

            holder.translatedExample1TextView.visibility = View.GONE
            holder.translatedExample2TextView.visibility = View.GONE
            holder.translatedExample3TextView.visibility = View.GONE

        } else if (isHideTranslation) {
            holder.translatedExample1TextView.visibility = View.GONE
            holder.translatedExample2TextView.visibility = View.GONE
            holder.translatedExample3TextView.visibility = View.GONE
        }


        return rootView
    }

    class Holder() {
        lateinit var collocationTextView: TextView
        lateinit var numberOfCollocationTextView: TextView
        lateinit var example1TextView: TextView
        lateinit var example2TextView: TextView
        lateinit var example3TextView: TextView

        lateinit var number1TextView: TextView
        lateinit var number2TextView: TextView
        lateinit var number3TextView: TextView

        lateinit var translatedExample1TextView: TextView
        lateinit var translatedExample2TextView: TextView
        lateinit var translatedExample3TextView: TextView

        lateinit var head_view: View
        lateinit var rest_view: View

        init {
            Log.d("MY_DEBUG", "holder");
        }
    }

//endregion

//region methods

    fun hideSentences() {
        isHideSentences = true
    }

    fun showSentences() {
        isHideSentences = false

    }

    fun hideTranslations() {
        isHideTranslation = true

    }

    fun showTranslations() {
        isHideTranslation = false

    }

    fun updateList(collocation: Collocation) {

        // list.get(0).collocation = "CHANGE !~!!!"
        //   list.get(0).translations.set(1, "zamiana działą")
        // list.get(0).translations = arrayListOf("jeden","dwa", "trzy")//to tez działa
        //  list.set(0, collocation)//nie działą

        var test = 0
    }
}