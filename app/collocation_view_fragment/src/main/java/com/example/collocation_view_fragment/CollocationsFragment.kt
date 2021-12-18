package com.example.collocation_view_fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

/**
 * @param
 * @return
 * @sample
 *
 *    fragment1 = supportFragmentManager
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
 */
class CollocationsFragment : Fragment(), AdapterView.OnItemClickListener,
    AdapterCollocationsListView.OnSentenceClickListener,
    CompoundButton.OnCheckedChangeListener {

    //region variables

    lateinit var list: ListView
    lateinit var collocationsList: ArrayList<Collocation>

    var hideUnknown = false
    var hideSentences = true
    var hideUnknownSentences = false
    var hideTranslations = false

    lateinit var hideUnknownCheckBox: CheckBox
    lateinit var hideSentencesCheckBox: CheckBox
    lateinit var hideTranslationsCheckBox: CheckBox
    lateinit var hideUnknownSentenceCheckBox: CheckBox

    lateinit var leftTextView: TextView
    lateinit var centerTextView: TextView
    lateinit var rightTextView: TextView

    lateinit var headTextView: TextView
    //endregion

    //region fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FRAGMENT", "create view");
        Log.d("MY_DEBUG", "fragment");
        var view = inflater.inflate(R.layout.collocations_fragment, container, false)
        list = view.findViewById<View>(R.id.collocations_list_view) as ListView
        hideUnknownCheckBox = view.findViewById(R.id.show_only_known_check_box) as CheckBox
        hideTranslationsCheckBox = view.findViewById(R.id.hide_translations_check_box) as CheckBox
        hideSentencesCheckBox = view.findViewById(R.id.show_only_headlines_check_box) as CheckBox
        hideUnknownSentenceCheckBox =
            view.findViewById(R.id.show_only_green_sentences_check_box) as CheckBox

        hideUnknownCheckBox.setOnCheckedChangeListener(this)
        hideTranslationsCheckBox.setOnCheckedChangeListener(this)
        hideSentencesCheckBox.setOnCheckedChangeListener(this)
        hideUnknownSentenceCheckBox.setOnCheckedChangeListener(this)

        headTextView = view.findViewById(R.id.fragment_text_view) as TextView

        leftTextView = view.findViewById(R.id.textViewLeft) as TextView
        leftTextView.setOnClickListener {
            mListener?.left()
        }

        rightTextView = view.findViewById(R.id.textViewRight) as TextView
        rightTextView.setOnClickListener {
            mListener?.right()
        }
        centerTextView = view.findViewById(R.id.textViewCenter) as TextView
        centerTextView.setOnClickListener {
            // Toast.makeText(view.context, "wyświetlanie listy", Toast.LENGTH_LONG).show();
            mListener?.centre()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var c = list.getItemAtPosition(position) as Collocation
        c.isChecked = !c.isChecked

        mListener?.clickItem(this, c)
        refreshList()
    }

    override fun sentenceClick(collocation: Collocation, nr: Int) {
        Log.d("MARCIN", "click sentence nr $nr id: $id");


        mListener?.clickSentence(this, collocation, nr)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("MY_DEBUG_ADAPTER", "on Checked");
        if (buttonView == hideUnknownCheckBox) {
            hideUnknown = isChecked
            setHideUnknown()
        }
        if (buttonView == hideSentencesCheckBox) {
            hideSentences = isChecked
            setHideSentences()
        }
        if (buttonView == hideTranslationsCheckBox) {
            hideTranslations = isChecked
            setHideTranslations()
        }
        if (buttonView == hideUnknownSentenceCheckBox) {
            hideUnknownSentences = isChecked
            setHideUnknownSentences()
        }
    }
    //endregion

    //region input >
    fun setHeadText(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            headTextView.setText(
                Html.fromHtml(
                    text,
                    Html.FROM_HTML_MODE_COMPACT
                )
            );
        } else {
            headTextView.setText(Html.fromHtml(text));
        }

    }

    fun setCollocations(
        collocations: ArrayList<Collocation>,
//        hideUnknown: Boolean,
//        hideTranslations: Boolean,
//        hideSentences: Boolean,
        leftWord: String, centreWord: String, rightWord: String
    ) {
        Log.d("MY_DEBUG", "fragment- set...");
        collocationsList = collocations
        this.hideUnknown = hideUnknown
        this.hideTranslations = hideTranslations
        this.hideSentences = hideSentences

        leftTextView.text = leftWord
        centerTextView.text = centreWord
        rightTextView.text = rightWord

        hideUnknownCheckBox.setOnCheckedChangeListener(null)
        hideTranslationsCheckBox.setOnCheckedChangeListener(null)
        hideSentencesCheckBox.setOnCheckedChangeListener(null)

        setHideUnknown()
        setHideSentences()
        setHideTranslations()
        setHideUnknownSentences()

        hideUnknownCheckBox.setOnCheckedChangeListener(this)
        hideTranslationsCheckBox.setOnCheckedChangeListener(this)
        hideSentencesCheckBox.setOnCheckedChangeListener(this)
        list.setOnItemClickListener(this)
    }

    fun updateCollocationList(list: ArrayList<Collocation>) {
        collocationsList = list
        refreshList()
    }
    //endregion

    //region < output

    fun getActualCollocationsList(): ArrayList<Collocation> {
        return collocationsList
    }

    //endregion

    //region < listener
    private var mListener: OnCollocationFragmentListener? = null

    companion object {
        val HIDE_UNKNOWN = 1
        val HIDE_TRANSLATIONS = 2
        val HIDE_SENTENCES = 3
    }


    fun setOnCollocationFragmentListener(onCollocationFragmentListener: OnCollocationFragmentListener) {
        mListener = onCollocationFragmentListener;

    }

    interface OnCollocationFragmentListener {
        fun clickItem(fragment: CollocationsFragment, collocation: Collocation)
        fun clickSentence(fragment: CollocationsFragment, collocation: Collocation, nr: Int)

        //        fun option(type: Int, fragment: CollocationsFragment)
        fun left()
        fun right()
        fun centre()

    }

    //endregion

    //region list view input >

    fun setHideUnknown() {
        //TODO jesli mam pokazywanie zdan tylko zielonych i w jakiejs kolokacji zxwine ja i rozwine to wtedy pokaze wszytskie zdania nawet te nie zielone ale to w sumie dobrze
        if (hideUnknown) {
            hideUnknownCheckBox.setOnCheckedChangeListener(null)
            hideUnknownCheckBox.isChecked = true
            hideUnknownCheckBox.setOnCheckedChangeListener(this)

            list.adapter =
                AdapterCollocationsListView(requireContext(), getOnlyKnown(collocationsList))
            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.isHideTranslation = hideTranslations
            adapter.isHideSentences = hideSentences
            adapter.isHideUnknownSentences = hideUnknownSentences

            refreshList()

        } else {
            hideUnknownCheckBox.setOnCheckedChangeListener(null)
            hideUnknownCheckBox.isChecked = false
            hideUnknownCheckBox.setOnCheckedChangeListener(this)

            list.adapter =
                AdapterCollocationsListView(requireContext(), collocationsList)
            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.isHideTranslation = hideTranslations
            adapter.isHideSentences = hideSentences
            adapter.isHideUnknownSentences = hideUnknownSentences

            refreshList()
        }
    }

    fun setHideSentences() {
        if (hideSentences) {

            hideSentencesCheckBox.setOnCheckedChangeListener(null)
            hideSentencesCheckBox.isChecked = true
            hideSentencesCheckBox.setOnCheckedChangeListener(this)

            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.hideSentences()
            refreshList()


        } else {

            hideSentencesCheckBox.setOnCheckedChangeListener(null)
            hideSentencesCheckBox.isChecked = false
            hideSentencesCheckBox.setOnCheckedChangeListener(this)

            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.showSentences()
            refreshList()
        }

    }

    fun setHideUnknownSentences() {
        if (hideUnknownSentences) {

            hideUnknownSentenceCheckBox.setOnCheckedChangeListener(null)
            hideUnknownSentenceCheckBox.isChecked = true
            hideUnknownSentenceCheckBox.setOnCheckedChangeListener(this)

            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.hideUnknownSentences()
            refreshList()


        } else {

            hideUnknownSentenceCheckBox.setOnCheckedChangeListener(null)
            hideUnknownSentenceCheckBox.isChecked = false
            hideUnknownSentenceCheckBox.setOnCheckedChangeListener(this)

            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.showUnknownSentences()
            refreshList()
        }

    }

    fun setHideTranslations() {
        if (hideTranslations) {

            hideTranslationsCheckBox.setOnCheckedChangeListener(null)
            hideTranslationsCheckBox.isChecked = true
            hideTranslationsCheckBox.setOnCheckedChangeListener(this)

            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.hideTranslations()
            refreshList()


        } else {

            hideTranslationsCheckBox.setOnCheckedChangeListener(null)
            hideTranslationsCheckBox.isChecked = false
            hideTranslationsCheckBox.setOnCheckedChangeListener(this)

            var adapter = list.adapter as AdapterCollocationsListView
            adapter.setOnSentenceClickListener(this)
            adapter.showTranslations()
            refreshList()
        }

    }

    //endregion

    //region method

    fun refreshList() {
        list.invalidateViews()
    }

    fun getOnlyKnown(list: ArrayList<Collocation>): ArrayList<Collocation> {
        var newList = ArrayList<Collocation>()
        for (i in list) {
            if (i.isChecked) {
                newList.add(i)
            }
        }
        return newList
    }

    //endregion

}