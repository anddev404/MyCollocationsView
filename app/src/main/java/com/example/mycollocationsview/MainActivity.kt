package com.example.mycollocationsview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.collocation_view_fragment.CollocationsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: CollocationsFragment = supportFragmentManager
            .findFragmentById(R.id.colocationsFragment) as CollocationsFragment //lubgetSupportFragmentManager

        if (fragment != null && fragment.isInLayout()) {
            fragment.setText("Witam, doszła wiadomość?")
        }


    }
}