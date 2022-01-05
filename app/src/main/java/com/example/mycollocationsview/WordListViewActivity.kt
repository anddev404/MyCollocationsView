package com.example.mycollocationsview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.anddev404.words_list_view.AdapterWordsListView
import com.anddev404.words_list_view.SearchWordView
import com.anddev404.words_list_view.Word
import com.example.collocation_view_fragment.AdapterCollocationsListView

class WordListViewActivity : AppCompatActivity() {
    lateinit var list: SearchWordView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list_view)

        list = findViewById<View>(R.id.custom_word_search_view) as SearchWordView
        list.setWords(Word.getList())
        list.setOnSearchWordViewListener(object : SearchWordView.OnSearchWordViewListener {
            override fun wordSearch(word: String, id: Int) {

                Toast.makeText(applicationContext, "Wybrano: $word", Toast.LENGTH_LONG).show();
            }

            override fun collocationsSearch(word: String) {
                Toast.makeText(applicationContext, "Szukasz kolokacji: $word", Toast.LENGTH_LONG)
                    .show();
            }

            override fun sentencesSearch(word: String) {
                Toast.makeText(applicationContext, "Szukasz zdań: $word", Toast.LENGTH_LONG).show();
            }

            override fun getGreenCollocationsFromDatabase(): List<String> {
                var list = arrayListOf<String>()
                list.add("jeden")
                list.add("dwa")
                list.add("trzy")
                list.add("cztery")

                //   Thread.sleep(3500)
                return list
            }
        })

    }
}