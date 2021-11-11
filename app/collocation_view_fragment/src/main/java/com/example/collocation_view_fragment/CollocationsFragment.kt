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

    lateinit var list: ListView
    lateinit var collocationsList: List<Collocation>
    var hideUnknown = false
    var hideSentences = false
    var hideTranslations = false

    lateinit var hideUnknownCheckBox: CheckBox
    lateinit var hideSentencesCheckBox: CheckBox
    lateinit var hideTranslationsCheckBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FRAGMENT", "create view");

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

    fun setCollocations(
        collocations: List<Collocation>,
        hideUnknown: Boolean,
        hideTranslations: Boolean,
        hideSentences: Boolean
    ) {
        collocationsList = collocations
        this.hideUnknown = hideUnknown
        this.hideTranslations = hideTranslations
        this.hideSentences = hideSentences

        setHideUnknown()

        list.setOnItemClickListener(this)
    }

    fun setHideUnknown() {
        if (hideUnknown) {
            hideUnknownCheckBox.isChecked = true
            list.adapter =
                AdapterCollocationsListView(requireContext(), getOnlyKnown(collocationsList))
        } else {
            hideUnknownCheckBox.isChecked = false
            list.adapter =
                AdapterCollocationsListView(requireContext(), collocationsList)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mListener?.click(this, list.getItemAtPosition(position) as Collocation)

    }


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

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView == hideUnknownCheckBox) {
            hideUnknown = isChecked
            setHideUnknown()
        }
        if (buttonView == hideTranslationsCheckBox) {
            Log.d("CHECKBOX", "hide translation " + isChecked);
        }
        if (buttonView == hideSentencesCheckBox) {
            Log.d("CHECKBOX", "hide sentence " + isChecked);
        }
    }
    //endregion

}