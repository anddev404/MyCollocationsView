package com.example.collocation_view_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class CollocationsFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FRAGMENT", "create view");

        return inflater.inflate(R.layout.collocations_fragment, container, false)
    }

    fun setText(txt: String) {
        val view = requireView().findViewById<View>(R.id.fragment_text) as TextView
        view.text = txt
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}