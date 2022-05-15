package com.anddev404.words_list_view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.anddev404.words_list_view.AdapterWordsListView.OnFavouriteListener


class SearchWordView(context: Context, attrs: AttributeSet) :
    OnFavouriteListener, ConstraintLayout(context, attrs) {

    private var text: TextView
    private var list: ListView
    private var searchButton: ImageButton
    private var favouriteButton: ImageButton
    private var timeButton: ImageButton

    private var searchCollocationsButton: Button
    private var filterSentencesButton: ImageButton
    private var studyButton: ImageButton
    private var searchSentencesButton: Button
    private var resetButton: ImageButton
    var editTextSearch: EditText

    private var buttonVerb: Button
    private var buttonAdjective: Button
    private var buttonNoun: Button
    private var buttonEnd: Button
    private var buttonhideLeft: Button
    private var buttonhideRight: Button

    private var viewHideLeft: View
    private var viewGideRight: View

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

    fun getActualWordList(): ArrayList<Word> {
        return (list.adapter as AdapterWordsListView).list
    }

    fun searchResult(searchWord: String): ArrayList<Word> {

        var result = arrayListOf<Word>()
        result.add(Word(-1, Word.ID_MINUS_ONE, "", ""))
        result.add(Word(0, searchWord, "", ""))

        var tmpList = originalWords

        result.add(Word(-2, Word.ID_MINUS_TWO, "=====", ""))//angielskie
        //  result.add(Word(-6, Word.ID_MINUS_SIX, "", ""))//czasowniki

        originalWords?.let {
            for (w in originalWords as ArrayList<Word>) {
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
        result.add(Word(-5, Word.ID_MINUS_FIVE, "", ""))

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
        favouriteButton =
            findViewById(R.id.custom_view_button_favourite)
        timeButton =
            findViewById(R.id.custom_view_button_time)
        filterSentencesButton =
            findViewById(R.id.custom_view_filter_search)
        studyButton =
            findViewById(R.id.study_button)
        resetButton =
            findViewById(R.id.custom_view_button_reset)

        list = findViewById<View>(R.id.custom_view_list_view) as ListView

//        list.setOnScrollListener(object : AbsListView.OnScrollListener {
//            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
//
//            }
//
//            override fun onScroll(
//                view: AbsListView?,
//                firstVisibleItem: Int,
//                visibleItemCount: Int,
//                totalItemCount: Int
//            ) {
//                Log.d("SCROOOL", "$firstVisibleItem $visibleItemCount $totalItemCount");
//            }
//
//        })
        buttonVerb =
            findViewById(R.id.button1verb)
        buttonAdjective =
            findViewById(R.id.button2adjective)
        buttonNoun =
            findViewById(R.id.button3noun)
        buttonEnd =
            findViewById(R.id.button4end)
        buttonhideLeft =
            findViewById(R.id.button1hideleft)
        buttonhideRight =
            findViewById(R.id.button3hideright)

        viewGideRight =
            findViewById(R.id.righthider)
        viewHideLeft =
            findViewById(R.id.lefthider)
        buttonhideLeft.setOnClickListener({
            if (viewHideLeft.visibility == View.INVISIBLE) viewHideLeft.visibility =
                View.VISIBLE else viewHideLeft.visibility = View.INVISIBLE
        })
        buttonhideRight.setOnClickListener({
            if (viewGideRight.visibility == View.INVISIBLE) viewGideRight.visibility =
                View.VISIBLE else viewGideRight.visibility = View.INVISIBLE
        })
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
                //  editTextSearch.setInputType(InputType.TYPE_NULL);

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
        favouriteButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                editTextSearch.text.clear()
                Log.d("MARCIN", "reset button");
                showedWords = arrayListOf()
                var tmppp = arrayListOf<Word>()
                var licznikVerb = 0
                var licznikNoun = 0
                var licznikAdjective = 0
                for (w in originalWords ?: arrayListOf()) {
                    if (w.favourite) {
                        tmppp.add(w)
                        if (w.partOfSpeech == 3) {
                            licznikVerb++
                        }
                        if (w.partOfSpeech == 2) {
                            licznikAdjective++
                        }
                        if (w.partOfSpeech == 1) {
                            licznikNoun++
                        }
                    }
                }
                showedWords = tmppp
                showedWords.let {
                    list.adapter =
                        AdapterWordsListView(context, showedWords!!)
                    Log.d("MARCIN", "refresh list");
                    Toast.makeText(
                        context,
                        "VERB = ${licznikVerb}, \n ADJ = $licznikAdjective, \n NOUN =$licznikNoun",
                        Toast.LENGTH_LONG
                    ).show();
                }


            }
        })
        timeButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                editTextSearch.text.clear()
                Log.d("MARCIN", "reset button");
                showedWords = arrayListOf()
                var tmppp = arrayListOf<Word>()
                var licznikVerb = 0
                var licznikNoun = 0
                var licznikAdjective = 0
                for (w in originalWords ?: arrayListOf()) {
                    if (w.time != null) {
                        tmppp.add(w)
                        if (w.partOfSpeech == 3) {
                            licznikVerb++
                        }
                        if (w.partOfSpeech == 2) {
                            licznikAdjective++
                        }
                        if (w.partOfSpeech == 1) {
                            licznikNoun++
                        }
                    }
                }
                //sortowanie
                var zakres1 = 500
                var zakres2 = 1000
                var zakres3 = 2000
                var zakres4 = 3000
                var zakres5 = 4000
                var zakres6 = 5000
                var zakres7 = 7000
                var zakres8 = 10000
                var zakres9 = 13000
                var zakres10 = 16000
                var zakres11 = 20000
                var zakres12 = 40000


                var newList = arrayListOf<Word>()
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres12) {
                            newList.add(w)
                        }
                    }

                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres11 && it <= zakres12) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres10 && it <= zakres11) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres9 && it <= zakres10) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres8 && it <= zakres9) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres7 && it <= zakres8) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres6 && it <= zakres7) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres5 && it <= zakres6) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres4 && it <= zakres5) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres3 && it <= zakres4) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it > zakres2 && it <= zakres3) {
                            newList.add(w)
                        }
                    }
                }
                for (w in tmppp) {
                    w.time?.let {
                        if (it <= zakres1) {
                            newList.add(w)
                        }
                    }
                }

                ///////////
                var newestList = arrayListOf<Word>()//porzadkowanie wg nieznane,kojarze,znane
                for (w in newList) {
                    if (w.group == 0) {
                        newestList.add(w)
                    }
                }
                for (w in newList) {
                    if (w.group == 1) {
                        newestList.add(w)
                    }
                }
                for (w in newList) {
                    if (w.group == 2) {
                        newestList.add(w)
                    }
                }
                showedWords = newestList



                showedWords.let {
                    list.adapter =
                        AdapterWordsListView(context, showedWords!!)
                    Log.d("MARCIN", "refresh list");
                    Toast.makeText(
                        context,
                        "VERB = ${licznikVerb}, \n ADJ = $licznikAdjective, \n NOUN =$licznikNoun",
                        Toast.LENGTH_LONG
                    ).show();
                }


            }
        })
        filterSentencesButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                Log.d("MARCIN", "filtr button");
                showedWords = arrayListOf()

                var copy = originalWords ?: arrayListOf()

                for (i in copy) {
                    if (i.greenCollocationsCount > 0 || i.greenSentencesCount > 0) {
                        showedWords?.add(i)

                    }
                }



                showedWords.let {
                    list.adapter =
                        AdapterWordsListView(context, showedWords!!)
                    Log.d("MARCIN", "refresh list");

                }


            }
        })
        studyButton.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                Log.d("MARCIN", "filtr button");
                showedWords = arrayListOf()

                var copy = originalWords ?: arrayListOf()

                for (i in copy) {
                    if (i.greenCollocationsCount > 0 || i.greenSentencesCount > 0) {
                        showedWords?.add(i)

                    }
                }


                showedWords?.let {
//tru mam wykrzykniki moze byc bład bo kopilator nie pozwolił bez tego bo nie mozna kolcetion.arraylist na java.arraylist

                    list.adapter =
                        AdapterWordsListView(context, showedWords!!)
                    Log.d("MARCIN", "refresh list");

                    var allGreenCollocations =
                        mListener?.getGreenCollocationsFromDatabase() ?: arrayListOf()

                    for (i in showedWords!!.indices) {
                        try {
                            showedWords!![i].study_String = allGreenCollocations[i]
                        } catch (e: Exception) {

                        }
                    }
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
        try {
            (list.adapter as AdapterWordsListView).setOnFavouriteListener(this)

        } catch (t: Throwable) {
        }
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
        fun getGreenCollocationsFromDatabase(): List<String>
        fun favouriteClick(id: Int, isFavourite: Boolean)
        fun openTimeActivityClick(list: List<Word>)
    }

    override fun favouriteClick(id: Int, isFavourite: Boolean) {
        mListener?.favouriteClick(id, isFavourite)
    }

    override fun openTimeActivityClick(id: Int, list: List<Word>) {
        Log.d("MARCIN_LIST", "jest w liscie: ${list.size}");

        var listReturn = arrayListOf<Word>()
        var boolean = false
        for (w in list) {
            if (w.id == id) {
                boolean = true
            }
            if (boolean) {
                listReturn.add(w)
            }
        }
        Log.d("MARCIN_LIST", "pobrano z listy: ${listReturn.size}");

        mListener?.openTimeActivityClick(listReturn)
    }

//////////////////////////////


}