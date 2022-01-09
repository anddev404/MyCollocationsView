package com.example.mycollocationsview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.collocation_view_fragment.GroupingCollocationTools

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

        var c = GroupingCollocationTools()

        //  c.toLogCollocations(GroupingCollocationTools.list_to_A_into_family)
        var l = c.groupListAndAddHeaders(GroupingCollocationTools.list_to_get_into_A, 1, "")
        c.toLogCollocations(l)
    }
}