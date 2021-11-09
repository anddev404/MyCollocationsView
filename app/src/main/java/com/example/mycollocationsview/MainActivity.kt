package com.example.mycollocationsview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collocation_view_fragment.Collocation
import com.example.collocation_view_fragment.CollocationsFragment

class MainActivity : AppCompatActivity(), CollocationsFragment.OnCollocationFragmentListener,
    View.OnClickListener {

    lateinit var fragment1: CollocationsFragment
    lateinit var fragment2: CollocationsFragment
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO w przyszłosci zaimplementopwac pobnieranie z limitem i pobiuerac i wyswietlac limit
        //TODO dodać ukrywanie i opcje
        //TODO przetestować zmiane we fragmencie tzn gdy sicagam nowe dane czy sie wyswietlaja i zmieniaja

        button = findViewById(R.id.changeCollocationButton) as Button
        button.setOnClickListener(this)

        var dogCollocations = Collocation.getListOfCollocationDog()
        fragment1 = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment) as CollocationsFragment

        if (fragment1 != null && fragment1.isInLayout()) {
            fragment1.setCollocations(dogCollocations)
        }
        fragment1.setOnCollocationFragmentListener(this)


        var catCollocations = Collocation.getListOfCollocationCat()
        fragment2 = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment2) as CollocationsFragment

        if (fragment2 != null && fragment2.isInLayout()) {
            fragment2.setCollocations(catCollocations)
        }
        fragment2.setOnCollocationFragmentListener(this)

    }

    override fun click(fragment: CollocationsFragment, collocation: Collocation) {
        // Toast.makeText(this, "=== " + collocation.collocation + " ===", Toast.LENGTH_LONG).show();
        collocation.isChecked = !collocation.isChecked
        fragment.refreshList()
    }

    override fun option(type: Int, fragment: CollocationsFragment) {
        Toast.makeText(this, "=== " + "changed options" + " === ", Toast.LENGTH_LONG).show();
    }

    override fun onClick(v: View?) {
        if (fragment1 != null && fragment1.isInLayout()) {
            var catCollocations = Collocation.getListOfCollocationCat()
            fragment1.setCollocations(catCollocations)
        }
    }
}