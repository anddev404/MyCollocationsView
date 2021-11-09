package com.example.collocation_view_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment


class CollocationsFragment : Fragment(), AdapterView.OnItemClickListener {

    lateinit var list: ListView
    lateinit var collocationsList: List<Collocation>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FRAGMENT", "create view");

        var view = inflater.inflate(R.layout.collocations_fragment, container, false)
        list = view.findViewById<View>(R.id.collocations_list_view) as ListView

        return view
    }

    fun setCollocations(collocations: List<Collocation>) {
        collocationsList = collocations
        list.adapter =
            AdapterCollocationsListView(requireContext(), collocationsList)
        list.setOnItemClickListener(this)
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
        val HIDE_TRANSLATION = 2
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

    //endregion

}