package com.example.mycollocationsview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collocation_view_fragment.Collocation
import com.example.collocation_view_fragment.CollocationsFragment

class MainActivity : AppCompatActivity(), CollocationsFragment.OnCollocationFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//todo opcje, parametry opcji przyslane do fragmenta , listener - odbieranie z paarametrem obiektu fragmenta,typ operacji np click lub zmiana opcjiu, ukrywanie tlumaczenia
        //TODO w przyszłosci zaimplementopwac pobnieranie z limitem i pobiuerac i wyswietlac limit
        var ttt = Collocation.getListOfCollocation()
        val fragment: CollocationsFragment = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment) as CollocationsFragment //lubgetSupportFragmentManager

        if (fragment != null && fragment.isInLayout()) {
            fragment.setCollocations(Collocation.getListOfCollocation())
        }
        var ttt2 = Collocation.getListOfCollocation()
        val fragment2: CollocationsFragment = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment2) as CollocationsFragment //lubgetSupportFragmentManager

        if (fragment2 != null && fragment2.isInLayout()) {
            fragment2.setCollocations(Collocation.getListOfCollocation())
        }
        fragment.setOnCollocationFragmentListener(this)
    }

    override fun click(type: Int, fragment: CollocationsFragment, extraObject: Any?) {

        if (type == CollocationsFragment.SELECTED_ITEM) {
            var item = extraObject as Collocation
            Toast.makeText(this, "=== " + item.collocation + " ===", Toast.LENGTH_LONG).show();

        }
        if (type == CollocationsFragment.SELECTED_HIDE_TRANSLATION) {
            Toast.makeText(this, "=== " + "hide translation" + " === ", Toast.LENGTH_LONG).show();

        }
    }

//
//    fragment1.setOnSubmitListener(this);
//
//    @Override
//    public void setOnSubmitListener(String arg)
//    {
//        text.setText(arg);
//    }

}