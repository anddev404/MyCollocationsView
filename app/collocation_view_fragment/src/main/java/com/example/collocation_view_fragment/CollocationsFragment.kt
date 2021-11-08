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
        list.adapter =
            AdapterCollocationsListView(requireContext(), collocations)
        list.setOnItemClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

//        mListener?.click(SELECTED_ITEM, this, list.getItemAtPosition(position) as Collocation)
        mListener?.click(SELECTED_HIDE_TRANSLATION, this, null)

    }


    //region listener
    private var mListener: OnCollocationFragmentListener? = null

    companion object {
        val SELECTED_HIDE_UNKNOWN = 1
        val SELECTED_HIDE_TRANSLATION = 2
        val SELECTED_ITEM = 3
    }


    fun setOnCollocationFragmentListener(onCollocationFragmentListener: OnCollocationFragmentListener) {
        mListener = onCollocationFragmentListener;

    }

    interface OnCollocationFragmentListener {
        fun click(type: Int, fragment: CollocationsFragment, extraObject: Any?)

    }
//endregion


}