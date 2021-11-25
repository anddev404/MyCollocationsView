package com.example.mycollocationsview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collocation_view_fragment.Collocation
import com.example.collocation_view_fragment.CollocationsFragment

class MainActivity : AppCompatActivity(), CollocationsFragment.OnCollocationFragmentListener,
    View.OnClickListener {

    lateinit var fragment1: CollocationsFragment
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MY_DEBUG", "main activty");
        supportActionBar?.hide()

        testFragments()

    }

    override fun onClick(v: View?) {
        onClickFragment()
    }
    //region fargments test


    override fun click(fragment: CollocationsFragment, collocation: Collocation) {
        collocation.isChecked = !collocation.isChecked
        fragment.refreshList()
    }

    override fun option(type: Int, fragment: CollocationsFragment) {
        Toast.makeText(this, "===  changed options   === ", Toast.LENGTH_LONG).show()
    }


    fun testFragments() {

        //TODO w przyszłosci zaimplementować pobieranie z limitem i pobierac i wyswietlac limit

        button = findViewById(R.id.changeCollocationButton) as Button
        button.setOnClickListener(this)

        var dogCollocations = Collocation.getListOfCollocationDog()
        fragment1 = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment) as CollocationsFragment

        if (fragment1 != null && fragment1.isInLayout()) {
            fragment1.setCollocations(dogCollocations, false, false, false)
        }
        fragment1.setOnCollocationFragmentListener(this)

    }

    fun onClickFragment() {
        if (fragment1 != null && fragment1.isInLayout()) {

            var list = fragment1.getActualCollocationsList()
            //here changing of list e.g. downloading translations
            //  list.get(0).
            fragment1.updateCollocationList(list)

        }
    }
    //endregion
}