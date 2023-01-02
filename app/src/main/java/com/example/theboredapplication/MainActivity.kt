package com.example.theboredapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.theboredapplication.view.QuoteFragment

class MainActivity : AppCompatActivity() {

    lateinit var left_button: Button
    lateinit var mid_button: Button
    lateinit var right_button: Button

    lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSharedPref = getSharedPreferences("sharedP", Context.MODE_PRIVATE)

        navigateToRandomQuotePage()

        left_button = findViewById<Button>(R.id.left_button)
        mid_button = findViewById<Button>(R.id.mid_button)
        right_button = findViewById<Button>(R.id.right_button)

        mid_button.setOnClickListener{
            navigateToRandomQuotePage()

            println("from shared pref"+mSharedPref.getString("tmp", "").toString())
        }

    }

    private fun navigateToRandomQuotePage(){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,QuoteFragment()).commit()
    }
}