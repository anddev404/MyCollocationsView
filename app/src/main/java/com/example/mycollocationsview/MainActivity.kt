package com.example.mycollocationsview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collocation_view_fragment.CollocationsFragment
import com.example.collocation_view_fragment.TestClass


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "" + TestClass.getString(), Toast.LENGTH_LONG).show();

        val fragment: CollocationsFragment = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment) as CollocationsFragment //lubgetSupportFragmentManager


        if (fragment != null && fragment.isInLayout()) {
            fragment.setText("Witam, doszła wiadomość?")
        }


    }
}