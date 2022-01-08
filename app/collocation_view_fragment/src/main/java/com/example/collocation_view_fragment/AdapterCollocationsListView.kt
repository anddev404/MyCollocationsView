package com.example.collocation_view_fragment

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AdapterCollocationsListView(var context: Context, var list: ArrayList<Collocation>) :
    BaseAdapter() {

    //region variables

    var inflater: LayoutInflater
    var isHideTranslation = false
    var isHideSentences = false
    var isHideUnknownSentences = false

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
        var hide = isHideSentences

        if (list.get(position).hideLayout) {
            var constraint =
                rootView.findViewById<View>(R.id.whole_constraint_layout_row_collocation) as ConstraintLayout
            constraint.visibility = View.GONE
        }

        holder.hide_show_sentences =
            rootView.findViewById<View>(R.id.hide_show_choosed_sentences) as ImageView

        holder.numberOfCollocationTextView =
            rootView.findViewById<View>(R.id.number_collocation) as TextView
        holder.collocationTextView =
            rootView.findViewById<View>(R.id.textView_collocation) as TextView
        holder.translatedCollocationTextView =
            rootView.findViewById<View>(R.id.textView_translated_collocation) as TextView

        holder.example1TextView =
            rootView.findViewById<View>(R.id.textView_example_1) as TextView
        holder.example2TextView =
            rootView.findViewById<View>(R.id.textView_example_2) as TextView
        holder.example3TextView =
            rootView.findViewById<View>(R.id.textView_example_3) as TextView
        holder.example4TextView =
            rootView.findViewById<View>(R.id.textView_example_4) as TextView
        holder.example5TextView =
            rootView.findViewById<View>(R.id.textView_example_5) as TextView

        holder.line1 =
            rootView.findViewById<View>(R.id.line1) as View
        holder.line2 =
            rootView.findViewById<View>(R.id.line2) as View
        holder.line3 =
            rootView.findViewById<View>(R.id.line3) as View
        holder.line4 =
            rootView.findViewById<View>(R.id.line4) as View
        holder.line5 =
            rootView.findViewById<View>(R.id.line5) as View

        holder.line0a =
            rootView.findViewById<View>(R.id.line0a) as View
        holder.line1a =
            rootView.findViewById<View>(R.id.line1a) as View
        holder.line2a =
            rootView.findViewById<View>(R.id.line2a) as View
        holder.line3a =
            rootView.findViewById<View>(R.id.line3a) as View
        holder.line4a =
            rootView.findViewById<View>(R.id.line4a) as View

        holder.number1TextView =
            rootView.findViewById<View>(R.id.number_example1) as TextView
        holder.number2TextView =
            rootView.findViewById<View>(R.id.number_example2) as TextView
        holder.number3TextView =
            rootView.findViewById<View>(R.id.number_example3) as TextView
        holder.number4TextView =
            rootView.findViewById<View>(R.id.number_example4) as TextView
        holder.number5TextView =
            rootView.findViewById<View>(R.id.number_example5) as TextView

        holder.translatedExample1TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_1) as TextView
        holder.translatedExample2TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_2) as TextView
        holder.translatedExample3TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_3) as TextView
        holder.translatedExample4TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_4) as TextView
        holder.translatedExample5TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_5) as TextView

        holder.head_view =
            rootView.findViewById<View>(R.id.head_colored_view) as View
        holder.rest_view =
            rootView.findViewById<View>(R.id.rest_colored_view) as View
        holder.blank_view =
            rootView.findViewById<View>(R.id.blank_view) as View

        if (list.get(position).isChecked) {
            holder.head_view.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            holder.rest_view.setBackgroundColor(rootView.resources.getColor(R.color.collocations_rest_chosed))

        }
        if (list.get(position).id < 0) {
            holder.head_view.setBackgroundColor(rootView.resources.getColor(R.color.collocations_header))

        }

        holder.collocationTextView.text =
            list.get(position).collocation + " (${list.get(position).frequency})"
        holder.translatedCollocationTextView.text = list.get(position).translatedCollocation


        holder.numberOfCollocationTextView.text = "" + (position + 1) + "."


        holder.example1TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        0,
                        !list.get(position).exampleChecked.get(0)
                    )
                    if (list.get(position).exampleChecked.get(0)) {
                        holder.example1TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample1TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example1TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample1TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 1);
            }
        })
        holder.translatedExample1TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        0,
                        !list.get(position).exampleChecked.get(0)
                    )
                    if (list.get(position).exampleChecked.get(0)) {
                        holder.example1TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample1TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example1TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample1TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 1);
            }
        })
        holder.example2TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        1,
                        !list.get(position).exampleChecked.get(1)
                    )
                    if (list.get(position).exampleChecked.get(1)) {
                        holder.example2TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample2TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example2TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample2TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 2);
            }
        })
        holder.translatedExample2TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        1,
                        !list.get(position).exampleChecked.get(1)
                    )
                    if (list.get(position).exampleChecked.get(1)) {
                        holder.example2TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample2TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example2TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample2TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 2);
            }
        })
        holder.example3TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        2,
                        !list.get(position).exampleChecked.get(2)
                    )
                    if (list.get(position).exampleChecked.get(2)) {
                        holder.example3TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample3TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example3TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample3TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 3);
            }
        })
        holder.translatedExample3TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        2,
                        !list.get(position).exampleChecked.get(2)
                    )
                    if (list.get(position).exampleChecked.get(2)) {
                        holder.example3TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample3TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example3TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample3TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 3);
            }
        })
        holder.example4TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        3,
                        !list.get(position).exampleChecked.get(3)
                    )
                    if (list.get(position).exampleChecked.get(3)) {
                        holder.example4TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample4TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example4TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample4TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 4);
            }
        })
        holder.translatedExample4TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        3,
                        !list.get(position).exampleChecked.get(3)
                    )
                    if (list.get(position).exampleChecked.get(3)) {
                        holder.example4TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample4TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example4TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample4TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 4);
            }
        })
        holder.example5TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        4,
                        !list.get(position).exampleChecked.get(4)
                    )
                    if (list.get(position).exampleChecked.get(4)) {
                        holder.example5TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample5TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example5TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample5TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 5);
            }
        })
        holder.translatedExample5TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                try {
                    list.get(position).exampleChecked.set(
                        4,
                        !list.get(position).exampleChecked.get(4)
                    )
                    if (list.get(position).exampleChecked.get(4)) {
                        holder.example5TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                        holder.translatedExample5TextView.setBackgroundColor(
                            rootView.resources.getColor(
                                R.color.collocations_head_chosed
                            )
                        )

                    } else {
                        holder.example5TextView.setBackgroundColor(Color.TRANSPARENT)
                        holder.translatedExample5TextView.setBackgroundColor(Color.TRANSPARENT)

                    }
                } catch (e: java.lang.Exception) {

                }
                mListener?.sentenceClick(list.get(position), 5);
            }
        })
        holder.hide_show_sentences.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("MARCIN", "CLICK rozwiniecie");

                hide = !hide
                if (hide) {
                    holder.example1TextView.visibility = View.GONE
                    holder.example2TextView.visibility = View.GONE
                    holder.example3TextView.visibility = View.GONE
                    holder.example4TextView.visibility = View.GONE
                    holder.example5TextView.visibility = View.GONE

                    holder.number1TextView.visibility = View.GONE
                    holder.number2TextView.visibility = View.GONE
                    holder.number3TextView.visibility = View.GONE
                    holder.number4TextView.visibility = View.GONE
                    holder.number5TextView.visibility = View.GONE

                    holder.line1.visibility = View.GONE
                    holder.line2.visibility = View.GONE
                    holder.line3.visibility = View.GONE
                    holder.line4.visibility = View.GONE
                    holder.line5.visibility = View.GONE

                    holder.line0a.visibility = View.GONE
                    holder.line1a.visibility = View.GONE
                    holder.line2a.visibility = View.GONE
                    holder.line3a.visibility = View.GONE
                    holder.line4a.visibility = View.GONE


                    holder.translatedExample1TextView.visibility = View.GONE
                    holder.translatedExample2TextView.visibility = View.GONE
                    holder.translatedExample3TextView.visibility = View.GONE
                    holder.translatedExample4TextView.visibility = View.GONE
                    holder.translatedExample5TextView.visibility = View.GONE

                    holder.blank_view.visibility = View.GONE
                } else {
                    //TO DO tu uwzglednic czy pokazuje tylko zielone czy wszystkie bo teraz mam -
                    // pokazywanie wszystkich po rozwinieciu listy i to nawet fajni jest ale powinno byc tu jescze sprawdzenie czy jest pokazywanie/ukrywanie zielonych
                    //tzn jesli wlaczone pokazywanie tylko zielonych to tylko zielone a jak wszystkich to ten kod podspodem
                    holder.example1TextView.visibility = View.VISIBLE
                    holder.example2TextView.visibility = View.VISIBLE
                    holder.example3TextView.visibility = View.VISIBLE
                    holder.example4TextView.visibility = View.VISIBLE
                    holder.example5TextView.visibility = View.VISIBLE

                    holder.number1TextView.visibility = View.VISIBLE
                    holder.number2TextView.visibility = View.VISIBLE
                    holder.number3TextView.visibility = View.VISIBLE
                    holder.number4TextView.visibility = View.VISIBLE
                    holder.number5TextView.visibility = View.VISIBLE

                    holder.line1.visibility = View.VISIBLE
                    holder.line2.visibility = View.VISIBLE
                    holder.line3.visibility = View.VISIBLE
                    holder.line4.visibility = View.VISIBLE
                    holder.line5.visibility = View.VISIBLE

                    holder.line0a.visibility = View.VISIBLE
                    holder.line1a.visibility = View.VISIBLE
                    holder.line2a.visibility = View.VISIBLE
                    holder.line3a.visibility = View.VISIBLE
                    holder.line4a.visibility = View.VISIBLE

                    holder.translatedExample1TextView.visibility = View.VISIBLE
                    holder.translatedExample2TextView.visibility = View.VISIBLE
                    holder.translatedExample3TextView.visibility = View.VISIBLE
                    holder.translatedExample4TextView.visibility = View.VISIBLE
                    holder.translatedExample5TextView.visibility = View.VISIBLE

                    holder.blank_view.visibility = View.VISIBLE
                }


            }

        })

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
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.example4TextView.text =
                    Html.fromHtml(
                        list.get(position).examples.get(3),
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.example4TextView.text = list.get(position).examples.get(3)
            }
        } catch (e: Exception) {
            holder.example4TextView.text = ""

        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.example5TextView.text =
                    Html.fromHtml(
                        list.get(position).examples.get(4),
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.example5TextView.text = list.get(position).examples.get(4)
            }
        } catch (e: Exception) {
            holder.example5TextView.text = ""

        }
////////////////////////////// translations


        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.translatedExample1TextView.text =
                    Html.fromHtml(
                        list.get(position).translations.get(0),
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.translatedExample1TextView.text = list.get(position).translations.get(0)
            }

        } catch (e: Exception) {
            holder.translatedExample1TextView.text = ""

        }
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.translatedExample2TextView.text =
                    Html.fromHtml(
                        list.get(position).translations.get(1),
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.translatedExample2TextView.text = list.get(position).translations.get(1)
            }

        } catch (e: Exception) {
            holder.translatedExample2TextView.text = ""

        }
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.translatedExample3TextView.text =
                    Html.fromHtml(
                        list.get(position).translations.get(2),
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.translatedExample3TextView.text = list.get(position).translations.get(2)
            }

        } catch (e: Exception) {
            holder.translatedExample3TextView.text = ""

        }

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.translatedExample4TextView.text =
                    Html.fromHtml(
                        list.get(position).translations.get(3),
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.translatedExample4TextView.text = list.get(position).translations.get(3)
            }

        } catch (e: Exception) {
            holder.translatedExample4TextView.text = ""

        }
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.translatedExample5TextView.text =
                    Html.fromHtml(
                        list.get(position).translations.get(4),
                        Html.FROM_HTML_MODE_COMPACT
                    )
            } else {
                holder.translatedExample5TextView.text = list.get(position).translations.get(4)
            }

        } catch (e: Exception) {
            holder.translatedExample5TextView.text = ""

        }


        if (hide) {
            holder.example1TextView.visibility = View.GONE
            holder.example2TextView.visibility = View.GONE
            holder.example3TextView.visibility = View.GONE
            holder.example4TextView.visibility = View.GONE
            holder.example5TextView.visibility = View.GONE

            holder.number1TextView.visibility = View.GONE
            holder.number2TextView.visibility = View.GONE
            holder.number3TextView.visibility = View.GONE
            holder.number4TextView.visibility = View.GONE
            holder.number5TextView.visibility = View.GONE

            holder.line1.visibility = View.GONE
            holder.line2.visibility = View.GONE
            holder.line3.visibility = View.GONE
            holder.line4.visibility = View.GONE
            holder.line5.visibility = View.GONE

            holder.line0a.visibility = View.GONE
            holder.line1a.visibility = View.GONE
            holder.line2a.visibility = View.GONE
            holder.line3a.visibility = View.GONE
            holder.line4a.visibility = View.GONE

            holder.translatedExample1TextView.visibility = View.GONE
            holder.translatedExample2TextView.visibility = View.GONE
            holder.translatedExample3TextView.visibility = View.GONE
            holder.translatedExample4TextView.visibility = View.GONE
            holder.translatedExample5TextView.visibility = View.GONE

            holder.blank_view.visibility = View.GONE

        } else if (isHideTranslation) {
            holder.translatedExample1TextView.visibility = View.GONE
            holder.translatedExample2TextView.visibility = View.GONE
            holder.translatedExample3TextView.visibility = View.GONE
            holder.translatedExample4TextView.visibility = View.GONE
            holder.translatedExample5TextView.visibility = View.GONE
        }

        try {
            if (list.get(position).exampleChecked.get(0)) {
                holder.example1TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                holder.translatedExample1TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))

            }
        } catch (e: java.lang.Exception) {

        }
        try {
            if (list.get(position).exampleChecked.get(1)) {
                holder.example2TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                holder.translatedExample2TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            }
        } catch (e: java.lang.Exception) {

        }
        try {
            if (list.get(position).exampleChecked.get(2)) {
                holder.example3TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                holder.translatedExample3TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            }
        } catch (e: java.lang.Exception) {

        }
        try {
            if (list.get(position).exampleChecked.get(3)) {
                holder.example4TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                holder.translatedExample4TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            }
        } catch (e: java.lang.Exception) {

        }
        try {
            if (list.get(position).exampleChecked.get(4)) {
                holder.example5TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
                holder.translatedExample5TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            }
        } catch (e: java.lang.Exception) {

        }
        Log.d("SENTENCE", "is hide sentence: $isHideUnknownSentences");

        if (isHideUnknownSentences) {
            try {

                if (!list.get(position).exampleChecked.get(0)) {
                    Log.d("SENTENCE", "is 1: ${list.get(position).exampleChecked.get(0)}");

                    holder.example1TextView.visibility = View.GONE
                    holder.translatedExample1TextView.visibility = View.GONE
                    holder.number1TextView.visibility = View.GONE

                }
            } catch (e: java.lang.Exception) {
            }
            try {

                if (!list.get(position).exampleChecked.get(1)) {
                    Log.d("SENTENCE", "is 2: ${list.get(position).exampleChecked.get(1)}");
                    holder.example2TextView.visibility = View.GONE
                    holder.translatedExample2TextView.visibility = View.GONE
                    holder.number2TextView.visibility = View.GONE

                }
            } catch (e: java.lang.Exception) {
            }
            try {

                if (!list.get(position).exampleChecked.get(2)) {
                    Log.d("SENTENCE", "is 3: ${list.get(position).exampleChecked.get(2)}");

                    holder.example3TextView.visibility = View.GONE
                    holder.translatedExample3TextView.visibility = View.GONE
                    holder.number3TextView.visibility = View.GONE

                }
            } catch (e: java.lang.Exception) {
            }
            try {
                if (!list.get(position).exampleChecked.get(3)) {
                    holder.example4TextView.visibility = View.GONE
                    holder.translatedExample4TextView.visibility = View.GONE
                    holder.number4TextView.visibility = View.GONE

                }
            } catch (e: java.lang.Exception) {
            }
            try {
                if (!list.get(position).exampleChecked.get(4)) {
                    holder.example5TextView.visibility = View.GONE
                    holder.translatedExample5TextView.visibility = View.GONE
                    holder.number5TextView.visibility = View.GONE

                }
            } catch (e: java.lang.Exception) {
            }
        }

        //dodatkowe ukrywanie zdan ktorych nie ma
        try {
            list.get(position).examples.get(0).length
            Log.d("LENGTH", "length 1: ${list.get(position).examples.get(0).length}");

        } catch (e: java.lang.Exception) {
            Log.d("LENGTH", "length 1: error");

            holder.example1TextView.visibility = View.GONE
            holder.translatedExample1TextView.visibility = View.GONE
            holder.number1TextView.visibility = View.GONE
        }

        try {
            list.get(position).examples.get(1).length
            Log.d("LENGTH", "length 2: ${list.get(position).examples.get(1).length}");

        } catch (e: java.lang.Exception) {
            Log.d("LENGTH", "length 2: error");

            holder.example2TextView.visibility = View.GONE
            holder.translatedExample2TextView.visibility = View.GONE
            holder.number2TextView.visibility = View.GONE
        }

        try {
            list.get(position).examples.get(2).length
            Log.d("LENGTH", "length 3: ${list.get(position).examples.get(2).length}");

        } catch (e: java.lang.Exception) {
            Log.d("LENGTH", "length 3: error");

            holder.example3TextView.visibility = View.GONE
            holder.translatedExample3TextView.visibility = View.GONE
            holder.number3TextView.visibility = View.GONE
        }

        try {
            list.get(position).examples.get(3).length
            Log.d("LENGTH", "length 4: ${list.get(position).examples.get(3).length}");


        } catch (e: java.lang.Exception) {
            Log.d("LENGTH", "length 4:  error");

            holder.example4TextView.visibility = View.GONE
            holder.translatedExample4TextView.visibility = View.GONE
            holder.number4TextView.visibility = View.GONE
        }

        try {
            list.get(position).examples.get(4).length
            Log.d("LENGTH", "length 5: ${list.get(position).examples.get(4).length}");


        } catch (e: java.lang.Exception) {
            Log.d("LENGTH", "length 5 error:");

            holder.example5TextView.visibility = View.GONE
            holder.translatedExample5TextView.visibility = View.GONE
            holder.number5TextView.visibility = View.GONE
        }
        return rootView
    }

    class Holder() {
        lateinit var collocationTextView: TextView
        lateinit var translatedCollocationTextView: TextView

        lateinit var numberOfCollocationTextView: TextView

        lateinit var example1TextView: TextView
        lateinit var example2TextView: TextView
        lateinit var example3TextView: TextView
        lateinit var example4TextView: TextView
        lateinit var example5TextView: TextView

        lateinit var line1: View
        lateinit var line2: View
        lateinit var line3: View
        lateinit var line4: View
        lateinit var line5: View

        lateinit var line0a: View
        lateinit var line1a: View
        lateinit var line2a: View
        lateinit var line3a: View
        lateinit var line4a: View

        lateinit var number1TextView: TextView
        lateinit var number2TextView: TextView
        lateinit var number3TextView: TextView
        lateinit var number4TextView: TextView
        lateinit var number5TextView: TextView

        lateinit var translatedExample1TextView: TextView
        lateinit var translatedExample2TextView: TextView
        lateinit var translatedExample3TextView: TextView
        lateinit var translatedExample4TextView: TextView
        lateinit var translatedExample5TextView: TextView

        lateinit var head_view: View
        lateinit var rest_view: View
        lateinit var blank_view: View

        lateinit var hide_show_sentences: ImageView

        init {
            Log.d("MY_DEBUG", "holder");
        }
    }

//endregion
// region input >

    fun hideSentences() {
        isHideSentences = true
    }

    fun hideUnknownSentences() {
        isHideUnknownSentences = true
    }

    fun showUnknownSentences() {
        isHideUnknownSentences = false
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

    //endregion
//region methods
//////////////////////////////
    private var mListener: OnSentenceClickListener? = null


    fun setOnSentenceClickListener(onSentenceClickListener: OnSentenceClickListener) {
        mListener = onSentenceClickListener;

    }

    interface OnSentenceClickListener {
        fun sentenceClick(collocation: Collocation, nr: Int)
    }

//////////////////////////////
//wysyłanie


}