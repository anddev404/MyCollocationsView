package com.example.mycollocationsview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anddev404.sentences_view.SentencesFragment
import com.example.collocation_view_fragment.Collocation
import com.example.collocation_view_fragment.CollocationsFragment
import com.example.collocation_view_fragment.part_of_speech.PartOfSpeechFragment

class MainActivity : AppCompatActivity(), CollocationsFragment.OnCollocationFragmentListener,
    View.OnClickListener, PartOfSpeechFragment.OnPartOfSpeechFragmentListener,
    SentencesFragment.OnSentenceClickListener {


    lateinit var fragment1: CollocationsFragment
    lateinit var fragmentPartOfSpeech: PartOfSpeechFragment
    lateinit var fragmentSentence: SentencesFragment

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MY_DEBUG", "main activty");
        supportActionBar?.hide()

        testFragments()


    }


    //region fargments test

    /**
     * @param
     * @return
     * @sample tylko aktualizacja bazy. Model(collocation.isChecked) został już zmieniony.
     */
    override fun clickItem(fragment: CollocationsFragment, collocation: Collocation) {
        collocation?.let {
            Log.d(
                "MARCIN", "${
                    collocation.id
                } ${collocation.collocation}"
            )
        }
    }

    override fun clickSentence(
        fragment: CollocationsFragment,
        collocation: Collocation,
        nr: Int
    ) {
        //TODO("Not yet implemented")
    }

    /**
     * @param
     * @return
     * @sample 1. reset widoku na PartOfSpeechFragment.PART_OF_SPEECH_UNKNOWN
     *  2. pobranie wczesniejszej kolokacji z domyslną relacją dla PART_OF_SPEECH_UNKNOWN to PartOfSpeechFragment.RELATION_1_V_obj_N
     *  3. setCollocations
     *
     *  example:
     *   var dogCollocations =
    Collocation.getListOfCollocationDog(PartOfSpeechFragment.RELATION_1_V_obj_N)

    if (fragment1 != null && fragment1.isInLayout()) {
    fragment1.setCollocations(
    dogCollocations,
    "cat",
    "3/3\ndog",
    "dog"
    )
    }
    fragmentPartOfSpeech.resetViews(PartOfSpeechFragment.PART_OF_SPEECH_UNKNOWN)
     */
    override fun left() {
        var dogCollocations =
            Collocation.getListOfCollocationCat(PartOfSpeechFragment.RELATION_1_V_obj_N)

        if (fragment1 != null && fragment1.isInLayout()) {
            fragment1.setCollocations(
                dogCollocations,
                "cat",
                "1/3\ncat",
                "dog"
            )
        }
        fragmentPartOfSpeech.resetViews(PartOfSpeechFragment.PART_OF_SPEECH_UNKNOWN)

    }

    /**
     * @param
     * @return
     * @sample 1. reset widoku na PartOfSpeechFragment.PART_OF_SPEECH_UNKNOWN
     *  2. pobranie następnej kolokacji z domyslną relacją dla PART_OF_SPEECH_UNKNOWN to PartOfSpeechFragment.RELATION_1_V_obj_N
     *  3. setCollocations
     *
     * example:
     *   var dogCollocations =
    Collocation.getListOfCollocationDog(PartOfSpeechFragment.RELATION_1_V_obj_N)

    if (fragment1 != null && fragment1.isInLayout()) {
    fragment1.setCollocations(
    dogCollocations,
    "cat",
    "3/3\ndog",
    "dog"
    )
    }
    fragmentPartOfSpeech.resetViews(PartOfSpeechFragment.PART_OF_SPEECH_UNKNOWN)
     */
    override fun right() {
        var dogCollocations =
            Collocation.getListOfCollocationDog(PartOfSpeechFragment.RELATION_1_V_obj_N)

        if (fragment1 != null && fragment1.isInLayout()) {
            fragment1.setCollocations(
                dogCollocations,
                "cat",
                "3/3\ndog",
                "dog"
            )
        }
        fragmentPartOfSpeech.resetViews(PartOfSpeechFragment.PART_OF_SPEECH_UNKNOWN)

    }

    override fun centre() {
        Toast.makeText(this, "click centre button", Toast.LENGTH_LONG).show();
    }


    fun testFragments() {
        fragmentSentence = supportFragmentManager
            .findFragmentById(R.id.sentencesFragment) as SentencesFragment
        fragmentSentence.setOnSentenceClickListener(this)
        fragmentSentence.setSentences("one", "jeden", true)
        button = findViewById(R.id.changeCollocationButton) as Button
        button.setOnClickListener(this)

        fragmentPartOfSpeech = supportFragmentManager
            .findFragmentById(R.id.partOfSpeechFragment) as PartOfSpeechFragment
        fragmentPartOfSpeech.setOnPartOfSpeechFragmentListener(this)


        fragment1 = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment) as CollocationsFragment
        if (fragmentPartOfSpeech != null && fragmentPartOfSpeech.isInLayout()) {
            fragmentPartOfSpeech.resetViews(PartOfSpeechFragment.PART_OF_SPEECH_UNKNOWN)
            var dogCollocations =
                Collocation.getListOfCollocationDog(PartOfSpeechFragment.RELATION_1_V_obj_N)

            fragment1.setCollocations(
                dogCollocations,
                "cat",
                "2/3\ndog",
                "dog2"
            )
        }
        fragment1.setOnCollocationFragmentListener(this)


    }

    /**
     * @param
     * @return
     * zmiana relacji powoduje pobranie odpowiednich kolokacji z danego słowa
     * @sample
     *  var dogCollocations = Collocation.getListOfCollocationDog(relation)

    if (fragment1 != null && fragment1.isInLayout()) {
    fragment1.setCollocations(
    dogCollocations,
    "cat",
    "2/3\ndog",
    "dog"
    )
    }
     */
    override fun relation(relation: String) {
        Log.d("RELATION", "relation $relation");


        var dogCollocations =
            Collocation.getListOfCollocationDog(relation)//pobranie z aktualnego słowa

        if (fragment1 != null && fragment1.isInLayout()) {
            fragment1.setCollocations(
                dogCollocations,
                "cat",
                "2/3\ndog",
                "dog"
            )
        }
    }
//endregion

    //    fun onClickFragment() {
//        if (fragment1 != null && fragment1.isInLayout()) {
//
//            var list = fragment1.getActualCollocationsList()
//
//            fragment1.updateCollocationList(list)
//            Toast.makeText(this, "CLICKKKK", Toast.LENGTH_LONG).show();
//
//        }
//    }
    override fun onClick(v: View?) {
//        onClickFragment()
    }


    override fun sentenceClick(
        fragment: View,
        nr: Int,
        sentence: String,
        translation: String,
        isChecked: Boolean
    ) {
        Toast.makeText(
            this,
            "click sentence: $nr, $sentence, $translation, $isChecked,",
            Toast.LENGTH_LONG
        ).show();
    }
}