package com.anddev404.words_list_view

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
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

    }

    fun setWords(words: ArrayList<Word>) {
        list.adapter =
            AdapterWordsListView(context, words)
    }

}