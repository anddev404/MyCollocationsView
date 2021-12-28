package com.anddev404.words_list_view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class SearchWordView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var text: TextView
    private var list: ListView
    private var searchButton: ImageButton
    private var searchCollocationsButton: Button
    private var searchSentencesButton: Button
    private var resetButton: ImageButton
    private var editTextSearch: EditText

    private var buttonVerb: Button
    private var buttonAdjective: Button
    private var buttonNoun: Button
    private var buttonEnd: Button


    private var originalWords: ArrayList<Word>? = null
    private var showedWords: ArrayList<Word>? = null

    fun goToIndex(index: Int) {
        try {
            list.setSelection(index)

        } catch (e: Exception) {

        }
    }

    fun quickRefreshViewList() {
        list.invalidateViews()
    }

    fun searchResult(searchWord: String): ArrayList<Word> {

        var result = arrayListOf<Word>()
        result.add(Word(-1, Word.ID_MINUS_ONE, "", ""))
        result.add(Word(0, searchWord, "", ""))

        var tmpList = originalWords

        result.add(Word(-2, Word.ID_MINUS_TWO, "=====", ""))//angielskie
        //  result.add(Word(-6, Word.ID_MINUS_SIX, "", ""))//czasowniki

        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 3 && w.word.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 1 && w.word.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 2 && w.word.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 0 && w.word.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 3 && w.word.contains(searchWord)) {
                    result.add(w)
                }
            }
        }

        // result.add(Word(-7, Word.ID_MINUS_SEVEN, "", ""))//rzeczowniki

        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 1 && w.word.contains(searchWord)) {
                    result.add(w)
                }
            }
        }

        //result.add(Word(-8, Word.ID_MINUS_EIGHT, "", ""))//pzzymiotniki

        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 2 && w.word.contains(searchWord)) {
                    result.add(w)
                }
            }
        }

        //  result.add(Word(-9, Word.ID_MINUS_NINE, "", ""))//nieznane

        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 0 && w.word.contains(searchWord)) {
                    result.add(w)
                }
            }
        }


        result.add(Word(-3, Word.ID_MINUS_THREE, "=====", ""))//polskie

        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 3 && w.translation.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 1 && w.translation.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 2 && w.translation.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 0 && w.translation.equals(searchWord)) {
                    result.add(w)
                }
            }
        }
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 3 && w.translation.contains(searchWord)) {
                    result.add(w)
                }
            }
        }

        //  result.add(Word(-7, Word.ID_MINUS_SEVEN, "", ""))//rzeczowniki

        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 1 && w.translation.contains(searchWord)) {
                    result.add(w)
                }
            }
        }

        // result.add(Word(-8, Word.ID_MINUS_EIGHT, "", ""))//pzzymiotniki

        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 2 && w.translation.contains(searchWord)) {
                    result.add(w)
                }
            }
        }

        //  result.add(Word(-9, Word.ID_MINUS_NINE, "", ""))//nieznane
        tmpList?.let {
            for (w in tmpList) {
                if (w.partOfSpeech == 0 && w.translation.contains(searchWord)) {
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

        buttonVerb =
            findViewById(R.id.button1verb)
        buttonAdjective =
            findViewById(R.id.button2adjective)
        buttonNoun =
            findViewById(R.id.button3noun)
        buttonEnd =
            findViewById(R.id.button4end)

        buttonVerb.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                goToIndex(0)
            }
        })
        buttonNoun.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                goToIndex(3868)
            }
        })
        buttonAdjective.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                goToIndex(1968)
            }
        })
        buttonEnd.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                goToIndex(8712)
            }
        })
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
        searchCollocationsButton =
            findViewById(R.id.searchCollocationButton)
        searchCollocationsButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                var searchWord = editTextSearch.text.toString()
                Log.d("MARCIN", "search collocations: $searchWord");
                if (searchWord.length > 1) {
                    mListener?.collocationsSearch(searchWord)
                } else {
                    Toast.makeText(context, "tekst jest za krótki", Toast.LENGTH_SHORT).show();
                }
            }
        })
        searchSentencesButton =
            findViewById(R.id.searchSentencesButton)
        searchSentencesButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                var searchWord = editTextSearch.text.toString()
                Log.d("MARCIN", "search sentences: $searchWord");
                if (searchWord.length > 1) {
                    mListener?.sentencesSearch(searchWord)
                } else {
                    Toast.makeText(context, "tekst jest za krótki", Toast.LENGTH_SHORT).show();
                }
            }
        })
    }

    fun setWords(words: ArrayList<Word>) {

        var nounCount = 1
        var adjCount = 1
        var verbCount = 1
        var unknownCount = 1

        for (w in words.indices) {
            if (words[w].partOfSpeech == 3) {
                words[w].forNr = verbCount
                verbCount++
            }
            if (words[w].partOfSpeech == 2) {
                words[w].forNr = adjCount
                adjCount++
            }
            if (words[w].partOfSpeech == 1) {
                words[w].forNr = nounCount
                nounCount++
            }
            if (words[w].partOfSpeech == 0) {
                words[w].forNr = unknownCount
                unknownCount++
            }
        }

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
        fun collocationsSearch(word: String)
        fun sentencesSearch(word: String)

    }

//////////////////////////////


}