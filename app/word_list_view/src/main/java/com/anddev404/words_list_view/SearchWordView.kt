package com.anddev404.words_list_view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class SearchWordView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var text: TextView
    private lateinit var list: ListView
    private var words: ArrayList<Word>? = null

    init {
        inflate(context, R.layout.search_word_view, this)
        text = findViewById(R.id.custom_view_text_view)


        var ta = context.obtainStyledAttributes(attrs, R.styleable.SearchWordView, 0, 0);
        try {
            text.text = ta.getString(R.styleable.SearchWordView_text);

        } finally {
            ta.recycle();
        }

        list = findViewById<View>(R.id.custom_view_list_view) as ListView
        list.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("MARCIN", "Click $id, $position ${words?.get(position)?.word}");
                mListener?.wordSearch("Dane wysłane: ${words?.get(position)?.word} $id, $position ");
                //TODO zmienic na tylko słowo
            }

        })
    }

    fun setWords(words: ArrayList<Word>) {
        this.words = words
        list.adapter =
            AdapterWordsListView(context, words)
    }

    //////////////////////////////
    private var mListener: OnSearchWordViewListener? = null


    fun setOnSearchWordViewListener(onListener: OnSearchWordViewListener) {
        mListener = onListener;

    }

    interface OnSearchWordViewListener {
        fun wordSearch(word: String)
    }

    //////////////////////////////


}