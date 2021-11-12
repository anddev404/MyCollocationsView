package com.example.collocation_view_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment


class CollocationsFragment : Fragment(), AdapterView.OnItemClickListener,
    CompoundButton.OnCheckedChangeListener {

    //region variables

    lateinit var list: ListView
    lateinit var collocationsList: List<Collocation>

    var hideUnknown = false
    var hideSentences = false
    var hideTranslations = false

    lateinit var hideUnknownCheckBox: CheckBox
    lateinit var hideSentencesCheckBox: CheckBox
    lateinit var hideTranslationsCheckBox: CheckBox

    //endregion

    //region fargment

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

        hideUnknownCheckBox.setOnCheckedChangeListener(this)
        hideTranslationsCheckBox.setOnCheckedChangeListener(this)
        hideSentencesCheckBox.setOnCheckedChangeListener(this)

        return view
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

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mListener?.click(this, list.getItemAtPosition(position) as Collocation)

    }

    //endregion

    //region listener
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
        fun click(fragment: CollocationsFragment, collocation: Collocation)
        fun option(type: Int, fragment: CollocationsFragment)
    }

    //endregion

    //region input
    fun setCollocations(
        collocations: List<Collocation>,
        hideUnknown: Boolean,
        hideTranslations: Boolean,
        hideSentences: Boolean
    ) {
        Log.d("MY_DEBUG", "fragment- set...");
        collocationsList = collocations
        this.hideUnknown = hideUnknown
        this.hideTranslations = hideTranslations
        this.hideSentences = hideSentences

        hideUnknownCheckBox.setOnCheckedChangeListener(null)
        hideTranslationsCheckBox.setOnCheckedChangeListener(null)
        hideSentencesCheckBox.setOnCheckedChangeListener(null)
        setHideUnknown()
//        setHideSentences()
//        setHideTranslations()
        hideUnknownCheckBox.setOnCheckedChangeListener(this)
        hideTranslationsCheckBox.setOnCheckedChangeListener(this)
        hideSentencesCheckBox.setOnCheckedChangeListener(this)
        list.setOnItemClickListener(this)
    }

    fun setHideUnknown() {
        if (hideUnknown) {
            hideUnknownCheckBox.setOnCheckedChangeListener(null)
            hideUnknownCheckBox.isChecked = true
            hideUnknownCheckBox.setOnCheckedChangeListener(this)
            list.adapter =
                AdapterCollocationsListView(requireContext(), getOnlyKnown(collocationsList))
        } else {
            hideUnknownCheckBox.setOnCheckedChangeListener(null)
            hideUnknownCheckBox.isChecked = false
            hideUnknownCheckBox.setOnCheckedChangeListener(this)
            list.adapter =
                AdapterCollocationsListView(requireContext(), collocationsList)
        }
    }

    fun setHideSentences() {
        if (hideSentences) {
            // hideSentencesCheckBox.isChecked = true
            Log.d("MARCIN", "hide sentences");


        } else {
            hideSentencesCheckBox.isChecked = false
            Log.d("MARCIN", "not hide sentences");
        }
    }

    fun setHideTranslations() {

        var lll = list.adapter as AdapterCollocationsListView
        lll?.hideTranslations()
        refreshList()
//        if (hideTranslations) {
//            hideTranslationsCheckBox.isChecked = true
//            Log.d("MARCIN", "hide translation");
//            var lll = list.adapter as AdapterCollocationsListView
//            lll?.hideTranslations()
//        } else {
//            hideTranslationsCheckBox.isChecked = false
//            Log.d("MARCIN", "not hide translation");
//
//            var lll = list.adapter as AdapterCollocationsListView
//            lll?.showTranslations()
//        }
    }

    //endregion

    //region method

    fun refreshList() {
        list.invalidateViews()
    }

    fun getOnlyKnown(list: List<Collocation>): List<Collocation> {
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