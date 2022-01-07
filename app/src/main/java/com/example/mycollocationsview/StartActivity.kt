package com.example.mycollocationsview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var collocationButton = findViewById<View>(R.id.button_collocation)
        collocationButton.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }
        var wordListButton = findViewById<View>(R.id.button2_list)
        wordListButton.setOnClickListener {
            var intent = Intent(this, WordListViewActivity::class.java);
            startActivity(intent);
        }


    }
}