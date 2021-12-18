package com.anddev404.words_list_view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.Exception

class SearchWordView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var text: TextView
    private var list: ListView
    private var searchButton: ImageButton
    private var resetButton: ImageButton
    private var editTextSearch: EditText

    private var originalWords: ArrayList<Word>? = null
    private var showedWords: ArrayList<Word>? = null

    fun goToIndex(index: Int) {
        try {
            list.setSelection(index)

        } catch (e: Exception) {

        }
    }

    fun searchResult(searchWord: String): ArrayList<Word> {

        var result = arrayListOf<Word>()
        result.add(Word(-1, Word.ID_MINUS_ONE, "", ""))
        result.add(Word(0, searchWord, "", ""))
        result.add(Word(-2, Word.ID_MINUS_TWO, "", ""))

        originalWords.let {
            for (w in originalWords!!) {
                if (w.word.contains(searchWord)) {
                    result.add(w)
                }
            }
        }
        result.add(Word(-3, Word.ID_MINUS_THREE, "", ""))

        originalWords.let {
            for (w in originalWords!!) {
                if (w.translation.contains(searchWord)) {
                    result.add(w)
                }
            }
        }
        return result
    }

    init {
        inflate(context, R.layout.search_word_view, this)

        editTextSearch = findViewById(R.id.custom_view_editText)

        searchButton =
            findViewById(R.id.custom_view_button_search)
        resetButton =
            findViewById(R.id.custom_view_button_reset)

        list = findViewById<View>(R.id.custom_view_list_view) as ListView

        searchButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                var searchWord = editTextSearch.text.toString()
                Log.d("MARCIN", "search: $searchWord");
                if (searchWord.length > 1) {
                    showedWords = searchResult(searchWord)
                    list.adapter =
                        AdapterWordsListView(context, showedWords!!)
                } else {
                    Toast.makeText(context, "tekst jest za krótki", Toast.LENGTH_SHORT).show();
                }


            }
        })

        resetButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                editTextSearch.text.clear()
                Log.d("MARCIN", "reset button");
                showedWords = originalWords
                showedWords.let {
                    list.adapter =
                        AdapterWordsListView(context, showedWords!!)
                    Log.d("MARCIN", "refresh list");

                }


            }
        })

        text = findViewById(R.id.custom_view_text_view)


        var ta = context.obtainStyledAttributes(attrs, R.styleable.SearchWordView, 0, 0);
        try {
            text.text = ta.getString(R.styleable.SearchWordView_text);

        } finally {
            ta.recycle();
        }

        list.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                if (showedWords?.get(position)?.id != null) {

                    var i = showedWords?.get(position)?.id

                    if (i!! >= 0) {
                        mListener?.wordSearch(
                            "" + showedWords?.get(position)?.word,
                            showedWords?.get(position)?.id ?: 0
                        );
                        Log.d("MARCIN", "Click $id, $position ${showedWords?.get(position)?.word}");

                    }


                }

            }

        })
    }

    fun setWords(words: ArrayList<Word>) {
        this.originalWords = words
        showedWords = originalWords
        list.adapter =
            AdapterWordsListView(context, words)
    }

    //////////////////////////////
    private var mListener: OnSearchWordViewListener? = null


    fun setOnSearchWordViewListener(onListener: OnSearchWordViewListener) {
        mListener = onListener;

    }

    interface OnSearchWordViewListener {
        fun wordSearch(word: String, id: Int)
    }

//////////////////////////////


}