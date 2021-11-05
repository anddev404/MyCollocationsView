package com.example.collocation_view_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


class CollocationsFragment : Fragment() {

    lateinit var list: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FRAGMENT", "create view");

        var view = inflater.inflate(R.layout.collocations_fragment, container, false)

        list = view.findViewById<View>(R.id.collocations_list_view) as ListView
        list.adapter =
            AdapterCollocationsListView(requireContext(), Collocation.getListOfCollocation())

        return view
    }

    fun setText(txt: String) {
//        val view = requireView().findViewById<View>(R.id.fragment_text) as TextView
//        view.text = txt
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}